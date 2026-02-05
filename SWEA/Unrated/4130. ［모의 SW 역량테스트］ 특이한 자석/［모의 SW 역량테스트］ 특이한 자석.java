import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static int[][] Magnets = new int[5][8];
    static boolean[] visited = new boolean[5];
    public static void main(String[] args) throws Exception {
//    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
        	int K = Integer.parseInt(br.readLine());
        	// 4개의 줄에 자성 정보
        	for (int i = 1; i <= 4; i++) {
        		String[] input_str = br.readLine().split(" ");
        		
        		int[] info = new int[8];
        		for (int j = 0; j < 8; j++) {
        			info[j] = Integer.parseInt(input_str[j]);
        		}
        		Magnets[i] = info;
        	}

        	// K개의 줄에 자석 회전 정보 [번호, 회전방향]
        	for (int k = 0; k < K; k++) {
            	Arrays.fill(visited, false);
        		String[] input_str = br.readLine().split(" ");
        		int target = Integer.parseInt(input_str[0]);
        		int dir = Integer.parseInt(input_str[1]);
        		
        		rotate(target, dir);
        	}
        	int sum = 0;
        	for (int i = 0; i < 4; i++) {
        		if (Magnets[i + 1][0] == 1) {
        			sum += Math.pow(2, i);
        		}
        	}
        	System.out.printf("#%d %d\n", test_case, sum);
        }
        
    }
    private static void rotate(int target, int dir) {
    	if (visited[target]) return;
		visited[target] = true;
		int targetVal2 = Magnets[target][2];
		int targetVal6 = Magnets[target][6];
		
    	// target이 dir로 도는데 전파가능인가?
    	if (target - 1 >= 1) {
    		// 왼쪽 자석이 있음
    		if (Magnets[target - 1][2] != targetVal6) { // 돌려야하나?
    			rotate(target - 1, dir * -1);
    		}
    	}
    	if (target + 1 <= 4) {
    		// 오른쪽에 자석이 있음
    		if (targetVal2 != Magnets[target + 1][6]) { // 돌려야하나?
    			rotate(target + 1, dir * -1);
    		}
		}
        
    	// 회전 실행
        if (dir == 1) { // 오른쪽
    		int temp = 0;
    		int temp2 = Magnets[target][0];
    		for (int i = 0; i < 7; i++) {
    			temp = Magnets[target][i + 1];
    			Magnets[target][i + 1] = temp2;
    			temp2 = temp;
    		}
    		Magnets[target][0] = temp2;
		} else { // 왼쪽
			int temp = 0;
			int temp2 = Magnets[target][7];
			for (int i = 7; i > 0; i--) {
				temp = Magnets[target][i - 1];
				Magnets[target][i - 1] = temp2;
				temp2 = temp;
			}
			Magnets[target][7] = temp2;
		}
    }
}