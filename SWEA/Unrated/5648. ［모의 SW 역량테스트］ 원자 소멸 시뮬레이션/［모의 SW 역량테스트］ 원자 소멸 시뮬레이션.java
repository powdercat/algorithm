// SWEA

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	static int[][] energy;
	static class Atom {
		int x;
		int y;
		int dir;
		int k;

		public Atom(int x, int y, int dir, int k) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.k = k;
		}
	}
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    	// 0.5초 만에 원자가 만나는 경우를 계산하기 힘드니 좌표를 두 배로 만들기 -2000~2000을 0~4000으로 이동
    	// 좌표, 에너지를 담은 ArrayList 가 필요하다
    	// 모두 넣는다.
    	// 전부 방향에 맞게 1씩 이동시킨다. 이동 시키면서 0이나 4000에서 벗어나면 밖에서 오는 원자는 없으니 더 이상 추적 필요 없음
    	// 기존 좌표의 에너지는 0으로, 이동된 좌표의 에너지에 자신의 에너지 추가
    	// 1씩 다 이동시켰으면 하나씩 뽑기
    	// 그 원자의 에너지랑, 좌표의 에너지가 다르면 다른 원자도 있다는 뜻 = 충돌했다
    	// 그 원자를 리스트에서 제외시키고, 해당 좌표의 에너지는 0으로 만들기 (충돌한 다른 원자들도 자신의 에너지 != 0 라서 제거될 수 있음)
    	
    	StringBuilder sb = new StringBuilder();
    	
    	int T = Integer.parseInt(br.readLine());
    	for (int t = 1; t <= T; t++) {
    		sb.append("#").append(t).append(" ");
    		energy = new int[4001][4001];
    		List<Atom> list = new ArrayList<>();
    		
    		int N = Integer.parseInt(br.readLine());
    		for (int n = 0; n < N; n++) {
        		String[] input = br.readLine().split(" ");
        		int x = Integer.parseInt(input[0]) * 2 + 2000;
        		int y = Integer.parseInt(input[1]) * 2 + 2000;
        		int dir = Integer.parseInt(input[2]);
        		int k = Integer.parseInt(input[3]);
        		list.add(new Atom(x, y, dir, k));
        		energy[x][y] = k;
    		}
    		int sum = 0;
    		while (list.size() > 1) {
    			// 원자들 모두 1씩 이동
        		for (int i = list.size() - 1; i >= 0; i--) {
        			Atom atom = list.get(i);
        			energy[atom.x][atom.y] = 0;
        			
        			atom.x += dx[atom.dir];
        			atom.y += dy[atom.dir];
        			if (atom.x < 0 || atom.x > 4000 || atom.y < 0 || atom.y > 4000) {
        				list.remove(i);
        				continue;
        			}
        			energy[atom.x][atom.y] += atom.k;
        		}
        		
        		// 에너지가 나랑 다르면 충돌한 것이니 제거 및 에너지 합산
        		for (int i = list.size() - 1; i >= 0; i--) {
        			Atom atom = list.get(i);
        			if (energy[atom.x][atom.y] == atom.k) {
        				continue;
        			}
        			sum += energy[atom.x][atom.y];
        			energy[atom.x][atom.y] = 0;
        			list.remove(i);
        		}
    		}
    		sb.append(sum).append("\n");
    	}
    	System.out.println(sb);
    }
}
