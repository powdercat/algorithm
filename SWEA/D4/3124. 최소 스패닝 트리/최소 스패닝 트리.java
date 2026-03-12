import java.io.*;
import java.util.*;

public class Solution {
	
	static class Node {
		int vertex, weight;
		Node next;
		
		public Node(int vertex, int weight, Solution.Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= TC; tc++) {
    		String[] input = br.readLine().split(" ");
    		int V = Integer.parseInt(input[0]);
    		int E = Integer.parseInt(input[1]);
    		boolean[] visited = new boolean[V];
    		Node[] adjList = new Node[V];
    		
    		for (int i = 0; i < E; i++) {
        		String[] input2 = br.readLine().split(" ");
        		int from = Integer.parseInt(input2[0]) - 1;
        		int to = Integer.parseInt(input2[1]) - 1;
    			int w = Integer.parseInt(input2[2]);
    			adjList[from] = new Node(to, w, adjList[from]);
    			adjList[to] = new Node(from, w, adjList[to]);
			}
    		
    		// {가중치, 정점} 오름차순
    		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    		pq.offer(new int[] {0, 0});
    		
    		long result = 0;
    		int count = 0;
    		
    		while (!pq.isEmpty() && count < V) {
    			int[] cur = pq.poll();
    			int weight = cur[0];
    			int minVertex = cur[1];
    			
    			if (visited[minVertex]) continue;
    			
    			visited[minVertex] = true;
    			result += weight;
    			count++;
    			
    			for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
    				if (visited[temp.vertex]) continue;
    				pq.offer(new int[] {temp.weight, temp.vertex});
    			}
    		}
    		
    		System.out.println("#" + tc + " " + result);
    	}
    }
}