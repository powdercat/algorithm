import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int K = Integer.parseInt(input[1]);
			String values = br.readLine();
			int len = N / 4;
			PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
			for (int start = 0; start < N; start++) {
				if (start + len >= N) {
					String newVal = values.substring(start, N) + values.substring(0, len - (N - start));
					pq.offer(Integer.parseInt(newVal, 16));
				} else {
					String newVal = values.substring(start, start + len);
					pq.offer(Integer.parseInt(newVal, 16));
				}
			}
			
			int prev = -1;
			int k = 1;
			while (!pq.isEmpty()) {
				int num = pq.poll();
				if (prev == num) {
					continue;
				}
				prev = num;
				if (k == K) {
					break;
				}
				k++;
			}
			System.out.println("#" + t + " " + prev);
		}
	}
}
