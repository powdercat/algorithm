import java.util.ArrayDeque;
import java.util.Deque;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            String[] words = br.readLine().split(" ");
            Deque<String> stack = new ArrayDeque<>();
            for (String w : words) {
                stack.push(w);
            }

            System.out.print("Case #" + i + ": " + stack.pop());
            while (!stack.isEmpty()) {
                System.out.print(" " + stack.pop());
            }
            System.out.print("\n");
        }
    }
}