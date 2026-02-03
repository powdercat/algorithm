import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
        	String[] input = br.readLine().split(" ");
        	int N = Integer.parseInt(input[0]);
        	int M = Integer.parseInt(input[1]);
        	
        	int[] weights = new int[N];
        	String[] weights_str = br.readLine().split(" ");
        	for (int i = 0; i < N; i++) {
        		weights[i] = Integer.parseInt(weights_str[i]);
        	}
        	Arrays.sort(weights);
        	
        	int result = 0;
        	if (weights[0] + weights[1] > M) {
        		result = -1;
        	} else if (weights[N - 2] + weights[N - 1] <= M) {
        		result = (weights[N - 2] + weights[N - 1]);
        	} else {
                int front = 0;
                int back = N - 1;
                while (front < back) {
            		int frontValue = weights[front];
            		int backValue = weights[back];
                	int sum = frontValue + backValue;
                	if (sum == M) {
                		result = M;
                    	break;
                	} else if (sum < M) {
                		if (result < sum) {
                			result = sum;
                		}
                		front++;
                	} else {
                		back--;
                	}
                }
        	}
        	
        	
        	System.out.println("#" + test_case + " " + result);
        }
    }
}