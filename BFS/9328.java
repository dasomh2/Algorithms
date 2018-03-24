import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,M,max;
	static char[] map[];
	static boolean[] visit[];
	static boolean[] key;
	static Queue<Point> q;
	static ArrayList<Point> door;
	private static char value;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String s = "";
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			max = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			q = new LinkedList<>();
			door = new ArrayList<>();
			
			key = new boolean[26];
			map = new char[N][M];
			visit = new boolean[N][M];

			for(int i=0; i<N; i++) {
				s = br.readLine();
				for(int j=0; j<M; j++) {
					map[i][j] = s.charAt(j);
					if((i==0 || j==0 || i==N-1 || j==M-1) && map[i][j] != '*') {
						q.add(new Point(i,j));
					}
				}
			}
			s = br.readLine();
			for(int i=0; i<s.length() && !s.equals("0"); i++) {
				int num = s.charAt(i)-'a';
				key[num] = true;
			}
			max = Math.max(BFS(), max);
			sb.append(max+"\n");
		}
		System.out.println(sb.toString());
	}
	private static int BFS() {
		int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
		int cnt = 0;

		while(!q.isEmpty()) {

			Point p = q.poll();
			value = map[p.x][p.y];


			if(value >= 'A' && value <= 'Z') {
				if(key[value-'A']) {
					map[p.x][p.y] = '.';
				}
				else {
					door.add(new Point(p.x, p.y, value));
					continue;
				}
			}
			else if(value >= 'a' && value <= 'z') {
				if(!key[value-'a']) {
					key[value-'a'] = true;
				}
				for(int i=0; i<door.size(); i++) {
					if(door.get(i).name == (value-'a'+'A')) {
						q.offer(new Point(door.get(i).x, door.get(i).y));
					}
				}
			}
			else if(value == '$') {
				map[p.x][p.y] = '.';
				cnt++;
			}

			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || visit[nx][ny] || map[nx][ny] == '*')	continue;

				visit[nx][ny] = true;
				q.offer(new Point(nx, ny));
			}
		}
		return cnt;
	}
}
class Point {
	int x; int y; char name;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point(int x, int y, char name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
}
