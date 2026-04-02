import java.util.*;
class Solution {
    
    static int cmd1 = 3; 
    
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int N = data.length;
        
        int cmd = 3;
        if (ext.equals("code")) {
            cmd = 0;
        } else if (ext.equals("date")) {
            cmd = 1;
        } else if (ext.equals("maximum")) {
            cmd = 2;
        }
        
        int size = 0;
        for (int[] d : data) {
            if (d[cmd] < val_ext) {
                size++;
            }
        }
        int[][] temp = new int[size][4];
        size = 0;
        for (int[] d : data) {
            if (d[cmd] < val_ext) {
                temp[size++] = d;
            }
        }
        
        if (sort_by.equals("code")) {
            cmd1 = 0;
        } else if (sort_by.equals("date")) {
            cmd1 = 1;
        } else if (sort_by.equals("maximum")) {
            cmd1 = 2;
        }
        
        Arrays.sort(temp, (a, b) -> {
            return a[cmd1] - b[cmd1];
        });
        
        // for (int i = 0; i < size; i++) {
        //     System.out.println(Arrays.toString(temp[i]));
        // }
        
        return temp;
    }
}
/*
[코드 번호(code), 제조일(date), 최대 수량(maximum), 현재 수량(remain)]
필터링: ext 컬럼이 <= val_ext
정렬: sort_by 컬럼 오름차순


*/