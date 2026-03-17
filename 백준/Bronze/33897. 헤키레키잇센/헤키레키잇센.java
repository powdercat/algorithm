import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        arr[0] = Integer.parseInt(st.nextToken());
        int len = 1;
        int max = 0;
        int count = 0;
        
        for (int i = 1; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i - 1] <= arr[i]) {
                len++;
            } else {
                max = Math.max(max, len);
                len = 1;
                count++;
            }
        }
        max = Math.max(max, len);
        count++;

        System.out.println(count + " " + max);
    }
}
