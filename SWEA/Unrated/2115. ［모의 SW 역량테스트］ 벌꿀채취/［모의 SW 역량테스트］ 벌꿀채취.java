import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	static int N;
	static int M;
	static int C;
	static int[][] maxProfits;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T = Integer.parseInt(br.readLine());
    	for (int t = 1; t <= T; t++) {
    		String[] input_str = br.readLine().split(" ");
        	N = Integer.parseInt(input_str[0]);
        	M = Integer.parseInt(input_str[1]);
        	C = Integer.parseInt(input_str[2]);
        	
        	int[][] map = new int[N][N];
        	for (int i = 0; i < N; i++) {
    			String[] temp_str = br.readLine().split(" ");
    			for (int j = 0; j < N; j++) {
    				map[i][j] = Integer.parseInt(temp_str[j]);
    			}
        	}
        	maxProfits = new int[N][N];
        	// (i, j)에서 시작하는 M개 벌통의 최대 수익 계산
        	for (int i = 0; i < N; i++) {
        		for (int j = 0; j <= N - M; j++) {
        			// M개의 벌통의 벌꿀 정보 모으기
        			int[] honey = new int[M];
        			for (int k = 0; k < M; k++) {
        				honey[k] = map[i][j + k];
        			}
        			// 그 꿀통들을 대상으로 얻을 수 있는 최대 수익 구하기
        			maxProfits[i][j] = func(honey, 0, 0, 0);
        		}
        	}

        	int maxProfit = 0;
        	// 첫 일꾼이 구간을 선택한다
        	for (int i1 = 0; i1 < N; i1++) {
        		for (int j1 = 0; j1 <= N - M; j1++) {
        			
        			// 다음 일꾼이 구간을 선택한다
        			for (int i2 = i1 + 1; i2 < N; i2++) {
        				int j2 = 0;
        				if (i1 == i2) {
        					j2 = i1 + 1;
        				}
        				for (; j2 <= N - M; j2++) {
        					maxProfit = Math.max(maxProfit, maxProfits[i1][j1] + maxProfits[i2][j2]);
        				}
        			}
        		}
        	}
        	System.out.printf("#%d %d\n", t, maxProfit);
    	}
    	
    }
    
    public static int func(int[] honey, int index, int sum, int sumProfit) {
    	// index 꿀을 담을지 안 담을지 선택하기
    	
    	if (sum > C) { // 이미 이 선택은 안돼
    		return 0;
    	}
    	
    	if (index == M) { // 담을지말지 여부를 다 선택했어
    		return sumProfit;
    	}
    	
    	// 이번 꿀 담을게
    	int selectSum = func(honey, index + 1, sum + honey[index], sumProfit + honey[index] * honey[index]);
    	
    	// 이번 꿀 안 담을게
    	int notSelectSum = func(honey, index + 1, sum, sumProfit);
    	
    	return Math.max(selectSum, notSelectSum);
    }
}