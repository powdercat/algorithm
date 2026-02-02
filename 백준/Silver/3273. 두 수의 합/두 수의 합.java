import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] arr_s = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arr_s[i]);
        }
        Arrays.sort(arr);

        int x = Integer.parseInt(br.readLine());

        int front = 0;
        int back = n - 1;

        int cnt = 0;
        while (front < back) {
            int sum = arr[front] + arr[back];
            if (sum == x) {
                cnt++;
                front++;
                back--;
            } else if (sum > x) {
                back--;
            } else {
                front++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt);
        System.out.println(sb);
    }
}
