import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    static final int SIZE = 9;
    static boolean[] isSelect = new boolean[SIZE];
    static int[] selected = new int[SIZE];
    static int[] numbers = new int[SIZE];
    static int[] myNumbers = new int[SIZE];
    static int win = 0;
    static int lose = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            // 리셋
            Arrays.fill(isSelect, false);
            Arrays.fill(selected, 0);
            Arrays.fill(numbers, 0);
            Arrays.fill(myNumbers, 0);
//            isSelect = new boolean[SIZE];
//            selected = new int[SIZE];
//            numbers = new int[SIZE];
//            myNumbers = new int[SIZE];
            win = 0;
            lose = 0;

            String[] input_str = br.readLine().split(" "); // len = SIZE
//            int[] myNumbers = new int[SIZE];
            boolean[] given = new boolean[19];
            for (int i = 0; i < SIZE; i++) {
                int temp = Integer.parseInt(input_str[i]);
                myNumbers[i] = temp;
                given[temp] = true;
            }
//            int[] numbers = new int[SIZE];
            int i = 0;
            for (int num = 1; num < 19; num++) {
                if (!given[num]) {
                    numbers[i++] = num;
                }
            }

            perm(0);
            System.out.println("#" + test_case + " " + win + " " + lose);
            /*
            // 주어진 numbers를 가지고 9개를 뽑아보자
            // 골라진 순열을 selected 배열에 순서대로 넣겠다
            // 값이 골라졌는지 여부를 isSelect[numbers의 인덱스] 로 확인하겠다.
            int firstFind = 0;
            int[] selected = new int[SIZE];
            boolean[] isSelect = new boolean[SIZE];
            // 골라진순열selected의 0번째 값을 찾으러가자
            for (int i = 0; i < SIZE; i++) { // numbers를 순회하며 골라지지 않은 경우에 넣자.
                if (isSelect[i]) { // 이미 numbers[i] 의 값이 selected순열에 있다면 스킵
                    continue;
                }
                // 아니라면 numbers[i]의 값을 selected순열의 지금 인덱스(지금은 0)에 넣기
                selected[firstFind] = numbers[i];
                // numbers[i] 값을 순열에 넣었다고 표시를 해야함
                isSelect[i] = true;
                // 이제 selected순열의 다음 인덱스(1번) 값을 찾아 떠나야해 -> 반복이네!
                // -> 그렇게 다 찾아졌다면, 이제는 selected순열의 0번째 인덱스에 numbers[i]값을 안 넣고싶어
                // 그럼 넘겨야겠지, 선택되지 않았다고도 표시해야해
                isSelect[i] = false;
                // numbers의 다음 인덱스 값을 선택하기 위해 반복문을 넘겨

            }
            */

        }
    }

    private static void perm(int selectedIdx) { // 순열selected[selectedIdx]에 넣을 값을 찾자
        // 만약에 순열selected[selectedIdx] 이렇게 넣으려는데, selectedIdx가 이미 순열을 초과했다면 종료해야함!
        // 순열이 다 채워졌다는 뜻이니까!
        if (selectedIdx == SIZE) {
            // 계산을 해야해
            // myNumbers랑 같은 인덱스끼리 vs 해
            // myNumbers[i] vs selected[i] 더 큰 사람이 myNumbers[i] + selected[i] 의 점수를 얻는다.
            int meSum = 0;
            int youSum = 0;
            // i를 0부터 9-1까지 돌면서 각각 점수를 합산한다
            for (int i = 0; i < SIZE; i++) {
                int me = myNumbers[i];
                int you = selected[i];
                int sum = me + you;
                if (me > you) {
                    meSum += sum;
                } else if (me < you) {
                    youSum += sum;
                }
            }
            // 합한 점수가 더 큰 사람이 이긴 것 - 이긴 횟수 + 1하기
            if (meSum > youSum) {
                win++;
            } else if (meSum < youSum) {
                lose++;
            }
            return;
        }

        for (int i = 0; i < SIZE; i++) {
            if (isSelect[i]) { // numbers[i]는 아직 순열에 들어가지 않았다.
                continue;
            }
            // 안 골라진 애니까 넣어야지!
            selected[selectedIdx] = numbers[i];
            // 넣었으니까 넣었음을 표시해야지
            isSelect[i] = true;
            // 다음 순열selected[selectedIdx + 1] 값을 찾으러 떠나자
            perm(selectedIdx + 1);
            // 다 찾아봤겠지
            // 이제 numbers[i]도 selected[i]에 넣지 않고, numbers[i+1]를 넣어보자!
            // 그러려면 numbers[i] 값은 아직 순열selected[i]에 넣지 않았다고 표시해야겠지
            isSelect[i] = false;
            // 다음 numbers[i+1] 값에게 넘기자
        }
    }
}