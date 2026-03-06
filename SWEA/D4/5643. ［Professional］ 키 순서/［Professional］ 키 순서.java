import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			// 나보다 큰 사람 수 + 나보다 작은 사람 수 + 나 = 전체 -> 알 수 있음
			
			int[][] graph = new int[N + 1][N + 1];
			for (int i = 0; i < M; i++) {
				String[] input = br.readLine().split(" ");
				int small = Integer.parseInt(input[0]);
				int big = Integer.parseInt(input[1]);
				// a < b
				// [a][b] a가 b보다 작다 1
				// [b][a] b가 a보다 크다 2
				graph[small][big] = 1;
				graph[big][small] = 2;
			}
			
			// 1번 학생부터 시작
			// 1번보다 작은 애들 탐색 -> 1번을 시작으로 쭉 작은 애들만 담기
			// 1번보다 큰 애들 탐색 -> 1번을 시작으로 쭉 큰 애들만 담기
			int result = 0;
			for (int i = 1; i <= N; i++) {
				// i보다 큰 애들
				int big = 0;
				Deque<Integer> queue = new ArrayDeque<>();
				queue.offer(i);
				boolean[] visited = new boolean[N + 1];
				visited[i] = true;
				while (!queue.isEmpty()) {
					int student = queue.poll();

					for (int j = 1; j <= N; j++) {
						if (visited[j]) continue;
						if (graph[student][j] == 1) {
							queue.offer(j);
							visited[j] = true;
							big++;
						}
					}
				}
				
				int small = 0;
				Deque<Integer> queue2 = new ArrayDeque<>();
				queue2.offer(i);
				boolean[] visited2 = new boolean[N + 1];
				visited2[i] = true;
				while (!queue2.isEmpty()) {
					int student = queue2.poll();
					
					for (int j = 1; j <= N; j++) {
						if (visited2[j]) continue;
						if (graph[student][j] == 2) {
							queue2.offer(j);
							visited2[j] = true;
							small++;
						}
					}
				}
				if (big + small + 1 == N) {
					result++;
				}
			}
			System.out.println("#" + t + " " + result);
		}
	}
}
