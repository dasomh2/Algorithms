import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N, max;
	static char[] candy[];
	static boolean[] visit[];
	static Point[] candidate = new Point[2];
	static int[] dx = {0,1}, dy = {1,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		candy = new char[N][N];
		visit = new boolean[N][N];

		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				candy[i][j] = s.charAt(j);
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				candidate[0] = new Point(i,j);
				pick(i, j, 1);
			}
		}
		System.out.println(max);
	}
	private static void pick(int x, int y, int cnt) {

		if(cnt == 2) {
			swap();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					for(int k=0; k<2; k++) {
						max = Math.max(max,find(i,j,k));	
					}
				}
			}
			swap();
			return;
		}
		for(int i=0; i<2; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx<0 || ny<0 || nx>N-1 || ny>N-1) continue;

			candidate[1] = new Point(nx,ny);
			pick(x, y, cnt+1);
		}
	}
	private static int find(int x, int y, int dir) {

		int count = 1;
				
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		if(nx>=0 && ny>=0 && nx<N && ny<N) {
			while(candy[x][y] == candy[nx][ny]) {
				count++;
				
				nx = nx + dx[dir];
				ny = ny + dy[dir];
				
				if(nx<0 || ny<0 || nx>N-1 || ny>N-1) {
					break;
				}
			}
		}
		return count;
	}
	private static void swap() {
		Point p1 = candidate[0];
		Point p2 = candidate[1];

		char tmp = candy[p1.x][p1.y];
		candy[p1.x][p1.y] = candy[p2.x][p2.y];
		candy[p2.x][p2.y] = tmp;
	}
}
class Point {
	int x; int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
