import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int N;
	static int M;
	static int[] numbers;
	static int[] result;
	static boolean[] isSelected;
	static StringBuilder sb = new StringBuilder(); // sout으로 하면 시간초과
	
    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	String[] input = br.readLine().split(" ");
    	N = Integer.parseInt(input[0]);
    	M = Integer.parseInt(input[1]);
    	isSelected = new boolean[N];
    	result = new int[M];
		numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    	Arrays.sort(numbers);
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
    		if (isSelected[i]) continue;
    		isSelected[i] = true;
    		result[depth] = numbers[i];
    		permutation(depth + 1);
    		isSelected[i] = false;
    	}
    }
}