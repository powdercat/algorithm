import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int num = Integer.parseInt(br.readLine());

        List<List<Integer>> a = new ArrayList<>();

        List<Integer>[] graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i < num; i++) {
            String[] input_str = br.readLine().split(" ");
            int x = Integer.parseInt(input_str[0]);
            int y = Integer.parseInt(input_str[1]);
            graph[x].add(y);
            graph[y].add(x);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        visited[1] = true;

        int result = 0;
        while (!queue.isEmpty()) {
            int k = queue.poll();
            List<Integer> list = graph[k];
            for (Integer l : list) {
                if (visited[l]) {
                    continue;
                }
                visited[l] = true;
                queue.offer(l);
                result++;
            }
        }
        System.out.println(result);
    }
}
