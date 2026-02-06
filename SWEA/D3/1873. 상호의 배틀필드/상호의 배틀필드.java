import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
    	// 상 하 좌 우
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T = Integer.parseInt(br.readLine());
    	for (int t = 1; t <= T; t++) {
    		String[] input_str = br.readLine().split(" ");
    		int H = Integer.parseInt(input_str[0]);
    		int W = Integer.parseInt(input_str[1]);
    		String[][] map = new String[H][W];

    		int cx, cy;
    		cx = cy = -1;
    		
    		int dir = -1;
    		for (int i = 0; i < H; i++) {
    			map[i] = br.readLine().split("");
    			for (int j = 0; j < W; j++) {
    				String val = map[i][j];
    				if (val.equals("<") || val.equals(">") || val.equals("^") || val.equals("v")) {
						cx = i;
						cy = j;
					}
    				if (val.equals("^")) {
    					dir = 0;
    				} else if (val.equals("v")) {
    					dir = 1;
    				} else if (val.equals("<")) {
    					dir = 2;
    				} else if (val.equals(">")) {
    					dir = 3;
    				}
    				
    			}
    		}
			
			// * * * . . . .
			// * - . . # * *
			// # < . * * * *
			
			// # 강철
			// * 벽돌
			// . 평지 (전차 들어갈 수 있음)
			// - 물 (전차 들어갈 수 없음)
			
			// 전차가 포탄 발사 시 (포탄은 계속 직진)
			// 벽돌: 평지 됨
			// 강철, 맵 벗어남: 아무 일 없음
			
			int N = Integer.parseInt(br.readLine());
			String[] cmds = br.readLine().split("");
			for (int i = 0; i < N; i++) {
				String cmd = cmds[i];
				if (cmd.equals("S")) {
					int tmp = 1;
					while (true) {
						int nx = cx + dx[dir] * tmp;
						int ny = cy + dy[dir] * tmp;
						if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
							break;
						}
						String val = map[nx][ny];
						if (val.equals("#")) {
							break;
						}
						if (val.equals("*")) {
							map[nx][ny] = ".";
							break;
						}
						// .과 -일 때 포탄은 계속되어야 함
						tmp++;
					}
					continue;
				}

				String val = "";
				// U D L R 방향 바꾸고 평지면 이동
				if (cmd.equals("U")) {
					dir = 0;
					val = "^";
				} else if (cmd.equals("D")) {
					dir = 1;
					val = "v";
				} else if (cmd.equals("L")) {
					dir = 2;
					val = "<";
				} else if (cmd.equals("R")) {
					dir = 3;
					val = ">";
				}
				map[cx][cy] = val;
				
				int nx = cx + dx[dir];
				int ny = cy + dy[dir];
				if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
					continue;
				}
				if (map[nx][ny].equals(".")) {
					map[cx][cy] = ".";
					map[nx][ny] = val;
					cx = nx;
					cy = ny;
				}
			}

			String val = "";
			if (dir == 0) {
				val = "^";
			} else if (dir == 1) {
				val = "v";
			} else if (dir == 2) {
				val = "<";
			} else {
				val = ">";
			}
			map[cx][cy] = val;
    		
        	System.out.printf("#%d ", t);
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
    	}
    }
}
