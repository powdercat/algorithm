
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		class Point {
			int x;
			int y;
			Point(int x, int y) {
				this.x = x;
				this.y = y;
			}
		}
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		int SIZE = 16;
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			br.readLine();
			
			boolean[][] visited = new boolean[SIZE][SIZE];
			
			String[][] matrix = new String[SIZE][SIZE];
			
			for (int i = 0; i < SIZE; i++) {
				matrix[i] = br.readLine().split("");
			}
			
			Queue<Point> queue = new ArrayDeque<>();
			int startX = 1;
			int startY = 1;
			queue.offer(new Point(startX, startX));
			visited[startX][startY] = true;
			
			int result = 0;
			while (!queue.isEmpty()) {
				Point p = queue.poll();
				int cx = p.x;
				int cy = p.y;
				
				for (int i = 0; i < 4; i++) {
					int nx = cx + dx[i];
					int ny = cy + dy[i];
					if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) {
						continue;
					}
					if (visited[nx][ny]) {
						continue;
					}
					visited[nx][ny] = true;
					if (matrix[nx][ny].equals("3")) {
						result = 1;
						break;
					}
					if (matrix[nx][ny].equals("0")) {
						queue.offer(new Point(nx, ny));
					}
				}
				if (result == 1) {
					break;
				}
			}
			
			System.out.println("#" + test_case + " " + result);
		}

	}

}