import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Map<Integer, Integer> map = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(input[i]);
        }
        for (int i = 0; i < N; i++) {
            int v = num[i];
            if (map.containsKey(v)) {
                int find = map.get(v);
                if (find == -1) {
                    sb.append(find + " ");
                    continue;
                }
                if (i < find) {
                    sb.append(find + 1 + " ");
                } else {
                    int temp = -1;
                    for (int j = i + 1; j < N; j++) {
                        if (v != num[j]) {
                            temp = j;
                            break;
                        }
                    }
                    map.put(v, temp);
                    if (temp == -1) {
                        sb.append(temp + " ");
                    } else {
                        sb.append(temp + 1 + " ");
                    }
                }
            } else {
                int temp = -1;
                for (int j = i + 1; j < N; j++) {
                    if (v != num[j]) {
                        temp = j;
                        break;
                    }
                }
                map.put(v, temp);
                if (temp == -1) {
                    sb.append(temp + " ");
                } else {
                    sb.append(temp + 1 + " ");
                }
            }
        }
        
        System.out.println(sb);
    }
}