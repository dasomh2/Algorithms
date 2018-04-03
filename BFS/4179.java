import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] J=new int[2];
	static boolean[] visit[];
	static char[] maze[];
	static Queue<Point> fire = new LinkedList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maze = new char[N][M];
		visit = new boolean[N][M];

		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				maze[i][j] = s.charAt(j);
				if(maze[i][j] == 'J') {
					J[0] = i;
					J[1] = j;
					maze[i][j] = '.';
				}
				else if(maze[i][j] == 'F') {
					fire.add(new Point(i,j));
					visit[i][j] = true;
				}
			}
		}
		br.close();
		BFS();
		System.out.println("IMPOSSIBLE");
		return;
	}
	private static void BFS() {
		int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
		int step=0;
		fire.add(new Point(J[0], J[1]));
		visit[J[0]][J[1]] = true;

		while(!fire.isEmpty()) {

			int qsize = fire.size();

			for(int a=0; a<qsize; a++) {
				Point p = fire.poll();
				
				if((p.x==0 || p.x==N-1 || p.y==0 || p.y==M-1) && maze[p.x][p.y] == '.') {
					System.out.println(step+1);
					System.exit(0);
				}

				for(int i=0; i<4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];

					if(Range(nx, ny) && !visit[nx][ny] && maze[nx][ny] == '.') {
						if(maze[p.x][p.y] == 'F') {
							visit[nx][ny] = true;
							maze[nx][ny] = 'F';
							fire.add(new Point(nx, ny));
						}
						else if(maze[p.x][p.y] == '.') {
							visit[nx][ny] = true;
							fire.add(new Point(nx, ny));
						}
					}
				}
			}
			step++;
		}
	}

	private static boolean Range(int x, int y) {
		if(x<0 || y<0 || x>N-1 || y>M-1)
			return false;
		return true;
	}
}
class Point {
	int x; int y;
	public Point(int x, int y ) {
		this.x = x;
		this.y = y;
	}
}
