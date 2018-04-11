import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, L, res;
	static boolean flag, pass;
	static int[] map[], dx={0,1}, dy= {1,0};
	static boolean[] slide[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<N; j++)
				map[i][j] = Integer.parseInt(s[j]);
		}

		for(int i=0; i<N; i++) {
			flag = true;
			slide = new boolean[N][N];
			int end_x=N-1, end_y=i;
			dfs(0,i,1,end_x, end_y);
			
			flag = true;
			slide = new boolean[N][N];
			end_y=N-1; end_x=i;
			dfs(i,0,0,end_x, end_y);
		}
		System.out.print(res+" ");
	}
	private static void dfs(int x, int y, int dir, int end_x, int end_y) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		if(x == end_x && y == end_y) {
			flag = false;
			res++;
			return;
		}
		if(!flag)
			return;

		if(isRange(nx, ny)) {
			if(Math.abs(map[nx][ny]-map[x][y]) > 1) {//차이가 1 이상이면 갈 수가 없음
				flag = false;
				return;
			}
			else if((map[nx][ny]-map[x][y]) == 1) {//차이가 1이면 경사로 있나 살펴봄
				int lx=nx, ly=ny;
				if(isPossible(nx,ny,L,dir,map[x][y])) {
					for(int k=0; k<L; k++) {
						lx = lx-dx[dir];
						ly = ly-dy[dir];

						slide[lx][ly] = true;
					}
					dfs(nx, ny, dir, end_x, end_y);
				}
				flag = false;
				return;
			}
			else if(map[nx][ny] - map[x][y] == -1) {
				int lx=x, ly=y;
				if(isPossible2(x,y,L,dir,map[nx][ny])) {
					for(int k=0; k<L; k++) {
						lx = lx+dx[dir];
						ly = ly+dy[dir];

						slide[lx][ly] = true;
					}
					dfs(lx, ly, dir, end_x, end_y);
				}
				flag = false;
				return;
			}
			else {//차이가 0이면 이동 
				dfs(nx, ny, dir, end_x, end_y);
			}
		}
	}
	private static boolean isPossible(int start_x, int start_y, int length, int dir, int value) {//3 -> 2
		if(dir == 1) {
			if(start_x-length<0)
				return false;
			for(int j=start_x-1; j>=start_x-length; j--) {
				if(map[j][start_y] != value || slide[j][start_y]) {
					return false;
				}
			}
		}
		else if(dir == 0) {
			if(start_y-length<0)
				return false;
			for(int j=start_y-1; j>=start_y-length; j--) {
				if(map[start_x][j] != value || slide[start_x][j]) {
					return false;
				}
			}
		}

		return true;
	}
	private static boolean isPossible2(int start_x, int start_y, int length, int dir, int value) {//2 -> 3
		if(dir == 1) {
			if(start_x+length>N-1) {
				return false;
			}
			for(int j=start_x+1; j<=start_x+length; j++) {
				if(map[j][start_y] != value || slide[j][start_y]) {
					return false;
				}
			}
		}
		else if(dir == 0) {
			if(start_y+length>N-1) {
				return false;
			}
			for(int j=start_y+1; j<=start_y+length; j++) {
				if(map[start_x][j] != value || slide[j][start_y]) {
					return false;
				}
			}
		}
		return true;
	}
	private static boolean isRange(int x, int y) {
		if(x<0 || y<0 || x>N-1 || y>N-1)
			return false;
		return true;
	}
}
