
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int[] scores;
	static int[] cals;
	static boolean[] selected;
	static int maxScore;
	static int N;
	static int L;

    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T = Integer.parseInt(br.readLine());
    	for (int t = 1; t <= T; t++) {
    		String[] input_str = br.readLine().split(" ");
    		N = Integer.parseInt(input_str[0]);
    		L = Integer.parseInt(input_str[1]);
    		
        	scores = new int[N];
        	cals = new int[N];
        	selected = new boolean[N];
    		
    		for (int i = 0; i < N; i++) {
        		input_str = br.readLine().split(" ");
    			scores[i] = Integer.parseInt(input_str[0]);
    			cals[i] = Integer.parseInt(input_str[1]);
    		}
    		maxScore = 0;
    		func(0, 0, 0);
    		
    		System.out.printf("#%d %d\n", t, maxScore);
    	}
    }
    
    private static void func(int ind, int sumScore, int sumCal) {
    	if (sumCal > L) {
    		return;
    	}
    	if (ind == N) {
    		maxScore = Math.max(maxScore, sumScore);
    		return;
    	}
    	func(ind + 1, sumScore + scores[ind], sumCal + cals[ind]);
    	func(ind + 1, sumScore, sumCal);
    }
}