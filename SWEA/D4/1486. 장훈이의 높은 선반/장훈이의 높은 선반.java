// SWEA

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	
	static int[] numbers;
	static int N, B, min;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= TC; tc++) {
    		String[] input = br.readLine().split(" ");
    		N = Integer.parseInt(input[0]);
    		B = Integer.parseInt(input[1]);
    		
    		numbers = new int[N];
    		min = Integer.MAX_VALUE;
    		
    		String[] input2 = br.readLine().split(" ");
    		for (int i = 0; i < N; i++) {
    			numbers[i] = Integer.parseInt(input2[i]);
    		}
    		// 부분집합
    		// 구한 값이 B랑 같거나 크면 최솟값과 비교
    		// 다 구했는데 B보다 작으면 무시
    		
    		permutation(0, 0);
    		System.out.println("#" + tc + " " + (min - B));
    	}
    }
    
    static void permutation(int idx, int sum) {
    	if (sum >= B) {
    		min = Math.min(min, sum);
    		return;
    	}
    	
    	if (idx >= N) {
    		return;
    	}
    	// numbers[idx] 값을 선택하는 경우
		permutation(idx + 1, sum + numbers[idx]);
		
    	// numbers[idx] 값을 선택하지 않는 경우
		permutation(idx + 1, sum);
    	
    }
}
