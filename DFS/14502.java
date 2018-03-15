import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, max=0;
	static int[] map[], copy[];
	static Queue<Point> q = new LinkedList<>();
	static Queue<Point> virus = new LinkedList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		copy = new int[N][M];

		for(int i=0; i<N; i++) {
			String s[] = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if(map[i][j] == 2) {
					virus.add(new Point(i,j));
				}
			}
		}

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					DFS(1, i, j);
					map[i][j] = 0;
				}
			}
		}
		System.out.println(max);
	}
	private static void spreadVirus() {
		int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy[i][j] = map[i][j];
			}
		}

		q.addAll(virus);

		while(!q.isEmpty()) {

			Point p = q.poll();

			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if(nx<0 || ny<0 || nx>N-1 || ny>M-1) continue;

				if(copy[nx][ny] == 0) {
					q.add(new Point(nx, ny));
					copy[nx][ny] = 2;
				}
			}
		}

	}
	private static void DFS(int depth, int v, int w) {
		if(depth == 3) {
			spreadVirus();
			max = Math.max(checkArea(), max);
		}
		else {
			for(int i=v; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 0) {
						map[i][j] = 1;
						DFS(depth+1, i, j);
						map[i][j] = 0;
					}
				}
			}
		}
	}
	private static int checkArea() {
		int cnt = 0;

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copy[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
