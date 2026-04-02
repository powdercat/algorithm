import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        for (int[] puddle: puddles) {
            int i = puddle[1] - 1;
            int j = puddle[0] - 1;
            dp[i][j] = -1;
        }
        
        dp[0][0] = 1;
        int modular = 1000000007;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) continue;
                if (dp[i][j] == -1) continue;
                int sum = 0;
                if (i > 0 && dp[i - 1][j] != -1) sum += (dp[i - 1][j] % modular);
                if (j > 0 && dp[i][j - 1] != -1) sum += (dp[i][j - 1] % modular);
                if (sum % modular == 0) sum = 0;
                dp[i][j] = sum;
            }
        }
        int answer = dp[n - 1][m - 1] % modular;
        return answer;
    }
}