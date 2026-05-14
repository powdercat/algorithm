import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }
        for (String calling : callings) {
            int rank = map.get(calling);
            players[rank] = players[rank - 1];
            players[rank - 1] = calling;
            
            map.replace(calling, rank - 1);
            map.replace(players[rank], rank);
        }
        
        return players;
    }
}