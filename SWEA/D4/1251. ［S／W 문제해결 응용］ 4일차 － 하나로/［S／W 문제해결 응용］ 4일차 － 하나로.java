import java.io.*;
import java.util.*;

public class Solution {
	
	static int[] parents;

    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= TC; tc++) {
    		int N = Integer.parseInt(br.readLine());
    		
    		int[] x = new int[N];
    		int[] y = new int[N];
    		String[] xInput = br.readLine().split(" ");
    		String[] yInput = br.readLine().split(" ");
    		for (int i = 0; i < N; i++) {
    			x[i] = Integer.parseInt(xInput[i]);
    			y[i] = Integer.parseInt(yInput[i]);
    		}
    		double E = Double.parseDouble(br.readLine());
    		
    		double[][] edges = new double[N * (N - 1)][3];
    		int k = 0;
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j < N; j++) {
    				if (i == j) continue;
    				double w = (Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2)) * E;
    				edges[k++] = new double[] {i, j, w};
    			}
    		}
    		
    		Arrays.sort(edges, (e1, e2) -> Double.compare(e1[2], e2[2]));
    		
    		parents = new int[N];
    		for (int i = 0; i < N; i++) {
    			parents[i] = -1;
    		}
    		
    		double result = 0;
    		int cnt = 0;
    		for (double[] edge : edges) {
    			int a = (int) edge[0];
    			int b = (int) edge[1];
    			double w = edge[2];
    			if (union(a, b)) {
    				result += w;
    				cnt++;
    				if (cnt == N - 1) break;
    			}
    			
    		}
    		System.out.println("#" + tc + " " + Math.round(result));
    	}
    }
    
    static int findRoot(int a) {
    	if (parents[a] < 0) return a;
    	return parents[a] = findRoot(parents[a]);
    }

	private static boolean union(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		if (aRoot == bRoot) return false;
		
		if (parents[aRoot] <= parents[bRoot]) {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		} else {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		}
		
		return true;
	}
}