import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        while (!str.equals(".")) {
            String[] arr = str.split("");
            int len = arr.length;
            Deque<String> stack = new ArrayDeque<>();

            String result = "yes";
            for (int i = 0; i < len; i++) {
                String v = arr[i];
                if (v.equals("(") || v.equals("[")) {
                    stack.push(v);
                    continue;
                }
                if (v.equals(")") || v.equals("]")) {
                	if (stack.isEmpty()) {
                		result = "no";
                		break;
                	}
                	String pop = stack.pop();
                	if ((v.equals(")") && !"(".equals(pop))) {
                        result = "no";
                        break;
                    }
                	if (v.equals("]") && !"[".equals(pop)) {
                        result = "no";
                        break;
                    }
                }
            }
            if (result.equals("yes") && !stack.isEmpty()) {
            	result = "no";
            }
            System.out.println(result);
            str = br.readLine();
        }

    }
}