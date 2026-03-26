import java.util.*;
import java.io.*;

class Solution {
    public String[] solution(String[][] plans) {
        Arrays.sort(plans, (a, b) -> {
            String[] time1 = a[1].split(":");
            String[] time2 = b[1].split(":");
            if (Integer.parseInt(time1[0]) == Integer.parseInt(time2[0])) {
                return Integer.parseInt(time1[1]) - Integer.parseInt(time2[1]);
            }
            return Integer.parseInt(time1[0]) - Integer.parseInt(time2[0]);
        });
        int N = plans.length;
        Deque<String[]> stack = new ArrayDeque<>(); // ["korean", "남은시간"]
        
        String[] result = new String[N];
        int r = 0;
        for (int i = 0; i < N - 1; i++) {
            String[] cur = plans[i];
            String[] start1 = cur[1].split(":"); // 12:11
            int curMin = Integer.parseInt(start1[1]) + Integer.parseInt(cur[2]) + Integer.parseInt(start1[0]) * 60;
            
            String[] next = plans[i + 1];
            String[] start2 = next[1].split(":");
            int nextMin = Integer.parseInt(start2[1]) + Integer.parseInt(start2[0]) * 60;
            
            if (curMin > nextMin) { // 과제를 멈췄다면
                String[] val = new String[]{cur[0], curMin - nextMin + ""};
                stack.push(val);
            } else { // 중간에 시간이 비면
                result[r++] = cur[0];
                if (curMin < nextMin) {
                    int leftMin = nextMin - curMin;
                    while (!stack.isEmpty() && leftMin > 0) {
                        String[] ing = stack.pop();
                        int ingLeftMin = Integer.parseInt(ing[1]);
                        if (leftMin >= ingLeftMin) {
                            result[r++] = ing[0];
                            leftMin-= ingLeftMin;
                        } else {
                            ing[1] = ingLeftMin - leftMin + "";
                            stack.push(ing);
                            break;
                        }
                    }
                }
            }
        }
        result[r++] = plans[N - 1][0];
        while (!stack.isEmpty()) {
            result[r++] = stack.pop()[0];
        }
        
        return result;
    }
}
/*
[name, start, playtime]
[["korean", "11:40", "30"], ["english", "12:10", "20"], ["math", "12:30", "40"]]
시간순으로 정렬하기
배열 순차적으로, ([다음 start] - [지금 start]) = 지금 실행시간
{start + playtime} 이 {다음 start}보다 크면 {지금 과제 playtime 줄이고} {하던 과제 스택}에 넣기
    {다음 start}보다 작거나 같으면 {종료된 과제}로 출력
    {다음 start}보다 작으면 {하던 과제 스택}에서 pop
        {다음 start} - {start + playtime} = {남은 과제를 처리할 수 있는 시간}
        {남은 시간}동안 {하던 과제1,2,3...} 처리 (남은시간 > 0인 동안)
            처리 못하면 {playtime 줄이고} {하던 과제 스택}push
                break
            처리완료하면 {종료된 과제}로 출력
            {남은시간}갱신
*/