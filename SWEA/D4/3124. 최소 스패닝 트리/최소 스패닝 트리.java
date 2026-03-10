import java.io.*;
import java.util.Arrays;

public class Solution {
	
	static int[] parents;

    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= TC; tc++) {
    		String[] input = br.readLine().split(" ");
    		int V = Integer.parseInt(input[0]);
    		int E = Integer.parseInt(input[1]);
    		
    		int[][] edges = new int[E][3];
    		for (int i = 0; i < E; i++) {
        		String[] input2 = br.readLine().split(" ");
        		int A = Integer.parseInt(input2[0]) - 1;
        		int B = Integer.parseInt(input2[1]) - 1;
    			int C = Integer.parseInt(input2[2]);
    			edges[i] = new int[] {A, B, C};
    		}
    		
    		parents = new int[V];
    		for (int p = 0; p < V; p++) {
    			parents[p] = -1;
    		}
    		
    		Arrays.sort(edges, (e1, e2) -> e1[2] - e2[2]);
    		
    		int count = 0;
    		long result = 0;
    		for (int[] edge : edges) {
    			int a = edge[0];
    			int b = edge[1];
    			int w = edge[2];
    			if (union(a, b)) {
    				result += w;
    				count++;
    				if (count == V - 1) {
    					break;
    				}
    			}
    		}
    		System.out.println("#" + tc + " " + result);
    		
    	}
    }
    
    static int findRoot(int a) {
    	if (parents[a] < 0) return a;
    	return parents[a] = findRoot(parents[a]);
    }
    
    static boolean union(int a, int b) {
    	int aRoot = findRoot(a);
    	int bRoot = findRoot(b);
    	if (aRoot == bRoot) return false;
    	
    	if (parents[aRoot] >= parents[bRoot]) {
    		parents[aRoot] += parents[bRoot];
    		parents[bRoot] = aRoot;
    	} else {
    		parents[bRoot] += parents[aRoot];
    		parents[aRoot] = bRoot;
    	}
    	return true;
    }
}