import java.util.*;

class Solution {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public int solution(String[] maps) {
        int N = maps.length;
        int M = maps[0].length();
        boolean[][] visited = new boolean[N][M];
        int sx = -1;
        int sy = -1;
        for (int i = 0; i < N; i++) {
            boolean find = false;
            for (int j = 0; j < M; j++) {
                if (maps[i].charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                    find = true;
                    break;
                }
            }
            if (find) break;
        }
        
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy, 0});
        int result = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            int time = cur[2];
            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (visited[nx][ny]) continue;
                if (maps[nx].charAt(ny) == 'X') continue;
                visited[nx][ny] = true;
                if (maps[nx].charAt(ny) == 'L') {
                    result = time + 1;
                    sx = nx;
                    sy = ny;
                    break;
                }
                queue.offer(new int[]{nx, ny, time + 1});
            }
            if (result > 0) break;
        }
        if (result == 0) return -1;
        
        int answer = result;
        
        visited = new boolean[N][M];
        queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy, 0});
        result = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            int time = cur[2];
            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (visited[nx][ny]) continue;
                if (maps[nx].charAt(ny) == 'X') continue;
                visited[nx][ny] = true;
                if (maps[nx].charAt(ny) == 'E') {
                    result = time + 1;
                    sx = nx;
                    sy = ny;
                    break;
                }
                queue.offer(new int[]{nx, ny, time + 1});
            }
            if (result > 0) break;
        }
        if (result == 0) return -1;
        answer += result;
        
        return answer;
    }
}
/*
X 접근 불가
S -> L
visited 초기화
L -> E
*/
