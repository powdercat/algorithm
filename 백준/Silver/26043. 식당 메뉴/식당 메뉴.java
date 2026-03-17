import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] order = new int[N][];
        int curIdx = 0;
        int studentCount = 0;

        PriorityQueue<Integer> A = new PriorityQueue<>((a, b) -> a - b);
        PriorityQueue<Integer> B = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                int like = Integer.parseInt(st.nextToken());
                order[studentCount++] = new int[]{num, like};
            } else {
                int[] info = order[curIdx];
                int stu = info[0];
                int like = info[1];
                if (num == like) {
                    A.offer(stu);
                } else {
                    B.offer(stu);
                }
                curIdx++;
            }
        }

        PriorityQueue<Integer> C = new PriorityQueue<>((a, b) -> a - b);
        for (int i = curIdx; i < studentCount; i++) {
            C.offer(order[i][0]);
        }

        StringBuilder sb = new StringBuilder();
        if (A.isEmpty()) {
            sb.append("None").append("\n");
        } else {
            while (!A.isEmpty()) {
                sb.append(A.poll()).append(" ");
            }
            sb.append("\n");
        }
        if (B.isEmpty()) {
            sb.append("None").append("\n");
        } else {
            while (!B.isEmpty()) {
                sb.append(B.poll()).append(" ");
            }
            sb.append("\n");
        }
        if (C.isEmpty()) {
            sb.append("None").append("\n");
        } else {
            while (!C.isEmpty()) {
                sb.append(C.poll()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}