import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int N = progresses.length;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            int leftPercent = 100 - progresses[i];
            int day = leftPercent / speeds[i];
            if (leftPercent % speeds[i] != 0) {
                day++;
            }
            arr[i] = day;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        int t = 1;
        for (int i = 1; i < N; i++) {
            if (arr[i - 1] < arr[i]) {
                queue.offer(t);
                t = 1;
                continue;
            }
            arr[i] = arr[i - 1];
            t++;
        }
        if (t != 0) {
            queue.offer(t);
        }
        int len = queue.size();
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            answer[i] = queue.poll();
        }
        return answer;
    }
}
