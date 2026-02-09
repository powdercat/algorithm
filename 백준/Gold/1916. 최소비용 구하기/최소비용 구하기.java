import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int nextNode;
        int price;
        Node(int nextNode, int price) {
            this.nextNode = nextNode;
            this.price = price;
        }
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        // 출발도시 도착도시 버스비용
        List<Node>[] graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String[] input_str = br.readLine().split(" ");
            int start = Integer.parseInt(input_str[0]);
            int end = Integer.parseInt(input_str[1]);
            int price = Integer.parseInt(input_str[2]);
            graph[start].add(new Node(end, price));
        }

        String[] input_str = br.readLine().split(" ");
        int start = Integer.parseInt(input_str[0]);
        int end = Integer.parseInt(input_str[1]);

        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        // 우선순위 큐에는 (현재까지의 거리정보, 현재 노드)가 필요함
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.price - b.price);
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curNode = cur.nextNode;
            int curPrice = cur.price;

            if (curPrice > distances[curNode]) { // 여기까지의 거리가, 지금 구하고 있는 거리보다 짧을때만 유효
                continue;
            }
            for (Node next : graph[curNode]) {
                int nextNode = next.nextNode;
                int nextPrice = next.price;
                int nextDistance = curPrice + nextPrice;
                if (distances[nextNode] > nextDistance) {
                    distances[nextNode] = nextDistance;
                    pq.add(new Node(nextNode, nextDistance));
                }
            }
        }
        System.out.println(distances[end]);
    }
}
