import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int result = 1;
        while (true) {
            int e = result % 15 == 0 ? 15 : result % 15;
            int s = result % 28 == 0 ? 28 : result % 28;
            int m = result % 19 == 0 ? 19 : result % 19;
            if (e == E && s == S && m == M) {
                break;
            }
            result++;
        }
        System.out.println(result);
    }
}