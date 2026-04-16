import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] result = new int[3];
        while (true) {
            String[] input = br.readLine().split(" ");
            if (input.length == 1) {
                break;
            }
            int a = 0;
            int N = Integer.parseInt(input[0]);
            int P = Integer.parseInt(input[1]);
            int sum = N + 1;
            if (P % 2 == 0) {
                result[a++] = sum - P;
                result[a++] = P - 1;
                result[a++] = sum - (P - 1);
            } else {
                result[a++] = sum - P;
                result[a++] = P + 1;
                result[a++] = sum - (P + 1);
            }
            Arrays.sort(result);
            for (int i = 0; i < 3; i++) {
                sb.append(result[i] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}