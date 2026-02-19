import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int[] trees = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(trees);

        // 제일 작은 값=left, 제일 큰 값=right
        // 적어도 M미터, H의 최댓값
        // H를 mid=left+(right-left)/2로 하기
        // 다 잘라보고, 자른 것의 합 = sum
        // sum < M -> H를 더 낮춰야 함 -> right = mid - 1
        // sum >= M -> H를 높여보기 -> left = mid + 1
        // left <= right -> 답은 right

        int left = 0;
        int right = trees[N - 1];
        while (left <= right) {
            int H = left + (right - left) / 2;

            long sum = 0;
            for (int i = 0; i < N; i++) {
                if (trees[i] > H) {
                    sum += (trees[i] - H);
                }
            }
            if (sum < M) {
                right = H - 1;
            } else {
                left = H + 1;
            }
        }
        System.out.println(right);
    }
}