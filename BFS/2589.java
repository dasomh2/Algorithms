import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static char[] map[];
	static int MIN = Integer.MIN_VALUE;
	static int[] dist[];
	static int N, M;
	static ArrayList<Point> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		dist = new int[N][M];

		String s = "";

		for(int i=0; i<N; i++) {
			s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'L') {
					list.add(new Point(i,j));
				}
			}
		}
		for(int i=0; i<list.size(); i++) {
			Point p = list.get(i);
			dist = new int[N][M];
			BFS(p.x, p.y);
		}
		System.out.println(MIN-1);
	}

	private static void BFS(int x, int y) {
		int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		dist[x][y] = 1;

		while(!q.isEmpty()) {

			Point p = q.poll();

			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if(isRange(nx, ny) && dist[nx][ny] == 0 && map[nx][ny] == 'L') {
					dist[nx][ny] = dist[p.x][p.y] + 1;
					q.add(new Point(nx, ny));
					MIN = Math.max(MIN, dist[nx][ny]);
				}
			}
		}
	}
	private static boolean isRange(int x, int y) {
		if(x<0 || y<0 || x>N-1 || y>M-1)
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
