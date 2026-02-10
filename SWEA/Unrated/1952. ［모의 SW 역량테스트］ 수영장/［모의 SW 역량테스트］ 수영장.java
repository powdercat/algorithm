import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int dayPrice;
	static int monthPrice;
	static int threeMonthPrice;
	static int yearPrice;
	static int[] plans;
	
	static int min;

    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T = Integer.parseInt(br.readLine());
    	for (int t = 1; t <= T; t++) {
    		String[] input = br.readLine().split(" ");
    		dayPrice = Integer.parseInt(input[0]);
    		monthPrice = Integer.parseInt(input[1]);
    		threeMonthPrice = Integer.parseInt(input[2]);
    		yearPrice = Integer.parseInt(input[3]);
    		String[] input2 = br.readLine().split(" ");
    		plans = new int[13];
    		int totalDay = 0;
    		for (int i = 1; i < 13; i++) {
    			plans[i] = Integer.parseInt(input2[i - 1]);
    			totalDay += plans[i];
    		}
    		
    		if (totalDay == 0) {
    			min = 0;
    		} else {
        		min = yearPrice;
        		cal(1, 0); // 1월부터 금액을 계산해보자
    		}
        	
    		System.out.println("#" + t + " " + min);
    	}
    }
    
    private static void cal(int month, int sumPrice) {
    	if (month > 12) {
    		// 최솟값 계산하기
    		min = Math.min(min, sumPrice);
    		return;
    	}
    	if (sumPrice > min) {
    		return;
    	}
    	
    	// 해당 월의 이용일이 몇일인지?
    	int cnt = plans[month];
    	if (cnt == 0) {
        	// 0일이면 다음 달로 넘기기
    		cal(month + 1, sumPrice);
    	} else {
        	// 1일 이상이면
        	// 1. 모두 1일 이용권
    		cal(month + 1, sumPrice + cnt * dayPrice);
        	// 2. 한 달 이용권
    		cal(month + 1, sumPrice + monthPrice);
        	// 3. 세 달 이용권
    		cal(month + 3, sumPrice + threeMonthPrice);
    	}
    }
    
}