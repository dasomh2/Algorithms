import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

//5:49~
	
	static boolean[] visit[];
	static char[] maze[];
	static int M,N;
	static PriorityQueue<Point> q = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		maze = new char[N][M];
		visit = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				maze[i][j] = s.charAt(j);
			}
		}

		BFS();
	}
	
	private static void BFS() {
		int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
		q.add(new Point(0,0,0));
		visit[0][0] = true;
		
		while(!q.isEmpty()) {
			
			Point p = q.poll();
			
			if(p.x == N-1 && p.y == M-1) {
				System.out.println(p.wall);
				return;
			}
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || visit[nx][ny])		continue;

				if(maze[nx][ny] == '0') {
					visit[nx][ny] = true;
					q.add(new Point(p.wall, nx, ny));
				}
				else if(maze[nx][ny] == '1') {
					visit[nx][ny] = true;
					q.add(new Point(p.wall+1 ,nx, ny));
				}
				
			}
		}
	}

}
class Point implements Comparable<Point>{
	int x;
	int y;
	int wall;
	
	public Point(int wall, int x, int y) {
		this.wall = wall;
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		return wall-o.wall;
	}
}
