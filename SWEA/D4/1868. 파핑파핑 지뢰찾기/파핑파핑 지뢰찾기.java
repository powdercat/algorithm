import java.io.*;
import java.util.*;

public class Solution {
	
	static int[] dx = {1, 1, -1, -1, 0, 0, 1, -1};
	static int[] dy = {0, -1, 0, 1, 1, -1, 1, -1};
	static int N;
	static char[][] map;
	
    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 지뢰가 없는 칸 선택 -> 주변이 숫자로 변함 -> 또 선택 -> 최소 클릭 수
    	// 터뜨릴때 팔방으로 이동하는데, 그 팔방의 팔방에 지뢰가 없을때만 이동 가능
    	// 한 번 터뜨린 다음 남은 애들 또 포문으로 돌기
    	// 터뜨리는 횟수를 잘 저장해야겠다
    	
    	// 0인 경우들을 다 터뜨리기 시도
    	// 그 다음은 갯수만큼+
    	
    	int T = Integer.parseInt(br.readLine());
    	for (int t = 1; t <= T; t++) {
    		N = Integer.parseInt(br.readLine());
        	int pointCnt = 0;
    		map = new char[N][N];
    		for (int i = 0; i < N; i++) {
    			String input = br.readLine();
    			for (int j = 0; j < N; j++) {
    				map[i][j] = input.charAt(j);
    				if (map[i][j] == '.') {
    					pointCnt++;
    				}
    			}
    		}
    		int result = 0;
    		int changed = 0;
        	// 0 찾기
    		// 팔방이 모두 0인 것을 찾아서 그걸로부터 시작
    		for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != '.') continue;
					int bombCnt = 0;
					for (int dir = 0; dir < 8; dir++) {
						int nx = i + dx[dir];
						int ny = j + dy[dir];
						if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
						if (map[nx][ny] == '*') {
							bombCnt++;
							break;
						}
					}
					if (bombCnt > 0) continue;
					// bfs 시작
					Deque<int[]> queue = new ArrayDeque<>();
					boolean[][] visited = new boolean[N][N];
					queue.offer(new int[] {i, j});
					map[i][j] = '0';
					changed++;
					visited[i][j] = true;
					while (!queue.isEmpty()) {
						int[] node = queue.poll();
						int x = node[0];
						int y = node[1];
				    	// bfs로 '.' 한 지점 잡기, 그 지점에서부터 '.' 팔방으로 이동
						//     (지뢰가 아닌 경우에만, 이동하면 팔방에 있는 지뢰 수 적기)
						for (int dir = 0; dir < 8; dir++) {
							int nx = x + dx[dir];
							int ny = y + dy[dir];
							if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
							if (visited[nx][ny]) continue;
							if (map[nx][ny] == '.') {
								// nx,ny 팔방에 bomb 몇 개 있는지 계산
								map[nx][ny] = bombCnt(nx, ny);
								changed++;
								// nx,ny가 0인 경우에만 큐에 넣기
								if (map[nx][ny] == '0') {
									queue.offer(new int[] {nx, ny});
									visited[nx][ny] = true;
								}
							}
						}
					}
					result++;
				}
			}
        	// 0 없으면 남은 갯수만큼
    		int left = pointCnt - changed;
    		System.out.println("#" + t + " " + (result + left));
    	}
    }

	private static char bombCnt(int x, int y) {
		int cnt = 0;
		for (int dir = 0; dir < 8; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if (map[nx][ny] == '*') {
				cnt++;
			}
		}
		return (char)('0' + cnt);
	}
}