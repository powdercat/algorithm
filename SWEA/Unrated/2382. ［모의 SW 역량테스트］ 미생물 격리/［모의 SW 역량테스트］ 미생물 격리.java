import java.io.*;
import java.util.*;

public class Solution {
	
	// 상하좌우
	static int[] dx = {-100, -1, 1, 0, 0}, dy = {-100, 0, 0, -1, 1};
	
    public static void main(String[] args) throws Exception {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = null;
    	
    	int TC = Integer.parseInt(br.readLine());
    	for (int tc = 1; tc <= TC; tc++) {
    		st = new StringTokenizer(br.readLine());
    		int N = Integer.parseInt(st.nextToken());
    		int M = Integer.parseInt(st.nextToken());
    		int K = Integer.parseInt(st.nextToken());
    		
    		int[][] cntMap = new int[N][N];
    		int[][] dirMap = new int[N][N];
    		Deque<int[]> queue = new ArrayDeque<>();
    		for (int k = 0; k < K; k++) {
        		st = new StringTokenizer(br.readLine());
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		int cnt = Integer.parseInt(st.nextToken());
        		int dir = Integer.parseInt(st.nextToken()); // 상하좌우
        		cntMap[x][y] = cnt;
        		dirMap[x][y] = dir;
        		queue.offer(new int[] {x, y});
    		}
    		// 이 위치에 있는 애의 개수, 방향 저장
    		
    		// 이동(temp 활용하기)
    		// 가장자리: 절반 죽음(0이면 사라짐, 리셋하고 큐에 넣지 않음) + 방향 반대 -> 새로운 큐, temp맵에 넣기
    		// temp에 들어가려는데 이미 값이 있다면? 기존꺼랑 지금꺼 크기 비교 -> 갯수 합하고큰거의 방향 남기기, 작은거 값 0으로 리셋
    		// tempMap[x][y]
    		for (int m = 0; m < M; m++) {
        		int[][] cntMapTemp = new int[N][N];
        		int[][] dirMapTemp = new int[N][N];
        		HashMap<List<Integer>, List<Integer>> mix = new HashMap<>();
    			while (!queue.isEmpty()) {
    				int[] cur = queue.poll();
    				int cx = cur[0];
    				int cy = cur[1];
    				int cnt = cntMap[cx][cy];
    				int dir = dirMap[cx][cy];
    				cntMap[cx][cy] = 0;
    				dirMap[cx][cy] = 0;
    				// 이동
    				int nx = cx + dx[dir];
    				int ny = cy + dy[dir];
    				if (nx == 0 || nx == N - 1 || ny == 0 || ny == N - 1) {
    					// 가장자리 처리
    					cnt /= 2;
    					if (cnt == 0) continue;
    					cntMapTemp[nx][ny] = cnt;
    					dirMapTemp[nx][ny] = turnDir(dir);
    					continue;
    				}
    				
    				// 이미 값이 있고 내가 제일 크니?
    				List<Integer> prev = mix.get(Arrays.asList(nx, ny));
    				int maxCnt = 0;
    				if (prev != null && prev.size() > 0) {
        				for (Integer p : prev) {
        					maxCnt = Math.max(maxCnt, p);
        				}
    				}
    				
    				// 이동한 자리의 상태가 어때?
    				if (cntMapTemp[nx][ny] == 0
    						|| (cntMapTemp[nx][ny] > 0 && maxCnt < cnt)) {
    					// 누가 없거나, 있는데 내가 짱이면 나로 바꿔
    					dirMapTemp[nx][ny] = dir;
    				}
					cntMapTemp[nx][ny] += cnt;

//    				mix.put(Arrays.asList(nx, ny), Arrays.asList(cnt));
//    				mix.computeIfAbsent(Arrays.asList(nx, ny), k -> new ArrayList<>()).add(cnt);
					mix.computeIfAbsent(Arrays.asList(nx, ny), ArrayList::new).add(cnt);
    			}
    			
    			// 원본에 temp 넣기, 살아남은 좌표들 큐에 넣기
    			for (int i = 0; i < N; i++) {
    				for (int j = 0; j < N; j++) {
    					cntMap[i][j] = cntMapTemp[i][j];
    					dirMap[i][j] = dirMapTemp[i][j];
    					if (cntMap[i][j] > 0) {
    						queue.offer(new int[] {i, j});
    					}
    				}
    			}
    		}

    		long result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					result += cntMap[i][j];
				}
			}
    		System.out.println("#" + tc + " " + result);
    	}
	}
    
    static int turnDir(int dir) {
    	if (dir == 1) {
    		return 2;
    	} else if (dir == 2) {
    		return 1;
    	} else if (dir == 3) {
    		return 4;
    	} else {
    		return 3;
    	}
    }
    
}