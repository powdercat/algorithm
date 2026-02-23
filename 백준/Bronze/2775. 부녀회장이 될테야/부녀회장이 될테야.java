import java.io.*;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] dp = new int[15][15];
        for (int j = 1; j <= 14; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= 14; i++) {
            for (int j = 1; j <= 14; j++) {
                int sum = 0;
                for (int l = 1; l <= j; l++) {
                    sum += dp[i - 1][l];
                }
                dp[i][j] = sum;
            }
        }

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[k][n]);
        }
    }
}