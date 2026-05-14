import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        int answer = 0;
        List<String> list = new ArrayList<>();
        for (String city : cities) {
            // 큐 순회하면서 값 있는지 없는지 확인
            boolean find = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(city.toLowerCase())) {
                    list.remove(i);
                    list.add(city.toLowerCase());
                    // 있으면 시간 +1
                    answer++;
                    find = true;
                    break;
                }
            }
            if (find) {
                continue;
            }
            // 없으면 (큐 크기가 cacheSize일 때만)큐에서 오래된 것 빼고 지금꺼 넣기, 시간 + 5
            if (list.size() == cacheSize) {
                list.remove(0);
            }
            list.add(city.toLowerCase());
            answer += 5;
        }
        
        return answer;
    }
}
/*
캐시사이즈가 0이면 return cities*5

가장 예전에 들어온 것 부터 빼는것.
큐 순회, 해당 값 없으면 제일 앞의 것 빼고 맨 뒤에 넣기.
*/