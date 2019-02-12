import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static char[] map[] = new char[12][6];
	static boolean[] visit[] ;
	static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
	static int cnt, total;
	static ArrayList<Point> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st;

		String s = "";

		for(int i=0; i<12; i++) {
			s = br.readLine();
			for(int j=0; j<6; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] != '.')
					total++;
			}
		}
		play_puyo();
		System.out.println(cnt);
	}
	private static void play_puyo() {
		while(true) {

			if(total == 0) {
				return;
			}
			visit = new boolean[12][6];
			boolean canPlay = false;

			//현재 상황에서 터뜨릴 수 있는 뿌요 다 찾아서 터뜨림 (dfs)
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(map[i][j] != '.' && !visit[i][j]) {
						dfs(i, j, map[i][j]);
						if(list.size() >= 4) {
							canPlay = true;
							for(int k=0; k<list.size(); k++) {
								Point p = list.get(k);
								map[p.x][p.y] = '.';
								total--;
							}
						}
						list.clear();
					}
				}
			}

			if(!canPlay) {//더이상 터뜨릴 뿌요가 없음 
				break;
			}
			else {
				cnt++;
				//터진 뿌요들 내려보내기 
				down();

			}
		}
	}
	private static void dfs(int x, int y, char cur) {
		visit[x][y] = true;
		list.add(new Point(x, y));

		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(isRange(nx, ny) && !visit[nx][ny] && map[nx][ny] == cur) {
				visit[nx][ny] = true;
				dfs(nx, ny, map[nx][ny]);
			}
		}
	}
	private static void down() {

		Queue<Character> q;

		for(int j=0; j<6; j++) {
			q = new LinkedList<>();
			for(int i=12-1; i>=0; i--) {
				if(map[i][j] != '.') {
					q.add(map[i][j]);
					map[i][j] = '.';
				}
			}

			int idx=11;
			while(!q.isEmpty()) {
				char cur = q.poll();
				map[idx--][j] = cur;
			}
		}
	}
	private static boolean isRange(int x, int y) {
		if(x<0 || y<0 || x>12-1 || y>6-1)
			return false;
		return true;
	}
}
class Point {
	int x,y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
