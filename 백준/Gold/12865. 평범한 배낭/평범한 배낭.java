import java.io.*;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        int[][] thing_WV = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            String[] input2 = br.readLine().split(" ");
            thing_WV[i][0] = Integer.parseInt(input2[0]); // W
            thing_WV[i][1] = Integer.parseInt(input2[1]); // V
        }
        // 0~K 각각 무게의 가방에 0~N번째 물건을 담아간다.
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            // i번째 things를 j무게의 가방에 넣으려고 한다
            int w = thing_WV[i][0];
            int v = thing_WV[i][1];

            // w무게 이상의 가방에만 이걸 넣을 수 있음
            for (int j = 0; j <= K; j++) {
                // w무게 가방 이전까지는 그대로 내려오기
                if (j - w < 0) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                // i-1번째 things 넣었던 배열에서 j - w 배열 값을 가져와야 함 + 내 가치
                // 걔랑, [i-1][j]값이랑 비교
                dp[i][j] = Math.max(dp[i - 1][j - w] + v, dp[i - 1][j]);
            }
        }
        int max = Integer.MIN_VALUE;
        for (int j = 0; j <= K; j++) {
            max = Math.max(max, dp[N][j]);
        }
        System.out.println(max);
    }
}
