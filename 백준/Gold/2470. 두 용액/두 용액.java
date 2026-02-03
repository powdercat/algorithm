import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
 
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String[] list_str = br.readLine().split(" ");
        int[] list = new int [N];
        for (int i = 0; i < N; i++) {
        	list[i] = Integer.parseInt(list_str[i]);
        }
        Arrays.sort(list);
        
        int front = 0;
        int back = N - 1;
        
        int minGap = Integer.MAX_VALUE;
        int[] minGapPair = new int[2];
        
        if (list[0] > 0) {
            // 양수만 있을 경우
        	minGapPair[0] = list[0];
        	minGapPair[1] = list[1];
        	minGap = minGapPair[0] + minGapPair[1];
        } else if (list[N - 1] < 0) {
        	// 음수만 있을 경우
        	minGapPair[0] = list[N - 2];
        	minGapPair[1] = list[N - 1];
        	minGap = minGapPair[0] + minGapPair[1];
        } else {
        	while (front < back) {
        		int frontValue = list[front];
        		int backValue = list[back];
            	int sum = frontValue + backValue;
            	if (sum == 0) {
            		minGap = 0;
                	minGapPair[0] = frontValue;
                	minGapPair[1] = backValue;
                	break;
            	} else if (sum < 0) {
            		front++;
            	} else {
            		back--;
            	}
            	
            	int abs = Math.abs(sum);
            	if (abs < minGap) {
            		minGap = abs;
                	minGapPair[0] = frontValue;
                	minGapPair[1] = backValue;
            	}
            }
        }
        
        System.out.println(minGapPair[0] + " " + minGapPair[1]);
    }
}