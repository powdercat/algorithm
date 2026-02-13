import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int M;
	static int[] numbers;
	static int[] result;
	static StringBuilder sb = new StringBuilder(); // sout으로 하면 시간초과
	
    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	String[] input = br.readLine().split(" ");
    	N = Integer.parseInt(input[0]);
    	M = Integer.parseInt(input[1]);
    	
    	numbers = new int[N + 1];
    	result = new int[M];
    	for (int i = 0; i < N; i++) {
    		numbers[i] = i + 1;
    	}
    	permutation(0);
    	
    	System.out.println(sb);
    }
    
    static void permutation(int depth) {
    	if (depth == M) {
    		for (int i = 0; i < M; i++) {
    			sb.append(result[i]).append(" ");
    		}
    		sb.append("\n");
    		return;
    	}
    	for (int i = 0; i < N; i++) {
    		result[depth] = numbers[i];
    		permutation(depth + 1);
    	}
    }
}