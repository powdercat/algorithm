import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input_str = br.readLine().split(" ");
        int N = Integer.parseInt(input_str[0]);
        int M = Integer.parseInt(input_str[1]);
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(str[j]);
            }
        }
        Deque<Integer[]> queue = new ArrayDeque<>();
        queue.offer(new Integer[]{0, 0, 1});

        boolean[][] visited = new boolean[N][M];
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Integer[] q = queue.poll();
            int x = q[0];
            int y = q[1];
            int cnt = q[2];
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            if (x == N - 1 && y == M - 1) {
                System.out.println(cnt);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (map[nx][ny] == 1) {
                    queue.offer(new Integer[]{nx, ny, cnt + 1});
                }
            }
        }
    }
}
