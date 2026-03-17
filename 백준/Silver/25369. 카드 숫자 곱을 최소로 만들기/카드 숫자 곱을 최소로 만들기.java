import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = 1;
        String[] input = br.readLine().split(" ");
        boolean isAll9 = true;
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(input[i]);
            M *= cur;
            if (cur != 9) {
                isAll9 = false;
            }
        }
        if (isAll9) {
            System.out.println(-1);
            System.exit(0);
        }

        result = new int[N];
        combination(0, 1);
    }

    static void combination(int depth, int mix) {
        if (depth == N) {
            if (mix > M) {
                for (int i = 0; i < N; i++) {
                    System.out.print(result[i] + " ");
                }
                System.exit(0);
            }
            return;
        }
        if (mix > M) {
            for (int i = 0; i < depth; i++) {
                System.out.print(result[i] + " ");
            }
            for (int i = depth; i <= N; i++) {
                System.out.print(1 + " ");
            }
            System.out.println();
            System.exit(0);
        }
        for (int i = 1; i <= 9; i++) {
            result[depth] = i;
            combination(depth + 1, mix * i);
        }
    }
}
