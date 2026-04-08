import java.io.*;
import java.util.*;

public class Solution {
	
	static int[][] map;
	static int N, P, min;
	static boolean[] selected;
	static int[][] exit = new int[2][3];
	static int[][] loc = new int[10][2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			P = 0;
			map = new int[N][N];
			int e = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 1) {
						exit[e++] = new int[] {i, j, map[i][j]};
					}
					if (map[i][j] == 1) {
						loc[P++] = new int[] {i, j};
					}
				}
			}
			selected = new boolean[P];
			min = Integer.MAX_VALUE;
			
			// P명을 선택하거나 선택하지 않거나
			func(0, 0);
			System.out.println("#" + t + " " + min);
			
		}
	}
	static void func(int depth, int trueCnt) {
		if (depth == P) {
			// 계산하기
			int[] exit1 = exit[0];
			int K1 = exit1[2];
			int[] exit2 = exit[1];
			int K2 = exit2[2];
			int[] arrive1 = new int[trueCnt];
			int[] start1 = new int[trueCnt];
			int[] end1 = new int[trueCnt];
			
			int[] arrive2 = new int[P - trueCnt];
			int[] start2 = new int[P - trueCnt];
			int[] end2 = new int[P - trueCnt];
			int a = 0, b = 0;
			for (int i = 0; i < P; i++) {
				int[] person = loc[i];
				if (selected[i]) {
					int distance = Math.abs(exit1[0] - person[0]) + Math.abs(exit1[1] - person[1]);
					arrive1[a++] = distance;
				} else {
					int distance = Math.abs(exit2[0] - person[0]) + Math.abs(exit2[1] - person[1]);
					arrive2[b++] = distance;
				}
			}
			Arrays.sort(arrive1);
			for (int i = 0; i < trueCnt; i++) {
				if (i < 3) {
					start1[i] = arrive1[i] + 1;
					end1[i] = start1[i] + K1;
					continue;
				}
				start1[i] = Math.max(arrive1[i] + 1, end1[i - 3]);
				end1[i] = start1[i] + K1;
			}
			Arrays.sort(arrive2);
			for (int i = 0; i < P - trueCnt; i++) {
				if (i < 3) {
					start2[i] = arrive2[i] + 1;
					end2[i] = start2[i] + K2;
					continue;
				}
				start2[i] = Math.max(arrive2[i] + 1, end2[i - 3]);
				end2[i] = start2[i] + K2;
			}
			int time = 0;
			if (trueCnt == 0) time = end2[P - trueCnt - 1];
			else if (trueCnt == P) time = end1[trueCnt - 1];
			else time = Math.max(end1[trueCnt - 1], end2[P - trueCnt - 1]);
			min = Math.min(min, time);
			
			return;
		}
		
		selected[depth] = true;
		func(depth + 1, trueCnt + 1);
		selected[depth] = false;
		func(depth + 1, trueCnt);
	}
}