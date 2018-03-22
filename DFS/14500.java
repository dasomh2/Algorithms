import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, max;
	static int[] map[], dx = {-1,0,1,0}, dy = {0,1,0,-1};
	static boolean[] visit[];


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visit = new boolean[N][M];

		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visit[i][j] = true;
				func(i,j,1,map[i][j]);
				visit[i][j] = false;
				rest(i,j,map[i][j]);
			}
		}
		System.out.println(max);
	}

	private static void func(int x, int y, int cnt, int sum) {
		if(cnt == 4) {
			max = Math.max(sum, max);
			return;
		}
		else {
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(!range(nx, ny)) {
					continue;
				}

				if(!visit[nx][ny]) {
					visit[nx][ny] = true;
					func(nx, ny, cnt+1, sum+map[nx][ny]);
					visit[nx][ny] = false;
				}
			}
		}
	}
	private static void rest(int x, int y, int initial) {
		int[] t1[] = {{0,1},{0,2},{-1,1}};
		int[] t2[] = {{0,1},{0,2},{1,1}};
		int[] t3[] = {{0,1},{-1,1},{1,1}};
		int[] t4[] = {{-1,-1},{0,-1},{1,-1}};

		int sum=initial, nx=0, ny=0;
		
		for(int j=0; j<3; j++) {
			nx = x + t1[j][0];
			ny = y + t1[j][1];
			
			if(!range(nx, ny)) {
				sum = initial;
				break;
			}
			sum += map[nx][ny];
			if(j==2) {
				max = Math.max(sum, max);
				sum = initial;
			}
		}
		for(int j=0; j<3; j++) {
			nx = x + t2[j][0];
			ny = y + t2[j][1];
			
			if(!range(nx,ny)) {
				sum = initial;
				break;
			}
			sum += map[nx][ny];
			if(j==2) {
				max = Math.max(sum, max);
				sum = initial;
			}
		}
		for(int j=0; j<3; j++) {
			nx = x + t3[j][0];
			ny = y + t3[j][1];
			
			if(!range(nx,ny)) {
				sum = initial;
				break;
			}
			sum += map[nx][ny];
			if(j==2) {
				max = Math.max(sum, max);
				sum = initial;
			}
		}

		for(int j=0; j<3; j++) {
			nx = x + t4[j][0];
			ny = y + t4[j][1];
			
			if(!range(nx,ny)) {
				sum = initial;
				break;
			}
			sum += map[nx][ny];
			if(j==2) {
				max = Math.max(sum, max);
			}
		}
	}
	private static boolean range(int x, int y) {
		if(x<0 || y<0 || x>N-1 || y>M-1) {
			return false;
		}
		return true;
	}
}
