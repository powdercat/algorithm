import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
 
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        String[] list_str = br.readLine().split(" ");
        int[] list = new int [N];
        for (int i = 0; i < N; i++) {
        	list[i] = Integer.parseInt(list_str[i]);
        }
        Arrays.sort(list);
        
        int front = 0;
        int back = N - 1;
        int cnt = 0;
        while (front < back) {
        	int sum = list[front] + list[back];
        	if (sum == M) {
        		cnt++;
        		if (front < N - 1) front++;
        		if (back > 0) back--;
        	} else if (sum > M) {
        		if (back > 0) back--;
        	} else {
        		if (front < N - 1) front++;
        	}
        }
        System.out.println(cnt);
    }
}