import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int N, bx, by, bt, ex, ey, et;
	static char[] map[];
	static int[] dist[][];
	static Point E, B;
	static boolean[] visit[][];
	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visit = new boolean[N][N][2];
		dist = new int[N][N][2];

		int cnt_E=0, cnt_B=0;

		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'B' && cnt_B < 2) {
					if(cnt_B == 1) {
						bx = i;
						by = j;
					}
					cnt_B++;
				}
				else if(map[i][j] == 'E' && cnt_E < 2) {
					if(cnt_E == 1) {
						ex = i;
						ey = j;
					}
					cnt_E++;
				}
			}
		}
		br.close();

		bt = isVertical(bx, by);
		et = isVertical(ex, ey);

		E = new Point(ex, ey, et);
		B = new Point(bx, by, bt);

		q.add(B);
		visit[bx][by][bt] = true;
		BFS(B, 0);
	}
	private static void BFS(Point p, int cnt) {

		while(!q.isEmpty()) {
			
			int nx = q.peek().x;
			int ny = q.peek().y;
			int nt = q.poll().type;
			
			if(nx == E.x && ny == E.y && nt == E.type) {
				System.out.println(dist[E.x][E.y][E.type]);
				System.exit(0);
			}

			Point new_p = new Point(nx, ny, nt);
			
			Up(new_p);
			Down(new_p);
			Left(new_p);
			Right(new_p);
			Turn(new_p);
		}
		System.out.println(0);
	}
	private static void Up(Point p) {
		if(p.type == 0 && p.x-1 >= 0) {//가로인 경우 
			if(map[p.x-1][p.y-1] != '1' && map[p.x-1][p.y] != '1' && map[p.x-1][p.y+1] != '1') {
				//한 칸 위로 올릴 때 세 점 모두 나무가 없는 경우만 올려야지
				int nx = p.x-1;
				int ny = p.y;

				if(!visit[nx][ny][p.type]) {
					visit[nx][ny][p.type] = true;
					q.add(new Point(nx, ny, p.type));
					dist[nx][ny][p.type] = dist[p.x][p.y][p.type]+1;
					
				}

			}
		}
		else if(p.type == 1 && p.x-2 >= 0) {//세로인 경우 
			if(map[p.x-2][p.y] != '1') {
				int nx = p.x-1;
				int ny = p.y;

				if(!visit[nx][ny][p.type]) {
					visit[nx][ny][p.type] = true;
					q.add(new Point(nx, ny, p.type));
					dist[nx][ny][p.type] = dist[p.x][p.y][p.type]+1;
				}
			}
		}
	}
	private static void Down(Point p) {
		if(p.type == 0 && p.x+1 < N) {
			if(map[p.x+1][p.y-1] != '1' && map[p.x+1][p.y] != '1' && map[p.x+1][p.y+1] != '1') {
				int nx = p.x+1;
				int ny = p.y;

				if(!visit[nx][ny][p.type]) {
					visit[nx][ny][p.type] = true;
					q.add(new Point(nx, ny, p.type));
					dist[nx][ny][p.type] = dist[p.x][p.y][p.type]+1;
				}
			}
		}
		else if(p.type == 1 && p.x+2 < N) {
			if(map[p.x][p.y] != '1' && map[p.x+1][p.y] != '1' && map[p.x+2][p.y] != '1') {
				int nx = p.x+1;
				int ny = p.y;

				if(!visit[nx][ny][p.type]) {
					visit[nx][ny][p.type] = true;
					q.add(new Point(nx, ny, p.type));
					dist[nx][ny][p.type] = dist[p.x][p.y][p.type]+1;
				}
			}
		}
	}
	private static void Left(Point p) {
		if(p.type == 0 && p.y-2 >= 0) {
			if(map[p.x][p.y-2] != '1' && map[p.x][p.y-1] != '1' && map[p.x][p.y] != '1') {
				int nx = p.x;
				int ny = p.y-1;

				if(!visit[nx][ny][p.type]) {
					visit[nx][ny][p.type] = true;
					q.add(new Point(nx, ny, p.type));
					dist[nx][ny][p.type] = dist[p.x][p.y][p.type]+1;
				}
			}
		}
		else if(p.type == 1 && p.y-1 >= 0) {
			if(map[p.x-1][p.y-1] != '1' && map[p.x][p.y-1] != '1' && map[p.x+1][p.y-1] != '1') {
				int nx = p.x;
				int ny = p.y-1;

				if(!visit[nx][ny][p.type]) {
					visit[nx][ny][p.type] = true;
					q.add(new Point(nx, ny, p.type));
					dist[nx][ny][p.type] = dist[p.x][p.y][p.type]+1;
				}
			}
		}
	}
	private static void Right(Point p) {
		if(p.type == 0 && p.y+2 < N) {
			if(map[p.x][p.y] != '1' && map[p.x][p.y+1] != '1' && map[p.x][p.y+2] != '1') {
				int nx = p.x;
				int ny = p.y+1;

				if(!visit[nx][ny][p.type]) {
					visit[nx][ny][p.type] = true;
					q.add(new Point(nx, ny, p.type));
					dist[nx][ny][p.type] = dist[p.x][p.y][p.type]+1;
				}
			}
		}
		else if(p.type == 1 && p.y+1 < N) {
			if(map[p.x-1][p.y+1] != '1' && map[p.x][p.y+1] != '1' && map[p.x+1][p.y+1] != '1') {
				int nx = p.x;
				int ny = p.y+1;

				if(!visit[nx][ny][p.type]) {
					visit[nx][ny][p.type] = true;
					q.add(new Point(nx, ny, p.type));
					dist[nx][ny][p.type] = dist[p.x][p.y][p.type]+1;
				}
			}
		}
	}
	private static void Turn(Point p) {
		if(p.type == 0 && p.x-1 >= 0 && p.x+1 < N) {
			if(checkTurn(p.x, p.y)) {

				if(!visit[p.x][p.y][1]) {
					visit[p.x][p.y][1] = true;
					q.add(new Point(p.x, p.y, 1));
					dist[p.x][p.y][1] = dist[p.x][p.y][p.type]+1;
				}
			}
		}
		else if(p.type == 1 && p.y-1 >= 0 && p.y+1 < N) {
			if(checkTurn(p.x, p.y)) {
				if(!visit[p.x][p.y][0]) {
					visit[p.x][p.y][0] = true;
					q.add(new Point(p.x, p.y, 0));
					dist[p.x][p.y][0] = dist[p.x][p.y][p.type]+1;
					
				}
			}
		}
	}
	private static boolean checkTurn(int x, int y) {
		if(map[x-1][y] != '1' && map[x-1][y-1] != '1' && map[x-1][y+1] != '1'
				&& map[x][y-1] != '1' && map[x][y+1] != '1'
				&& map[x+1][y-1] != '1' && map[x+1][y] != '1' && map[x+1][y+1] != '1') {
			return true;
		}
		else
			return false;
		
	}
	private static int isVertical(int x, int y) {
		if(x-1 >= 0 && x+1 < N) {
			if(map[x][y] == map[x-1][y] && map[x+1][y] == map[x][y]) {
				return 1;
			}
		}
		return 0;
	}
}
class Point {
	int x; int y; int type;

	public Point(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type =type;
	}
}
