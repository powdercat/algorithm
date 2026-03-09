import java.io.*;

public class Main {
	
    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input = br.readLine().split(" ");
    	int N = Integer.parseInt(input[0]);
    	int K = Integer.parseInt(input[1]);
    	
    	int[] value = new int[N];
    	int k = N - 1;
    	for (int i = 0; i < N; i++) {
    		int A = Integer.parseInt(br.readLine());
    		value[i] = A;
    		if (A > K) {
    			k = i - 1;
    			break;
    		}
    	}
    	
    	int result = 0;
    	while (K > 0) {
    		result += K / value[k];
    		K = K % value[k--];
    	}
    	System.out.println(result);
	}
}