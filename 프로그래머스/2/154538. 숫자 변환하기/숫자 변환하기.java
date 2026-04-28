import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        if (x == y) return 0;
        int[] count = new int[y + 1];
        if (x + n <= y) count[x + n] = 1;
        if (x * 2 <= y) count[x * 2] = 1;
        if (x * 3 <= y) count[x * 3] = 1;
        for (int i = x + 1; i <= y; i++) {
            if (count[i] == 0) continue;
            if (i + n <= y && (count[i + n] == 0 || count[i] < count[i + n])) {
                count[i + n] = count[i] + 1;
            }
            if (i * 2 <= y && (count[i * 2] == 0 || count[i] < count[i * 2])) {
                count[i * 2] = count[i] + 1;
            }
            if (i * 3 <= y && (count[i * 3] == 0 || count[i] < count[i * 3])) {
                count[i * 3] = count[i] + 1;
            }
        }
        
        return count[y] == 0 ? -1 : count[y];
    }
}
/*
이 값까지 오는동안 한 연산 횟수
int[Y+1]

10으로 시작(0), 15/20/30 생성, 각 +1(=1로 저장)
11 봐, 없어... 15(1) 봐 있어 -> 15에서 또 20(1이 있네 1로 냅둠)/30(1이 있네 1로 냅둠)/45(불가)
*/