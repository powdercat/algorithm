import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        if (people.length == 1) return 1;
        
        Arrays.sort(people);
        
        int front = 0;
        int back = people.length - 1;
        while (front < back) {
            if (people[front] + people[back] <= limit) {
                front++;
                back--;
                answer++;
            } else {
                back--;
                answer++;
            }
        }
        if (front == back) {
            answer++;
        }
        
        return answer;
    }
}
/*
사람 몸무게 정렬

큰 사람은 작은사람이랑 같이 타야함
back = i - 1
front = 0
back -> front 보기
    back + front <= limit면 같이 타, front++, back--, answer++;
    back + front > limit면 back 혼자 타, back--, answer++;
    front < back 일 때까지 반복
*/