import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
    		String[] input_str = br.readLine().split(" ");
    		
    		int aLen = input_str[0].length();
    		int bLen = input_str[1].length();
    		int len = Math.max(aLen, bLen);
    		
    		int[] A = new int[len];
    		int[] B = new int[len];
    		
    		for (int i = len - aLen; i < len; i++) {
    			A[i] = input_str[0].charAt(i - (len - aLen)) - '0';
    		}
    		for (int i = len - bLen; i < len; i++) {
    			int temp = input_str[1].charAt(i - (len - bLen)) - '0';
    			B[i] = temp;
    		}
    		boolean up = false;
    		int[] result = new int[len];
    		for (int i = len - 1; i >= 0; i--) {
    			int sum = A[i] + B[i];
    			if (up) {
    				sum += 1;
    			}
    			if (sum >= 10) {
    				sum -= 10;
    				up = true;
    			} else {
    				up = false;
    			}
    			result[i] = sum;
    		}
    		StringBuilder sb = new StringBuilder();
    		if (up) sb.append("1");
    		for (int i = 0; i < len; i++) {
    			sb.append("" + result[i]);
    		}
    		
        	System.out.println("#" + test_case + " "+ sb.toString());
        }
    }
}