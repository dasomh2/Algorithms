import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] box[] ;
	static int M, N, H, empty;
	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		box = new int[N][M][H];

		for(int k=0; k<H; k++) {
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					box[i][j][k] = Integer.parseInt(st.nextToken());
					if(box[i][j][k] == 1) {
						q.add(new Point(i,j,k));
					}
					if(box[i][j][k] == -1)
						empty++;
				}
			}
		}
		BFS();
	}
	private static void BFS() {
		boolean[] visit[][] = new boolean[N][M][H];//방문체크 
		int[] dx = {-1,0,1,0,0,0}, dy = {0,1,0,-1,0,0}, dz = {0,0,0,0,1,-1};
		int cnt=0;
		int ripe=0;

		while(!q.isEmpty()) {
			int qsize = q.size();
			for(int a=0; a<qsize; a++) {
				
				Point p = q.poll();//큐에는 익은 토마토
				ripe++;

				for(int i=0; i<6; i++) {
					int nx = p.x+dx[i];
					int ny = p.y+dy[i];
					int nz = p.z+dz[i];

					if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || nz>H-1 || nz<0) continue;//범위 벗어나면 통과 

					if(!visit[nx][ny][nz] && box[nx][ny][nz] == 0) {
						box[nx][ny][nz] = 1;
						visit[nx][ny][nz] = true;
						q.add(new Point(nx, ny, nz));
					}	
				}
			}
			cnt++;
		}
		if(ripe == (N*M*H)-empty) {
			System.out.println(cnt-1);
		}
		else
			System.out.println(-1);
		
		return;
	}
}
class Point {
	int x;
	int y;
	int z;

	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
