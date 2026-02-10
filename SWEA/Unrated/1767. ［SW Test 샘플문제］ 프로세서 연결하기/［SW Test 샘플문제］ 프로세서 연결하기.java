import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	static int N, max, totalCnt, min;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static ArrayList<int[]> list; // 실제 연결해야하는 코어의 리스트, 코어의 행렬 위치값, 몇 개 들어올지 모르니 ArrayList
	static int[] used = new int[13];
	// 1. 연결해야하는 코어 리스트 찾기
	// 		-비 가장자리 코어 리스트 생성
	// 2. 완탐 생각
	// 		-코어 1개만 연결, 2개만 연결, 3개만 연결 ...
	// 3. 코어에 전선 놓기
	// 		- 4방향 시도
	// 			코어를 선택하느냐 안 선택하느냐
	// 4. 가지치기
	// 		교차 불가
	// 		남은 코어를 다 연결해도 임시 최대 코어 갯수보다 작은 경우
	
	 public static void main(String[] args) throws Exception {
	 	
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	
	 	int T = Integer.parseInt(br.readLine());
	 	for (int t = 1; t <= T; t++) {
	     	N = Integer.parseInt(br.readLine());
	     	map = new int[N][N];
	     	list = new ArrayList<>();
	     	max = 0;
	     	min = Integer.MAX_VALUE;
	     	totalCnt = 0; // 가장자리가 아닌 코어수
	     	for (int i = 0; i < N; i++) {
        		String[] input_str = br.readLine().split(" ");
        		for (int j = 0; j < N; j++) {
        			map[i][j] = Integer.parseInt(input_str[j]);
        			if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
        				continue;
        			}
        			if (map[i][j] == 1) { // 가장자리 코어가 아님
        				list.add(new int[] {i, j});
        			}
        		}
        	}
	     	totalCnt = list.size();
	     	setPower(0, 0, 0);
	     	
	     	System.out.printf("#%d %d\n", t, min);
	 	}
	 }
	 
	// 처리해야하는 코어의 인덱스, 놓은 코어 개수, 전선 개수
	 static void setPower(int index, int coreCnt, int lineCnt) {
		 // 남은 코어 수 + 지금까지 놓은 코어의 수 < 임시 최대 코어 개수
		 if (totalCnt - index + coreCnt < max) {
			 return;
		 }
		 if (index == totalCnt) {
			 // 코어의 최대값
			 // 전선의 최소길이
			 if (max < coreCnt) {
				 max = coreCnt;
				 min = lineCnt;
			 } else if (max == coreCnt) {
				 min = Math.min(min, lineCnt);
			 }
			 
			 return;
		 }
		 
		 int[] core = list.get(index);
		 int r = core[0];
		 int c = core[1];
		 // 해당 코어를 4방향으로 전선 놓기 시도
		 for (int d = 0; d < 4; d++) {
			 // 해당 코어를 d 방향으로 놓는 것이 가능한지 체크
			 if (!isAvailable(r, c, d)) continue;
			 
			 // 해당 코어를 d방향으로 전선 놓기
			 int nr = r;
			 int nc = c;
			 int cnt = 0; // 놓아진 전선의 길이
			 while (true) {
				 nr += dr[d];
				 nc += dc[d];
				 if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
					 break;
				 }
				 map[nr][nc] = 2;
				 cnt++;
			 }
			 
			 // 다음 코어로 넘어가기
			 setPower(index + 1, coreCnt + 1, lineCnt + cnt);
			 
			 nr = r;
			 nc = c;
			 // 해당 코어를 d방향으로 놓은 전선 지우기
			 while (true) {
				 nr += dr[d];
				 nc += dc[d];
				 if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
					 break;
				 }
				 map[nr][nc] = 0;
			 }
			 
		 }
		 
		 // 해당 코어를 전선 놓기 하지 않기
		 setPower(index + 1, coreCnt, lineCnt);
	 }

	private static boolean isAvailable(int r, int c, int d) {
		// [r][c]가 d 방향으로 쭉 갈 수 있는가?
		while (true) {
			r += dr[d];
			c += dc[d];
			if (r < 0 || r >= N || c < 0 || c >= N) {
				return true;
			}
			if (map[r][c] == 1 || map[r][c] == 2) {
				return false;
			}
		}
	}
}
