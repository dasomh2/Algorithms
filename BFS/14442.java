import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int[] map[];
	static boolean[] visit[][];
	static int N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visit = new boolean[K+1][N][M];

		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j) - 48;
			}
		}
		System.out.println(BFS());
	}
	private static int BFS() {
		Queue<Point> q = new LinkedList<>();
		int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
		q.offer(new Point(map[0][0], 0, 0));
		visit[map[0][0]][0][0] = true;

		int res = 0;

		while(!q.isEmpty()) {

			res++;
			int qsize = q.size();

			for(int a=0; a<qsize; a++) {

				Point p = q.poll();
		
				if(p.x == N-1 && p.y == M-1) {
					return res;
				}

				for(int i=0; i<4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];

					if(isRange(nx, ny)) {

						if(p.wall == K) {//K번 부쉈으면 다음곳은 0이고 방문안한곳 
							if(map[nx][ny] == 0 && !visit[p.wall][nx][ny]) {
								visit[p.wall][nx][ny] = true;
								q.offer(new Point(p.wall, nx, ny));
							}
						}
						else if(p.wall < K) {//K번보다 적게 벽을 부쉈을 때

							if(map[nx][ny] == 0) {//현재 벽이 아니면 그냥 간다 
								if(!visit[p.wall][nx][ny]) {
									visit[p.wall][nx][ny] = true;
									q.offer(new Point(p.wall, nx, ny));
								}
							}
							else {//현재 벽이면 부수고 간다 
								if(!visit[p.wall][nx][ny]) {
									visit[p.wall][nx][ny] = visit[p.wall+1][nx][ny] = true;
									q.offer(new Point(p.wall+1, nx, ny));
								}
							}
						}
					}
				}
			}
		}

		return -1;
	}
	private static boolean isRange(int x, int y) {
		if(x<0 || y<0 || x>N-1 || y>M-1)
			return false;
		return true;
	}
}
class Point {
	int wall;
	int x;
	int y;
	public Point(int wall, int x, int y) {
		this.wall = wall;
		this.x = x;
		this.y = y;
	}
}
