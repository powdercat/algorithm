import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	static int[] permut;
	static int[] numbers;
	static boolean[] used;
	static int N;
	static int M;
	
	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input_str = br.readLine().split(" ");
    	N = Integer.parseInt(input_str[0]);
    	M = Integer.parseInt(input_str[1]);
    	used = new boolean[N];
    	numbers = new int[N];
    	for (int i = 0; i < N; i++) {
    		numbers[i] = i + 1;
    	}
    	permut = new int[M];
    	
    	permutation(0);
    	System.out.println(sb);
    }
    
    private static void permutation(int cnt) {
    	if (cnt == M) {
    		for (int p : permut) {
    			sb.append(p).append(" ");
    		}
    		sb.append("\n");
    		return;
    	}
    	
    	for (int i = 0; i < N; i++) {
    		if (used[i]) {
    			continue;
    		}
    		
    		permut[cnt] = numbers[i];
    		used[i] = true;
    		permutation(cnt + 1);
    		used[i] = false;
    	}
    }
}