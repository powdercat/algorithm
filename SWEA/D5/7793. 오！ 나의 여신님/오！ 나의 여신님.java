import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= TC; tc++) {
			sb.append("#").append(tc).append(" ");
    		String[] input = br.readLine().split(" ");
    		int N = Integer.parseInt(input[0]);
    		int M = Integer.parseInt(input[1]);
    		
    		char[][] map = new char[N][M];
    		Deque<int[]> badQueue = new ArrayDeque<>();
    		Deque<int[]> syQueue = new ArrayDeque<>();
    		for (int i = 0; i < N; i++) {
    			String input2 = br.readLine();
    			for (int j = 0; j < M; j++) {
    				map[i][j] = input2.charAt(j);
    				if (map[i][j] == '*') {
    		    		badQueue.offer(new int[] {i, j, 0});
    				} else if (map[i][j] == 'S') {
    					map[i][j] = '.';
    		    		syQueue.offer(new int[] {i, j, 0});
    				}
    			}
    		}

    		boolean[][] syVisited = new boolean[N][M];
    		
    		int n = 0;
    		boolean find = false;
    		boolean end = false;
    		while (true) {
    			// bad 전파 - 지도가 변하겠지
    			// n초에 해당하는 좌표는 다 전파해야 함
    			while (!badQueue.isEmpty()) {
    				// n이랑 같을 때까지 뽑기
    				if (badQueue.peek()[2] != n) {
    					break;
    				}
    				int[] curBad = badQueue.poll();
    				for (int dir = 0; dir < 4; dir++) {
    					int nx = curBad[0] + dx[dir];
    					int ny = curBad[1] + dy[dir];
    					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
    					if (map[nx][ny] == '.') {
    						map[nx][ny] = '*';
    						badQueue.offer(new int[] {nx, ny, curBad[2] + 1});
    					}
    				}
    			}
    			
    			// 수연이 이동 - 이동한 경로
    			while (!syQueue.isEmpty()) {
    				// n이랑 같을 때까지 뽑기
    				if (syQueue.peek()[2] != n) {
    					break;
    				}
    				int[] curSy = syQueue.poll();
    				boolean move = false;
    				for (int dir = 0; dir < 4; dir++) {
    					int nx = curSy[0] + dx[dir];
    					int ny = curSy[1] + dy[dir];
    					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
    					if (syVisited[nx][ny]) continue;
    					syVisited[nx][ny] = true;
    					if (map[nx][ny] == 'D') {
    						find = true;
    						break;
    					}
    					
    					if (map[nx][ny] == '.') {
    						syQueue.offer(new int[] {nx, ny, curSy[2] + 1});
    					}
    				}
        			if (find) break;
    			}
    			n++;
    			if (syQueue.isEmpty()) {
    				end = true;
    			}
    			if (find || end) break;
    		}
    		if (find) {
    			sb.append(n);
    		} else {
    			sb.append("GAME OVER");
    		}
    		sb.append("\n");
    	}
    	System.out.println(sb);
    }
}