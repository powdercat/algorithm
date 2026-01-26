import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int hour = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());
        int takeMin = Integer.parseInt(br.readLine());

        int plusHour = takeMin / 60;
        int plusMin = takeMin % 60;
        min += plusMin;
        if (min >= 60) {
            min -= 60;
            plusHour += 1;
        }

        hour += plusHour;
        hour %= 24;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("" + hour + " " + min);
        bw.flush();
    }
}