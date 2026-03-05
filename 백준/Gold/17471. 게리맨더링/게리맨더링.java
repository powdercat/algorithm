import java.io.*;
import java.util.*;

public class Main {
	
	static boolean[] result;
	static int N, min, M, answer;
	static int[] people;
	static boolean[][] graph;

	public static void main(String[] args) throws Exception {
		
		/*
		 * 완전탐색 N 최대 10
		 * N개 중 1~N-1개 선택, Combination
		 * 고른 거랑, 그 외의 거랑 / 나눌 수 있는 게 맞는지 체크 - 어케 체크하지?
		 * 연결 여부 확인 - 묶음을 어딘가 저장해두고
		 * 예시 - 1234 / 56 -> 1이랑 연결된 것 - 4 2 넣고 1 제거, 4랑 연결된 것 - 3 넣고 4 제거, 2랑 연결된 것 - 1356 - 13은 이미 있었기에 안 넣음 - 56은 내꺼아니라서 안 넣음, 3랑 연결된 것 - 더 없음 제거, 다 제거됨
		 * 연결된 것 중에 내꺼가 있다면 제외, 내 묶음이 없다면 제외하지 않음
		 *  	56 -> 5랑 연결된 것 - 2 - 내 묶음 아니니까 5 제거 못함
		 *  	선거구가 두 개 이상인데 - 나랑 연결된 것 중에 내 묶음이 없다면 불가능한 방법
		 *  	나랑 연결된 것 중에 내 묶음이 하나라도 있다면 통과.
		 * 나눌 수 있다면 -> 각 구역 별 인구 수 계산하여 차이를 저장 -> 최솟값 구하기
		 * 
		 * N개 중 1~N/2개 선택
		 * 
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		people = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new boolean[N + 1][11];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int closeN = Integer.parseInt(st.nextToken());
			for (int j = 0; j < closeN; j++) {
				int close = Integer.parseInt(st.nextToken());
				graph[i][close] = true;
			}
		}
		M = (N + 1) / 2;
		
		result = new boolean[N + 1];
		min = Integer.MAX_VALUE;
		answer = -1;
		
		combination(1);
		System.out.println(answer);
	}

	private static void combination(int depth) {
		if (depth == N + 1) {
			int cnt = 0;
			int Astart = 0;
			int Bstart = 0;
			for (int i = 1; i <= N; i++) {
				if (result[i]) {
					cnt++;
					Astart = i;
				} else {
					Bstart = i;
				}
			}
			// 1개는 있어야해
			if (cnt == 0 || cnt == N) return;

			// result[1 ~ N] 에서 true 인 애 하나 큐에 담기
			// bfs - result[]가 true면서 연결된 애들 담기, 방문처리 - 방문한 갯수가 cnt랑 동일해야해
			Deque<Integer> Aqueue = new ArrayDeque<>();
			Aqueue.offer(Astart);
			boolean[] Avisited = new boolean[N + 1];
			while (!Aqueue.isEmpty()) {
				int a = Aqueue.poll();
				if (Avisited[a]) continue;
				Avisited[a] = true;
				boolean[] connects = graph[a];
				for (int i = 1; i <= N; i++) {
					if (connects[i] && result[i]) {
						Aqueue.offer(i);
					}
				}
			}
			int visitCnt = 0;
			for (boolean A : Avisited) {
				if (A) visitCnt++;
			}
			if (visitCnt != cnt) return;
			
			
			// 마찬가지로 result[]에서 false인 애들로도 돌아야함, N-cnt랑 동일해야 함
			Deque<Integer> Bqueue = new ArrayDeque<>();
			Bqueue.offer(Bstart);
			boolean[] Bvisited = new boolean[N + 1];
			while (!Bqueue.isEmpty()) {
				int b = Bqueue.poll();
				if (Bvisited[b]) continue;
				Bvisited[b] = true;
				boolean[] connects = graph[b];
				for (int i = 1; i <= N; i++) {
					if (connects[i] && !result[i]) {
						Bqueue.offer(i);
					}
				}
			}
			visitCnt = 0;
			for (boolean B : Bvisited) {
				if (B) visitCnt++;
			}
			if (visitCnt != N - cnt) return;

			int sum = 0;
			for (int i = 1; i <= N; i++) {
				if (result[i]) {
					sum += people[i];
				} else {
					sum -= people[i];
				}
			}
			
			
			min = Math.min(min, Math.abs(sum));
			answer = min;
			
			return;
		}
		
		// people 을 돌면서, 선택하거나 선택하지 않거나
		result[depth] = true;
		combination(depth + 1);
		result[depth] = false;
		combination(depth + 1);
	}
}
