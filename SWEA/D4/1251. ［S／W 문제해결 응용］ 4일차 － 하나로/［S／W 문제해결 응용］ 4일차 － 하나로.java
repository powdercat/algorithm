import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= TC; tc++) {
    		int N = Integer.parseInt(br.readLine());
    		boolean[] visited = new boolean[N];
    		
    		int[] x = new int[N];
    		int[] y = new int[N];
    		String[] xInput = br.readLine().split(" ");
    		String[] yInput = br.readLine().split(" ");
    		for (int i = 0; i < N; i++) {
    			x[i] = Integer.parseInt(xInput[i]);
    			y[i] = Integer.parseInt(yInput[i]);
    		}
    		double E = Double.parseDouble(br.readLine());

    		// 정점 i -> j, j -> i 들의 연결 간선 정보 저장
    		double[][] adjMatrix = new double[N][N];
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j < N; j++) {
    				if (i == j) continue;
    				double w = (Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2)) * E;
    				adjMatrix[i][j] = w;
    			}
    		}
    		
    		double[] minEdge = new double[N];
    		Arrays.fill(minEdge, Double.MAX_VALUE);
    		minEdge[0] = 0;
    		
    		double result = 0;
    		for (int v = 0; v < N; v++) { // 정점 수 만큼 정점을 선택하기
    			double min = Double.MAX_VALUE;
    			int minVertex = -1;
    			
    			// 현재의 MST와 연결할 수 있는 정점 중 간선 비용이 최소인 정점 찾기
    			for (int i = 0; i < N; i++) {
    				if (!visited[i] && min > minEdge[i]) {
    					min = minEdge[i];
    					minVertex = i;
    				}
    			}
    			
    			if (minVertex == -1) {
    				break;
    			}
    			
    			visited[minVertex] = true;
    			result += min;
    			
    			// minVertex를 MST에 연결하며, minVertex와 연결된 비트리정점의 정보를 활용해 minEdge 갱신
    			for (int i = 0; i < N; i++) {
    				if (visited[i]) continue;
    				if (adjMatrix[minVertex][i] == 0) continue;
    				if (minEdge[i] > adjMatrix[minVertex][i]) {
    					minEdge[i] = adjMatrix[minVertex][i];
    				}
    			}
    		}
    		System.out.println("#" + tc + " " + Math.round(result));
    	}
    }
}