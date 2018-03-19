import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	//12:30~
	static int N ,min=200001, bx, by, bt, ex, ey, et ,cnt;
	static char[] map[];
	static int[] dist[], dx= {-1,0,1,0}, dy = {0,1,0,-1};
	static Point E, B;
	static boolean flag = false;
	static boolean[] visit[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visit = new boolean[N][N][2];
		dist = new int[N][N];

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

		DFS(B, 0);

		if(flag) {
			System.out.println(min+1);
		}
		else
			System.out.println(0);

	}
	private static void DFS(Point p, int cnt) {

		visit[p.x][p.y][p.type] = true;

		if(p.x == E.x && p.y == E.y && p.type == E.type) {
			flag = true;
			min = Math.min(min, cnt);
			visit[p.x][p.y][p.type] = false;
			return;
		}

		Up(p);
		Down(p);
		Left(p);
		Right(p);
		Turn(p);

	}
	private static void Up(Point p) {
		if(p.type == 0 && p.x-1 >= 0) {//가로인 경우 
			if(map[p.x-1][p.y-1] != '1' && map[p.x-1][p.y] != '1' && map[p.x-1][p.y+1] != '1') {
				//한 칸 위로 올릴 때 세 점 모두 나무가 없는 경우만 올려야지
				int nx = p.x-1;
				int ny = p.y;

				if(!visit[nx][ny][p.type]) {
					DFS(new Point(nx, ny, p.type), cnt++);
					visit[nx][ny][p.type] = false;
					cnt--;
				}

			}
		}
		else if(p.type == 1 && p.x-2 >= 0) {//세로인 경우 
			if(map[p.x-2][p.y] != '1') {
				int nx = p.x-1;
				int ny = p.y;

				if(!visit[nx][ny][p.type]) {
					DFS(new Point(nx, ny, p.type), cnt++);
					visit[nx][ny][p.type] = false;
					cnt--;
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
					DFS(new Point(nx, ny, p.type), cnt++);
					visit[nx][ny][p.type] = false; cnt--;
				}
			}
		}
		else if(p.type == 1 && p.x+2 < N) {
			if(map[p.x][p.y] != '1' && map[p.x+1][p.y] != '1' && map[p.x+2][p.y] != '1') {
				int nx = p.x+1;
				int ny = p.y;

				if(!visit[nx][ny][p.type]) {
					DFS(new Point(nx, ny, p.type), cnt++);
					visit[nx][ny][p.type] = false; cnt--;
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
					DFS(new Point(nx, ny, p.type), cnt++);
					visit[nx][ny][p.type] = false; cnt--;
				}
			}
		}
		else if(p.type == 1 && p.y-1 >= 0) {
			if(map[p.x-1][p.y-1] != '1' && map[p.x][p.y-1] != '1' && map[p.x+1][p.y-1] != '1') {
				int nx = p.x;
				int ny = p.y-1;

				if(!visit[nx][ny][p.type]) {
					DFS(new Point(nx, ny, p.type), cnt++);
					visit[nx][ny][p.type] = false; cnt--;
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
					DFS(new Point(nx, ny, p.type), cnt++);
					visit[nx][ny][p.type] = false; cnt--;
				}
			}
		}
		else if(p.type == 1 && p.y+1 < N) {
			if(map[p.x-1][p.y+1] != '1' && map[p.x][p.y+1] != '1' && map[p.x+1][p.y+1] != '1') {
				int nx = p.x;
				int ny = p.y+1;

				if(!visit[nx][ny][p.type]) {
					DFS(new Point(nx, ny, p.type), cnt++);
					visit[nx][ny][p.type] = false; cnt--;
				}
			}
		}
	}
	private static void Turn(Point p) {
		if(p.type == 0 && p.x-1 >= 0 && p.x+1 < N) {
			if(checkTurn(p.x, p.y)) {

				if(!visit[p.x][p.y][1]) {
					DFS(new Point(p.x, p.y, 1), cnt++);
					visit[p.x][p.y][1] = false; cnt--;
				}
			}
		}
		else if(p.type == 1 && p.y-1 >= 0 && p.y+1 < N) {
			if(checkTurn(p.x, p.y)) {
				if(!visit[p.x][p.y][0]) {
					DFS(new Point(p.x, p.y, 0), cnt++);
					visit[p.x][p.y][0] = false; cnt--;
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
