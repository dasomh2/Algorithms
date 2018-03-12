import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static final int N=12, M=6;
	static char[] puyo[] = new char[N][M];
	static boolean[] visit[];
	static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
	static Queue<Point> tmp;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int sequence=0;

		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				puyo[i][j] = s.charAt(j);
			}
		}

		while(true) {

			boolean flag = false;
			visit = new boolean[N][M];

			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(puyo[i][j] != '.' && !visit[i][j]) {
						tmp = new LinkedList<>();
						tmp.add(new Point(i,j));
						DFS(i,j);

						if(tmp.size() >= 4) {
							flag = true;
							while(!tmp.isEmpty()) {
								Point p = tmp.poll();
								puyo[p.x][p.y] = '.';
							}
						}
					}
				}
			}
			if(flag) {
				sequence++;
				down();
			}
			else {
				break;
			}		
		}
		System.out.println(sequence);
	}
	private static void DFS(int x, int y) {
		
		visit[x][y] = true;

		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx<0 || ny<0 || nx>N-1 || ny>M-1) continue;

			if(puyo[nx][ny] == puyo[x][y] && !visit[nx][ny]) {
				tmp.add(new Point(nx, ny));
				DFS(nx, ny);
			}
		}
	}

	private static void down() {

		for (int i = 0; i < 6; i++) {
	        for (int j = 10; j >= 0; j--) {
	            for (int k = 11; k > j; k--) {
	                if (puyo[j][i] != '.' && puyo[k][i] == '.') {
	                    puyo[k][i] = puyo[j][i];
	                    puyo[j][i] = '.';
	                    break;
	                }
	            }
	        }
	    }

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
