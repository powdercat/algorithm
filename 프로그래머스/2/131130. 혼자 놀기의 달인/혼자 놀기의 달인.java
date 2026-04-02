import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int N = cards.length;
        
        boolean[] visited = new boolean[N];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            int card = cards[i];
            int count = 1;
            
            while (true) {
                int index = card - 1;
                if (visited[index]) break;
                visited[index] = true;
                card = cards[index];
                count++;
            }
            // System.out.println(count);
            pq.offer(count);
        }
        if (pq.size() == 1) return 0;
        return pq.poll() * pq.poll();
    }
}

/*
1부터 탐색하기
1 -> 8 -> 4 -> 7 -> 1
2 -> 6 -> 5 -> 2

0인덱스부터 시작, 그대로 따라가기
    따라가는 것을 어디에 담지? 계속 ++하다가 방문한 곳이면 큐에 담자
방문했을경우 스킵

*/