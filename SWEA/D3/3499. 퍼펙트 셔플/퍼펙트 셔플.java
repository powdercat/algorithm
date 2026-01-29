import java.util.Arrays;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {

	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		StringBuffer sb = new StringBuffer();
         
        int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			int len = Integer.parseInt(br.readLine());
			String[] arr = br.readLine().split(" ");
			// 길이 구하고 반으로 자르기, A, B 길이의 배열 만들어서 하나씩 넣기
			// 반으로 자르고 각각에서 하나씩 빼서 넣기
			int halfLen = (len + 1) / 2;
			String[] arr1 = Arrays.copyOfRange(arr, 0, halfLen);
			String[] arr2 = Arrays.copyOfRange(arr, halfLen, len);
			
			Stack<String> stack = new Stack<>();
			sb.append("#" + test_case);
			for (int i = 0; i < halfLen; i++) {
				stack.push(arr1[i]);
				sb.append(" " + arr1[i]);
				if (i == halfLen - 1 && len % 2 == 1) {
					continue;
				}
				stack.push(arr2[i]);
				sb.append(" " + arr2[i]);
			}
			System.out.println(sb.toString());
			sb = new StringBuffer();
		}
	}
}
