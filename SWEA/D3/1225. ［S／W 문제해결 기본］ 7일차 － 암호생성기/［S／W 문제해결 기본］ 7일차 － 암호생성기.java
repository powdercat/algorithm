
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {

	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		StringBuffer sb = new StringBuffer();
         
		for(int test_case = 1; test_case <= 10; test_case++) {
			br.readLine();
			String[] arr = br.readLine().split(" ");
			
			Queue<Integer> queue = new ArrayDeque<>();
			
			for (String a : arr) {
				queue.offer(Integer.parseInt(a));
			}
			int k = 1;
			while (true) {
				int val = queue.poll() - k;
				if (val <= 0) {
					queue.offer(0);
					break;
				}
				queue.offer(val);
				if (k == 5) {
					k = 1;
				} else {
					k++;
				}
			}
			
			sb.append("#" + test_case);
			for (Integer v : queue) {
				sb.append(" " + v);
			}
			System.out.println(sb.toString());

			sb = new StringBuffer();
		}
	}
}