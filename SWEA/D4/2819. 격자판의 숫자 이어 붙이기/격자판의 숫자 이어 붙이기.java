import java.io.*;
import java.util.*;

public class Solution {

	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	static class Node {
		int x, y;
		String val;
		int count;
		
		public Node(int x, int y, String val, int count) {
			super();
			this.x = x;
			this.y = y;
			this.val = val;
			this.count = count;
		}
	}

	public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= TC; tc++) {
    		// bfs로 count하며 이동, 방문여부 필요없음, 7자리를 다 완성하면 hashmap에 넣기, 이미 있는거면 스킵
    		String[][] map = new String[4][4];
    		for (int i = 0; i < 4; i++) {
    			String[] input = br.readLine().split(" ");
    			for (int j = 0; j < 4; j++) {
    				map[i][j] = input[j];
    			}
    		}
    		
    		HashMap<String, Boolean> hash = new HashMap<>();
    		
    		for (int i = 0; i < 4; i++) {
    			for (int j = 0; j < 4; j++) {
    	    		// 임의의 점으로부터 시작
    				Deque<Node> queue = new ArrayDeque<>();
    				queue.offer(new Node(i, j, map[i][j], 1));
    				while (!queue.isEmpty()) {
    					Node cur = queue.poll();
    					int cx = cur.x;
    					int cy = cur.y;
    					String val = cur.val;
    					int count = cur.count;
    					if (count == 7) {
    						// hashmap에 넣기
    						if (hash.containsKey(val)) continue;
    						hash.put(val, true);
    						continue;
    					}
    					for (int dir = 0; dir < 4; dir++) {
    						int nx = cx + dx[dir];
    						int ny = cy + dy[dir];
    						if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
    						String newVal = val + map[nx][ny];
        					queue.offer(new Node(nx, ny, newVal, count + 1));
    					}
    				}
    			}
    		}

    		System.out.println("#" + tc + " " + hash.size());
    	}
    }
}