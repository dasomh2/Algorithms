import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static char[] map[];
	static int[] arr[], dist[], dx={-1,0,1,0}, dy={0,1,0,-1};
	static int[] S = new int[2], F = new int[2];
	static PriorityQueue<Point> q = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		arr = new int[N][M];
		dist = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++ ) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'g') {
					arr[i][j] = 3000;
					q.add(new Point(0,i,j));
				}
				else if(map[i][j] == 'S') {
					S[0] = i;
					S[1] = j;
				}
				else if(map[i][j] == 'F') {
					F[0] = i;
					F[1] = j;
				}
			}
		}
		
		//spread trash
		Trash();
		
		for(int[] dist : dist)
			Arrays.fill(dist, -1);
		
		//Find the fastest way to go to 'F'
		q.add(new Point(0,S[0], S[1]));
		dist[S[0]][S[1]] = 0;
		BFS();
	}

	private static void BFS() {
		
		while(!q.isEmpty()) {
			
			int x = q.peek().x;
			int y = q.peek().y;
			int g = q.poll().g;
			
			if(x == F[0] && y == F[1]) {
				System.out.println(dist[x][y]/3000+" "+dist[x][y]%3000);
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				
				if(chk(nx, ny) && dist[nx][ny] == -1) {
					dist[nx][ny] = g+arr[nx][ny];
					q.add(new Point(g+arr[nx][ny], nx, ny));
				}
			}
		}
	}
	private static void Trash() {
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.poll().y;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i] ;
				int ny = y + dy[i] ;
				
				if(chk(nx,ny) && map[nx][ny] == '.')
					arr[nx][ny] = 1;
			}
		}
	}
	private static boolean chk(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
}
class Point implements Comparable<Point> {
	int x;
	int y;
	int g;
	
	public Point(int g, int x, int y) {
		this.g = g;
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		return g-o.g;
	}
}
