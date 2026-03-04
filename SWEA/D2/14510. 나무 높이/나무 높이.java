import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			int maxTall = 0;
			int N = Integer.parseInt(br.readLine());
			int[] talls = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				talls[i] = Integer.parseInt(st.nextToken());
				maxTall = Math.max(maxTall, talls[i]);
			}
			
			int needTall = 0;
			int odd = 0;
			for (int i = 0; i < N; i++) {
				int need = maxTall - talls[i];
				needTall += need;
				if (need % 2 == 1) odd++;
			}
			if (needTall == 0) {
				sb.append("#").append(tc).append(" ").append(0).append("\n");
				continue;
			}
			// 최소홀수일이 있으면 남은 짝수일이 나온다
			int even = (needTall - odd) / 2;
			// 짝수일을 -1하고, 홀수일을 +2 하면서 결과일을 구하는데 - 반복, 구한게 더 크면 앞에꺼가 최소임.
			int min = Math.max(odd * 2 - 1, even * 2);
			while (true) {
				even--;
				odd += 2;
				if (even < 0) break;
				int temp = Math.max(odd * 2 - 1, even * 2);
				if (temp > min) {
					break;
				}
				min = temp;
			}
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

}