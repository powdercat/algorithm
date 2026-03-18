import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static int[][] map, location;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// n이 1000 -> 100만 개의 방
		// 한 점에서 상하좌우로 딱 1큰 애로만 이동 가능, 가장 많은 개수 이동
		// 최대이동개수가 여러 개라면 시작방번호가 가장 작은 것
		// 출력: 시작방번호 최대이동개수
		// 완전탐색
		// 낮은 수부터 시작, 최대수=N*N
		// 현재 최대 이동 개수 = max, N*N - 시작방번호 == max면 끝. 최대로 간다고 해도 max랑 같기 때문에 더 할 필요가 없음
		// 시작방번호를 더 키울 필요도 없음

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			int totalRoom = N * N;
			location = new int[totalRoom][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()) - 1;
					location[map[i][j]] = new int[] {map[i][j], i, j, 1};
				}
			}
			

			boolean[] visited = new boolean[totalRoom];
			int max = 0;
			int maxRoomNum = -1;
			for (int i = 0; i < totalRoom; i++) {
				if (totalRoom - i == max) {
					break;
				}
				Deque<int[]> queue = new ArrayDeque<>();
				if (visited[i]) continue;
				int[] arr = Arrays.copyOf(location[i], 5);
				arr[4] = i;
				queue.offer(arr); // 방번호, i, j, 이동 횟수, 시작 방 번호
				while (!queue.isEmpty()) {
					int[] cur = queue.poll();
					int roomNum = cur[0];
					int cx = cur[1];
					int cy = cur[2];
					int count = cur[3];
					int startRoomNum = cur[4];
					if (visited[roomNum]) continue;
					visited[roomNum] = true;
					boolean move = false;
					for (int dir = 0; dir < 4; dir++) {
						int nx = cx + dx[dir];
						int ny = cy + dy[dir];
						if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
						if (map[nx][ny] == roomNum + 1) {
							queue.offer(new int[] {roomNum + 1, nx, ny, count + 1, startRoomNum});
							move = true;
							break;
						}
					}
					if (!move) {
						if (count > max) {
							max = Math.max(max, count);
							maxRoomNum = startRoomNum;
						}
						break;
					}
				}
			}
			System.out.println("#" + tc + " " + (maxRoomNum + 1) + " " + max);
		}
	}
}
