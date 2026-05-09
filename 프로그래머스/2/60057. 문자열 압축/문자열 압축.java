import java.io.*;

class Solution {
    public int solution(String s) {
        int len = s.length();
        int unit = 1;
        int min = len;
        StringBuilder sb;
        while (unit <= len / 2) {
            sb = new StringBuilder();
            int count = 1;
            int i = 0;
            for (; i < len; i += unit) {
                if (i + unit * 2 > len) break;
                String now = s.substring(i, i + unit);
                String next = s.substring(i + unit, i + unit * 2);
                
                if (now.equals(next)) {
                    count++;
                } else {
                    if (count > 1) {
                        sb.append(count);
                        count = 1;
                    }
                    sb.append(now);
                }
            }
            if (count > 1) {
                sb.append(count);
                count = 1;
            }
            sb.append(s.substring(i));
            min = Math.min(min, sb.toString().length());
            unit++;
        }
        
        return min;
    }
}
/*
단위 n
만들어지는 최종 문자 = result
지금이랑 다음이 같으면 숫자+1, 다르면 숫자+지금 만들고 숫자는 1로

*/