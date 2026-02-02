
import java.io.*;
//import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] lens_st = br.readLine().split(" ");
        int N = Integer.parseInt(lens_st[0]);
        int M = Integer.parseInt(lens_st[1]);

        String[] nArray_st = br.readLine().split(" ");
        String[] mArray_st = br.readLine().split(" ");

        int len = N + M;
//        int[] result = new int[len];
        int ni = 0, mi = 0;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));   //할당된 버퍼에 값 넣어주기
        for (int i = 0; i < len; i++) {
            if (ni >= N) {
                bw.write(mArray_st[mi++] + " ");
//                result[i] = Integer.parseInt(mArray_st[mi]);
//                System.out.print(mArray_st[mi++] + " ");
                continue;
            } else if (mi >= M) {
                bw.write(nArray_st[ni++] + " ");
//                result[i] = Integer.parseInt(nArray_st[ni]);
//                System.out.print(nArray_st[ni++] + " ");
                continue;
            }
            int nv = Integer.parseInt(nArray_st[ni]);
            int mv = Integer.parseInt(mArray_st[mi]);
            if (nv < mv) {
                ni++;
//                result[i] = nv;
                bw.write(nv + " ");
//                System.out.print(nv + " ");
            } else {
                mi++;
//                result[i] = mv;
                bw.write(mv + " ");
//                System.out.print(mv + " ");
            }
        }

           //버퍼에 있는 값 전부 출력
        bw.flush();   //남아있는 데이터를 모두 출력시킴
        bw.close();   //스트림을 닫음
//        System.out.print(result[0]);

//        String s = result[0] + "";
//        for (int i = 1; i < len; i++) {
//            s += (" " + result[i]);
////            System.out.print(" " + result[i]);
//        }
    }
}
