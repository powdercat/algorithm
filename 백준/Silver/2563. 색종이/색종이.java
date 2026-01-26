import java.io.*;
import java.util.StringTokenizer;


/**
 * 100*100 배열 선언
 * 해당되는 것 1로 변경
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[][] arr = new boolean[100][100]; // false

        int cnt = 0;
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    if (arr[X + x][Y + y]) {
                        continue;
                    }
                    arr[X + x][Y + y] = true;
                    cnt++;
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(cnt + ""); // + 연산에 문자열이 하나라도 있으면 전체가 문자열로 변환됨
        bw.flush();
    }
}