import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, X, UP=2, DOWN=3, ROW=0, COL=1, ret;
	static int[] map[], dx={0,1}, dy= {1,0};
	static boolean[] can[];
	static boolean flag, fin;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;

		//int T = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		ret = 0;

		map = new int[N][N];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<N; i++) {
			//가로 
			can = new boolean[N][N];
			flag = true;
			fin = false;
			dfs(i,0,ROW);


			//세로
			can = new boolean[N][N];
			flag = true;
			fin = false;
			dfs(0,i,COL);
		}
		System.out.println(ret);
	}
	private static void dfs(int x, int y, int type) {
		if(flag && !fin) {

			int nx = x + dx[type];
			int ny = y + dy[type];

			if(type == ROW) {
				if(y == N-1) {
					fin = true;
					ret++;
					return;
				}
			}
			else if(type == COL) {
				if(x == N-1) {
					fin = true;
					ret++;
					return;
				}
			}

			if(isRange(nx, ny)) {

				if(map[nx][ny] == map[x][y]+1) {//2 3
					if(slide(nx,ny,UP,type,map[x][y])) {
						dfs(nx, ny, type);
					}
					else {
						flag = false;
						return;
					}

				}
				else if(map[nx][ny] == map[x][y]-1) {//3 2
					if(!slide(x, y, DOWN, type, map[nx][ny])) {
						flag = false;
						return;
					}
				}
				else if(map[nx][ny] == map[x][y]) {
					dfs(nx, ny, type);
				}
			}
		}
	}
	private static boolean slide(int x, int y, int shape, int type, int value) {
		//(x,y) 기준 type(내리막 or 오르막)
		if(shape == UP) {//오르막 -(x,y)
			for(int i=0; i<X; i++) {
				x = x-dx[type];
				y = y-dy[type];

				if(!isRange(x,y) || can[x][y] || map[x][y] != value) {
					return false;
				}
				can[x][y] = true;
			}
		}
		else if(shape == DOWN) {//내리막 -(nx,ny)
			for(int i=0; i<X; i++) {
				x = x + dx[type];
				y = y + dy[type];

				if(!isRange(x,y) || can[x][y] || map[x][y] != value) {
					return false;
				}
				can[x][y] = true;
			}
			dfs(x, y, type);
		}
		return true;
	}
	private static boolean isRange(int x, int y) {
		if(x<0 || y<0 || x>N-1 || y>N-1)
			return false;
		return true;
	}
}
