import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			boolean[][] graph = new boolean[N + 1][N + 1];
			for (int i = 0; i < M; i++) {
				String[] input = br.readLine().split(" ");
				int a = Integer.parseInt(input[0]);
				int b = Integer.parseInt(input[1]);
				// a < b
				graph[a][b] = true;
			}
			
			// i < k 이고 k < j 면 i < j이다
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (graph[i][j]) continue;
						if (graph[i][k] && graph[k][j]) {
							graph[i][j] = true;
						}
					}
				}
			}
			// 나보다 키 큰 + 나보다 키 작은 = N - 1
			// true로만 검증 가능해야 함
			int result = 0;
			for (int i = 1; i <= N; i++) {
				int count = 0;
				for (int j = 1; j <= N; j++) {
					// i보다 키 큰
					if (graph[i][j]) count++;
					// i보다 작은
					if (graph[j][i]) count++;
				}
				if (count == N - 1) result++;
			}
			System.out.println("#" + t + " " + result);
		}
	}
}