import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, W, H, max, bombCnt;
	static int[] result;
	static int[][] map;
	static int[][] tempMap;
	
    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= TC; tc++) {
    		String[] input = br.readLine().split(" ");
    		
    		N = Integer.parseInt(input[0]);
    		W = Integer.parseInt(input[1]);
    		H = Integer.parseInt(input[2]);
    		
    		result = new int[N];
    		map = new int[H][W];
			tempMap = new int[H][W];
			
			int total = 0;
    		for (int i = 0; i < H; i++) {
    			String[] input2 = br.readLine().split(" ");
    			for (int j = 0; j < W; j++) {
    				map[i][j] = Integer.parseInt(input2[j]);
    				if (map[i][j] > 0) total++;
    			}
    		}
    		
    		max = Integer.MIN_VALUE;
    		permutation(0);
    		int answer = total - max;
    		if (max == Integer.MIN_VALUE) {
    			answer = 0;
    		}
    		
    		System.out.println("#" + tc + " " + answer);
    	}
    }

	private static void permutation(int depth) {
		if (depth == N) {
    		for (int i = 0; i < H; i++) {
    			for (int j = 0; j < W; j++) {
    				tempMap[i][j] = map[i][j];
    			}
    		}
			// result에 담긴대로 떨어뜨리기
    		bombCnt = 0;
    		for (int n = 0; n < N; n++) {
    			int w = result[n];
    			boolean exist = false;
    			int i, power = 0;
    			for (i = 0; i < H; i++) {
    				power = tempMap[i][w];
    				if (power != 0) { // 값이 있음
    					exist = true;
    					break;
    				}
    			}
    			if (!exist) {
    				return;
    			}
				bombCnt++;
				tempMap[i][w] = 0;
				
    			if (power == 1) {
    				continue;
    			}

				remove(i, w, power);
				down();
    		}
    		
    		max = Math.max(max, bombCnt);
    		
			return;
		}
		
		for (int i = 0; i < W; i++) {
			result[depth] = i;
			permutation(depth + 1);
		}
	}

	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

	private static void remove(int x, int y, int power) {
//		tempMap[x][y] = 0;
		// 이미 그 값은 0이고, 카운트도 됨
		if (power == 1) return;
		for (int dir = 0; dir < 4; dir++) {
			int cx = x;
			int cy = y;
			for (int p = 1; p < power; p++) {
				int nx = cx + dx[dir] * p;
				int ny = cy + dy[dir] * p;
				if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
					continue;
				}
				if (tempMap[nx][ny] == 0) continue;
				int newPower = tempMap[nx][ny];
				tempMap[nx][ny] = 0;
				bombCnt++;
				remove(nx, ny, newPower);
			}
		}
	}
	
	private static void down() {
		// 아래부터 탐색, 비어있는 칸 인덱스 저장, 쭉 올라가기, 올라가다가 값이 있으면 그 값을 비어있는 칸 인덱스에 저장하고 현재 값 0으로 바꾸기, 0까지 반복
		for (int j = 0; j < W; j++) {
			int emptyH = -1;
			// 비어있는 칸 찾기
			for (int h = H - 1; h >= 0; h--) {
				if (tempMap[h][j] == 0) {
					emptyH = h;
					break;
				}
			}
			if (emptyH == -1 || emptyH == 0) continue; // 해당 w는 다 채워져있거나 맨 위만 비어있음
			// 값이 있는 경우 값 찾기
			int targetH = emptyH;
			for (int h = emptyH - 1; h >= 0; h--) {
				if (tempMap[h][j] > 0) {
					tempMap[targetH--][j] = tempMap[h][j];
					tempMap[h][j] = 0;
				}
				if (targetH < 0) break;
			}
		}
	}
}