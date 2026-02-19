import java.io.*;

public class Main {

    static int N, M;
    static int[] numbers, result;
    static boolean[] selected;
    static StringBuilder sb;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        numbers = new int[N];
        selected = new boolean[N];
        result = new int[M];
        for (int i = 1; i <= N; i++) {
            numbers[i - 1] = i;
        }
        permut(0);
        System.out.println(sb);
    }

    static void permut(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            if (selected[i]) continue;
            result[depth] = numbers[i];
            selected[i] = true;
            permut(depth + 1);
            selected[i] = false;
        }
    }
}