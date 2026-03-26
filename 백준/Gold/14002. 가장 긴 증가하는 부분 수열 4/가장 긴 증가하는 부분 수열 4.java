import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// dp 0~N까지 전부 초기 길이는 1
		int N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		int[] A = new int[N];
		int[] dp = new int[N];
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(input[i]);
			dp[i] = 1;
		}
		if (N == 1) {
			System.out.println(1);
			System.out.println(A[0]);
			System.exit(0);
		}

		int count = 0;
		int maxIdx = -1;
		for (int i = 1; i < N; i++) {
			int prevMaxDp = -1;
			for (int j = 0; j < i; j++) {
				//
				if (A[j] < A[i]) {
					prevMaxDp = Math.max(prevMaxDp, dp[j]);
				}
			}
			if (prevMaxDp != -1) {
				dp[i] = prevMaxDp + 1;
			}
			if (dp[i] > count) {
				count = dp[i];
				maxIdx = i;
			}
		}
		System.out.println(count);
		
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(A[maxIdx]);
		count--;
		maxIdx--;
		while (count > 0) {
			if (count == dp[maxIdx] && A[maxIdx] < stack.peek()) {
				stack.push(A[maxIdx]);
				count--;
			}
			maxIdx--;
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}
}
