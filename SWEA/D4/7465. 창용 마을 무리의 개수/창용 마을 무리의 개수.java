import java.io.*;

public class Solution {
	
	static int N, M;
	static int[] parents;

    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= TC; tc++) {
    		sb.append("#").append(tc).append(" ");
    		String[] input = br.readLine().split(" ");
        	N = Integer.parseInt(input[0]);
        	M = Integer.parseInt(input[1]);
        	parents = new int[N + 1];
        	
        	for (int i = 1; i <= N; i++) {
        		parents[i] = i;
        	}
        	
        	for (int i = 0; i < M; i++) {
        		String[] input2 = br.readLine().split(" ");
        		int a = Integer.parseInt(input2[0]);
        		int b = Integer.parseInt(input2[1]);
        		union(a, b);
        	}
        	
        	int cnt = 0;
        	for (int i = 1; i <= N; i++) {
        		if (i == findSet(i)) {
        			cnt++;
        		}
        	}
        	
        	sb.append(cnt).append("\n");
    	}
    	System.out.println(sb);
    }
    
    public static int findSet(int a) {
    	if (a == parents[a]) {
    		return a;
    	}
    	return parents[a] = findSet(parents[a]);
    }
    
    public static boolean union(int a, int b) {
    	int aRoot = findSet(a);
    	int bRoot = findSet(b);
    	
    	if (aRoot == bRoot) {
    		return false;
    	}
    	parents[bRoot] = aRoot;
    	return true;
    }
}