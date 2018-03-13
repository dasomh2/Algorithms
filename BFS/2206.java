import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] map[];
	static boolean[] visit[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visit = new boolean[2][N][M];

		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j)-48;
			}
		}

		System.out.println(BFS());

		br.close();
	}
	private static int BFS() {
		int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
		int qsize = 0, step=1;
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0,0,0));
		visit[0][0][0] = visit[1][0][0] = true;

		while(!q.isEmpty()) {

			qsize = q.size();
			step++;

			for(int a=0; a<qsize; a++ ) {
				Point p = q.poll();

				for(int i=0; i<4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];

					if(nx<0 || ny<0 || nx>N-1 || ny>M-1) continue;

					if(nx == N-1 && ny == M-1) {
						return step;
					}

					//벽 안 부순 경우 
					if(!visit[0][nx][ny] && p.wall == 0) {

						if(map[nx][ny] == 1) {//벽인 경우 부숨
							q.add(new Point(nx, ny, 1));
						}
						else {
							q.add(new Point(nx, ny, 0));
						}
						visit[0][nx][ny] = true;
					}
					//벽 부순 경우 
					else if(!visit[1][nx][ny] && p.wall == 1) {
						//인접한 점이 방인 경우만 갈 수 있어 
						if(map[nx][ny] == 0) {
							q.add(new Point(nx, ny, 1));
							visit[1][nx][ny] = true;
						}
					}
				}

			}
		}
		return -1;
	}

}
class Point{
	int x;
	int y;
	int wall;

	public Point(int x, int y, int wall) {
		this.x = x;
		this.y = y;
		this.wall = wall;
	}

}
