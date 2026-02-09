import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	static int N;
	static int[] operator;

    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T = Integer.parseInt(br.readLine());
    	for (int t = 1; t <= T; t++) {
        	N = Integer.parseInt(br.readLine());
        	
        	String[] operator_str = br.readLine().split(" ");
        	// 2 1 0 1
        	// + - * /
        	// 0 1 2 3
        	operator = new int[N - 1];
        	int j = 0;
        	for (int i = 0; i < 4; i++) {
        		int n = Integer.parseInt(operator_str[i]);
        		for (int k = 0; k < n; k++) {
        			operator[j++] = i;
        		}
        	}
        	
        	String[] numbers_str = br.readLine().split(" ");
        	int[] numbers = new int[N];
        	for (int i = 0; i < N; i++) {
        		numbers[i] = Integer.parseInt(numbers_str[i]);
        	}
        	
        	Arrays.sort(operator);

        	int max = Integer.MIN_VALUE;
        	int min = Integer.MAX_VALUE;
        	do {
            	int num = numbers[0];
        		for (int i = 0; i < N - 1; i++) {
            		int nextNum = numbers[i + 1];
    				int op = operator[i];
    				if (op == 0) {
    					num += nextNum;
    				} else if (op == 1) {
    					num -= nextNum;
    				} else if (op == 2) {
    					num *= nextNum;
    				} else {
    					num /= nextNum;
    				}
        		}
        		max = Math.max(max, num);
        		min = Math.min(min, num);
        	} while (np());
        	
        	System.out.printf("#%d %d\n", t, max - min);
    	}
    }
    
    private static boolean np() {
    	int opLen = N - 1;
    	int i = opLen - 1;
    	while (i > 0 && operator[i - 1] >= operator[i]) { // 앞이 나보다 작으면 멈춤, 내 인덱스 = i
    		i--;
    	}
    	// i 부터 끝까지 내림차순 이라는 뜻
    	if (i == 0) {
    		return false; // 다음 순열 없음
    	}
    	
    	// operator[i - 1] 보다 큰 수들 중에서 가장 작은 수 찾기
    	int j = opLen - 1;
    	while (operator[i - 1] >= operator[j]) {
    		j--;
    	}
    	
    	swap(i - 1, j);
    	
    	// i 부터 끝까지 오름차순으로 정렬하기
    	int k = opLen - 1;
    	while (i < k) {
    		swap(i, k);
    		i++;
    		k--;
    	}
    	return true;
    	
    }
    private static void swap(int i, int j) {
    	int temp = operator[i];
    	operator[i] = operator[j];
    	operator[j] = temp;
    }
    
}