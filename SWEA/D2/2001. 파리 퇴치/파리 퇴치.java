import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
        	String[] input = br.readLine().split(" ");
        	int N = Integer.parseInt(input[0]);
        	int M = Integer.parseInt(input[1]);
        	
        	int[][] matrix = new int[N][N];
        	for (int i = 0; i < N; i++) {
        		String[] temp = br.readLine().split(" ");
        		for (int j = 0; j < N; j++) {
        			matrix[i][j] = Integer.parseInt(temp[j]);
        		}
        	}
        	
        	int[][] prefix = new int[N][N]; // 0,0 부터 i, j 까지의 누적 합
        	for (int i = 0; i < N; i++) {
        		for (int j = 0; j < N; j++) {
        			prefix[i][j] = ((i >= 1) ? prefix[i - 1][j] : 0) + ((j >= 1) ? prefix[i][j - 1] : 0)
        					- ((i >= 1 && j >= 1) ? prefix[i - 1][j - 1] : 0) + matrix[i][j];
        		}
    		}
        	
        	int max = 0;
        	for (int i = 0; i <= N - M; i++) {
        		for (int j = 0; j <= N - M; j++) {
        			int sum = prefix[i + M - 1][j + M - 1] - ((i >= 1) ? prefix[i - 1][j + M - 1] : 0)
        					- ((j >= 1) ? prefix[i + M - 1][j - 1] : 0) + ((i >= 1 && j >= 1) ? prefix[i - 1][j - 1]: 0);
        			max = Math.max(max, sum);
        		}
        	}
        	System.out.println("#" + test_case + " " + max);
        }
    }
 
}