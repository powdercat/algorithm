// SWEA

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	static int N, min;
	static int[][] synergy;
	static boolean[] used;
	
    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= TC; tc++) {
    		N = Integer.parseInt(br.readLine());
    		synergy = new int[N][N];
    		for (int i = 0; i < N; i++) {
    			synergy[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    		}
    		used = new boolean[N];
    		min = Integer.MAX_VALUE;
    		
    		combination(0, 0);
    		
    		System.out.println("#" + tc + " " + min);
    	}
    }
    
    static void combination(int idx, int start) {
    	if (min == 0) {
    		return;
    	}
    	if (idx == N / 2) {
    		// A, B 음식 정해졌으니 시너지 구하기
    		// used 배열 보면 됨
    		int[] A = new int[N /2];
    		int[] B = new int[N /2];
    		int a, b;
    		a = b = 0;
    		for (int i = 0; i < N; i++) {
    			if (used[i]) {
    				A[a++] = i;
    			} else {
    				B[b++] = i;
    			}
    		}
    		
    		int aSum = 0;
    		for (int i = 0; i < N / 2; i++) {
    			for (int j = i + 1; j < N / 2; j++) {
    				aSum += synergy[A[i]][A[j]] + synergy[A[j]][A[i]];
    			}
    		}
    		int bSum = 0;
    		for (int i = 0; i < N / 2; i++) {
    			for (int j = i + 1; j < N / 2; j++) {
    				bSum += synergy[B[i]][B[j]] + synergy[B[j]][B[i]];
    			}
    		}
    		
    		min = Math.min(min, Math.abs(aSum - bSum));
    		return;
    	}
    	
    	for (int i = start; i < N; i++) {
    		if (used[i]) continue;
    		used[i] = true;
    		combination(idx + 1, i + 1);
    		used[i] = false;
    	}
    }
}
