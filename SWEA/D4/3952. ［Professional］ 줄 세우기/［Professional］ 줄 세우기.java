import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {
	
    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= TC; tc++) {
    		String[] input = br.readLine().split(" ");
    		int N = Integer.parseInt(input[0]);
    		int M = Integer.parseInt(input[1]);
    		
    		List<List<Integer>> graph = new ArrayList<>();
    		for (int i = 0; i <= N; i++) {
    			graph.add(new ArrayList<>());
    		}
    		int[] inDegree = new int[N + 1];

    		for (int i = 0; i < M; i++) {
    			String[] input2 = br.readLine().split(" ");
    			int out = Integer.parseInt(input2[0]);
    			int in = Integer.parseInt(input2[1]);
    			graph.get(out).add(in);
    			inDegree[in]++;
    		}
    		
    		// inDegree가 0인 in 찾기
    		Deque<Integer> queue = new ArrayDeque<>();
    		for (int i = 1; i <= N; i++) {
    			if (inDegree[i] == 0) {
    				queue.offer(i);
    			}
    		}
    		
    		List<Integer> result = new ArrayList<>();
    		// inDegree가 0인 in 지워가기
    		while (!queue.isEmpty()) {
    			int out = queue.poll();
    			result.add(out);
    			
    			for (Integer in : graph.get(out)) {
    				// out 하나 없어졌기에 그 in의 진입차수 -1
    				inDegree[in]--;
    				
    				// 지울 수 있는 in이면 queue에 넣기
    				if (inDegree[in] == 0) {
    					queue.offer(in);
    				}
    			}
    		}
    		
    		sb.append("#").append(tc).append(" ");
    		for (int i = 0; i < result.size(); i++) {
    			sb.append(result.get(i)).append(" ");
    		}
    		sb.append("\n");
    	}
    	System.out.println(sb);
    }
}