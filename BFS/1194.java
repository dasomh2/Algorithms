import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static Queue<Point> q = new LinkedList<>();
	static char[] map[];
	static boolean[] visit[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visit = new boolean[N][M][1<<7];//64 : possibilities of keys

		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == '0') {
					q.add(new Point(i,j,0));
					visit[i][j][0] = true;
				}
			}
		}
		BFS();

	}

	private static void BFS() {
		int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
		int step = 0;
		
		while(!q.isEmpty()) {

			int qSize = q.size();
			
			while(qSize-- > 0) {
				
				Point p = q.poll();
				for(int i=0; i<4; i++) {

					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					int key = p.key;

					if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || map[nx][ny] == '#' || visit[nx][ny][key])
						continue;

					if(map[nx][ny] == '1') {
						System.out.println(step);
						return;
					}
					if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
						key = (key |= (1 << map[nx][ny]-'a'));
					}
					else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
						if((key & (1 << map[nx][ny]-'A')) == 0) {
							continue;
						}
					}

					visit[nx][ny][key] = true;
					q.offer(new Point(nx, ny, key));
					
				}
				
			}step++;
		}
		System.out.println("-1");

	}

}
class Point {
	int x;
	int y;
	int key;

	public Point(int x, int y, int key) {
		this.x = x;
		this.y = y;
		this.key = key;
	}
}
