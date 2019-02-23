import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	static int time=1, R, C, N;
	static char[] map[];
	static Queue<Point> bomb = new LinkedList<>();
	static boolean[] visit[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		if(N%2 == 0) {
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<C; j++) {
				sb.append('O');
			}

			for(int i=0; i<R; i++) {
				System.out.println(sb.toString());
			}
			return;
		}

		String s = "";
		for(int i=0; i<R; i++) {
			s = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'O') {
					bomb.add(new Point(i,j,3));
				}
			}
		}
		bfs();
	}
	private static void bfs() {

		for(int t=1; t<=N; t++) {

			//폭탄 map에 세팅 
			if(t == 1) {
				tick_tock();
			}
			else {
				if(t%2 == 0) {
					tick_tock();
					put_bomb();
				}
				else {
					baam();
				}
			}
		}
		print_map();
	}
	private static void baam() {
		int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
		visit = new boolean[R][C];

		//폭탄 터뜨리기
		int qsize = bomb.size();
		for(int a=0; a<qsize; a++) {
			Point p = bomb.poll();
			p.t--;

			if(p.t == 0) {
				map[p.x][p.y] = '.';
				for(int i=0; i<4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];	

					if(isRange(nx, ny) && !visit[nx][ny]) {
						visit[nx][ny] = true;
						map[nx][ny] = '.';
					}
				}
			}
			else {
				if(map[p.x][p.y] != '.')
					bomb.add(p);
			}

		}
	}
	private static void tick_tock() {
		int qsize = bomb.size();

		for(int i=0; i<qsize; i++) {
			Point p = bomb.poll();
			p.t--;
			bomb.add(p);
		}
	}
	private static void put_bomb() {

		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == '.') {
					map[i][j] = 'O';
					bomb.offer(new Point(i,j,3));
				}
			}
		}
	}
	private static void print_map() {
		for(int i=0; i<R; i++) {
			System.out.println(map[i]);
		}
	}
	private static boolean isRange(int x, int y) {
		if(x<0 || y<0 || x>R-1 || y>C-1)
			return false;
		return true;
	}
}
class Point {
	int x, y, t;
	public Point(int x, int y, int t) {
		this.x = x;
		this.y = y;
		this.t = t;
	}
}
