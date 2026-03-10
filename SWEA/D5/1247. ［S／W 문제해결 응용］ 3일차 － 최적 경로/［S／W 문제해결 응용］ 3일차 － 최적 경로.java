import java.io.*;

public class Solution {

	static int[] result;
	static int[][] coordinates;
	static int N, min;
	static boolean[] selected;

    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= TC; tc++) {
    		N = Integer.parseInt(br.readLine());
    		
    		coordinates = new int[N + 2][2];
    		result = new int[N + 2];
    		result[0] = 0; // 회사
    		result[N + 1] = 1; // 집
    		
    		String[] input = br.readLine().split(" ");
    		for (int i = 0; i < N + 2; i++) {
    			int x = Integer.parseInt(input[i * 2]);
    			int y = Integer.parseInt(input[i * 2 + 1]);
    			coordinates[i] = new int[]{x, y};
    		}
    		
    		selected = new boolean[N + 2];
    		min = Integer.MAX_VALUE;
    		permutation(1);
    		
    		System.out.println("#" + tc + " " + min);
    	}
	}
    
    static void permutation(int depth) {
    	if (depth == N + 1) {
    		int sum = 0;
    		int cx = coordinates[0][0];
    		int cy = coordinates[0][1];
    		for (int i = 1; i < N + 2; i++) {
        		int customer = result[i];
        		int nx = coordinates[customer][0];
        		int ny = coordinates[customer][1];
        		sum += (Math.abs(cx - nx) + Math.abs(cy - ny));
        		cx = nx;
        		cy = ny;
    		}
    		min = Math.min(min, sum);
    		return;
    	}
    	
    	for (int i = 2; i < N + 2; i++) {
    		if (selected[i]) continue;
    		result[depth] = i;
    		selected[i] = true;
    		permutation(depth + 1);
    		selected[i] = false;
    	}
    }
}