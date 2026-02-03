import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
        	int N = Integer.parseInt(br.readLine());
        	char[] tree = new char[N + 1];
        	
        	int result = 1;
        	int skip = 0;
        	for (int i = 1; i < N + 1; i++) {
        		String[] input = br.readLine().split(" ");
        		char ch = input[1].charAt(0);
        		skip = i + 1;
        		
        		// 문자인데 더 따라오는 게 없으면 0
        		if ((ch == '+' || ch == '-' || ch == '*' || ch == '/') && input.length != 4) {
        			// [i 연산자 숫자 숫자] 여야 함
        			result = 0;
        			break;
        		}
        		if (Character.isDigit(ch) && input.length > 2) {
        			// [i 숫자] 여야 함
        			result = 0;
        			break;
        		}
        		
        		tree[i] = ch;
        	}
        	for (int i = skip; i < N + 1; i++) {
        		br.readLine();
        	}
        	
        	System.out.println("#" + test_case + " " + result);
        }
    }
}