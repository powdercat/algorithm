import java.io.*;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] lens_st = br.readLine().split(" ");
        int N = Integer.parseInt(lens_st[0]);
        int M = Integer.parseInt(lens_st[1]);

        String[] nArray_st = br.readLine().split(" ");
        String[] mArray_st = br.readLine().split(" ");

        int len = N + M;
        int ni = 0, mi = 0;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < len; i++) {
            if (ni >= N) {
                bw.write(mArray_st[mi++] + " ");
                continue;
            } else if (mi >= M) {
                bw.write(nArray_st[ni++] + " ");
                continue;
            }
            int nv = Integer.parseInt(nArray_st[ni]);
            int mv = Integer.parseInt(mArray_st[mi]);
            if (nv < mv) {
                ni++;
                bw.write(nv + " ");
            } else {
                mi++;
                bw.write(mv + " ");
            }
        }

        bw.flush();
        bw.close();
    }
}
