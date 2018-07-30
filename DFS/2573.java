import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {

	static int N, M, part, T;
	static int[] map[], dx = {-1,0,1,0}, dy = {0,1,0,-1};
	static boolean[] visit[];
	static Queue<Point> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String[] str = br.readLine().split(" ");

		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		map = new int[N][M];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			if(checking() == 0) {//there are no icebergs
				System.out.println(0);
				System.exit(0);
			}
			else {
				melting();
				T++;
			}
		}
	}
	private static int checking() {
		q = new LinkedList<>();

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != 0) {
					int cnt=0;
					for(int k=0; k<4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];

						if(isRange(nx, ny) && map[nx][ny] == 0) {
							cnt++;
						}
					}
					q.add(new Point(i,j,cnt));
				}
			}
		}
		return q.size();
	}
	private static void melting() {
		part = 0;
		
		while(!q.isEmpty()) {
			Point p = q.poll();

			int x = p.x;
			int y = p.y;
			int iceberg = p.iceberg;

			map[x][y] = map[x][y] - iceberg;
			if(map[x][y] < 0)
				map[x][y] = 0;
		}
		
		visit = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != 0 && !visit[i][j]) {
					DFS(i,j);
					part++;
					if(part >= 2) {
						System.out.println(T+1);
						System.exit(0);
					}
				}
			}
		}
	}
	private static void DFS(int x, int y) {

		visit[x][y] = true;

		for(int k=0; k<4; k++) {//인접한 점들 
			int nx = x + dx[k];
			int ny = y + dy[k];

			if(isRange(nx, ny) && map[nx][ny] != 0 && !visit[nx][ny]) {
				DFS(nx, ny);
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
	int x; int y; int iceberg;
	public Point(int x, int y, int iceberg) {
		this.x =x;
		this.y =y;
		this.iceberg = iceberg;
	}
}
