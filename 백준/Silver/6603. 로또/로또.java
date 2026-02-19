import java.io.*;

public class Main {

    static int K, M = 6;
    static int[] numbers, result;
    static boolean[] selected;
    static StringBuilder sb;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while (true) {
            String[] input = br.readLine().split(" ");
            if (input.length == 1) {
                break;
            }
            K = Integer.parseInt(input[0]);
            numbers = new int[K];
            for (int i = 0; i < K; i++) {
                numbers[i] = Integer.parseInt(input[1 + i]);
            }
            selected = new boolean[K];
            result = new int[M];

            permut(0, 0);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void permut(int depth, int prev) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < K; i++) {
            if (selected[i]) continue;
            if (prev < numbers[i]) {
                result[depth] = numbers[i];
                selected[i] = true;
                permut(depth + 1, result[depth]);
            }
            selected[i] = false;
        }
    }
}