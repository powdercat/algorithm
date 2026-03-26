import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		
		int[] A = new int[N];
		int[] dp = new int[N];
		int[] prev = new int[N];
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(input[i]);
		}
		Arrays.fill(dp, 1);
		Arrays.fill(prev, -1);
		
		if (N == 1) {
			System.out.println(1);
			System.out.println(A[0]);
			System.exit(0);
		}

		int count = 1;
		int maxIdx = 0;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (A[j] < A[i] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
					prev[i] = j;
				}
			}
			if (dp[i] > count) {
				count = dp[i];
				maxIdx = i;
			}
		}
		System.out.println(count);
		
		Deque<Integer> stack = new ArrayDeque<>();
		while (count > 0) {
			stack.push(A[maxIdx]);
			maxIdx = prev[maxIdx];
			count--;
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}
}
