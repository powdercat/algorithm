import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int N = 100;
    	int TC = 10;
    	int[][] matrix;
    	
    	int[] dx = {1, -1, 0, 0};
    	int[] dy = {0, 0, 1, -1};
    	boolean[][] visited;
    	
    	for (int tc = 1; tc <= TC; tc++) {
    		br.readLine();
    		
    		visited = new boolean[N][N];
    		matrix = new int[N][N];
    		
    		for (int i = 0; i < N; i++) {
    			String[] input = br.readLine().split("");
    			for (int j = 0; j < N; j++) {
    				matrix[i][j] = Integer.parseInt(input[j]);
    			}
    		}
    		
    		Deque<int[]> queue = new ArrayDeque<>();

    		int cx = 1, cy = 1;
    		queue.offer(new int[] {cx, cy});
    		visited[cx][cy] = true;
    		
    		boolean find = false;
    		while (!queue.isEmpty()) {
    			int[] current = queue.poll();
    			cx = current[0];
    			cy = current[1];
    			
    			for (int dir = 0; dir < 4; dir++) {
    				int nx = cx + dx[dir];
    				int ny = cy + dy[dir];
    				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
    					continue;
    				}
    				if (visited[nx][ny]) {
    					continue;
    				}
    				if (matrix[nx][ny] == 3) {
    					find = true;
    					break;
    				}
    				
    				if (matrix[nx][ny] != 0) {
    					continue;
    				}
    				
    				visited[nx][ny] = true;
    				queue.offer(new int[]{nx, ny});
    			}
    			
    			if (find) {
    				break;
    			}
    		}
    		System.out.println("#" + tc + " " + (find ? 1 : 0));
    	}
    }
}