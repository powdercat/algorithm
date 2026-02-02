import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        for (int test_case = 1; test_case <= 10; test_case++) {
            LinkedList<Integer> origin = new LinkedList<>();
            int N = Integer.parseInt(br.readLine());
             
            String[] input = br.readLine().split(" ");
            for (String s : input) {
                origin.add(Integer.parseInt(s));
            }
             
            int addN = Integer.parseInt(br.readLine());
            String[] insertCommand = br.readLine().split(" ");
             
            int index = 0;
            for (int i = 0; i < addN;) {
                if (insertCommand[index].equals("I")) {
                    int insertIndex = Integer.parseInt(insertCommand[++index]);
                    int k = Integer.parseInt(insertCommand[++index]);
                    for (int j = 0; j < k; j++) {
                        origin.add(insertIndex++, Integer.parseInt(insertCommand[++index]));
                    }
                    index++;
                    i++;
                } else if (insertCommand[index].equals("D")) {
                    int deleteIndex = Integer.parseInt(insertCommand[++index]);
                    int k = Integer.parseInt(insertCommand[++index]);
                    for (int j = 0; j < k; j++) {
                    	origin.remove(deleteIndex);
                    }
                    index++;
                    i++;
                }
            }
            System.out.printf("#%d", test_case);
            for (int i = 0; i < 10; i++) {
                System.out.print(" " + origin.get(i));
            }
            System.out.println();
        }
 
    }
 
}