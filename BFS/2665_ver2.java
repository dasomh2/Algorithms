import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

	static int N;
	static char[] map[];
	static boolean[] visit[];
	static PriorityQueue<Point> q = new PriorityQueue<>();

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		visit = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		q.offer(new Point(0,0,0));
		visit[0][0] = true;
		BFS();
		
		br.close();
	}
	
	private static void BFS() 
	{
		int[] dx = {-1,0,1,0}, dy = {0,-1,0,1};
		
		while(!q.isEmpty())
		{
			int x = q.peek().x;
			int y = q.peek().y;
			int cnt = q.poll().cnt;
			
			//visit[x][y] = true;
			
			for(int i=0; i<4; i++)
			{
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(x == N-1 && y == N-1)
				{
					System.out.println(cnt);
					return;
				}
				if(nx<0 || ny<0 || nx>N-1 || ny>N-1 || visit[nx][ny])
					continue;
				
				if(map[nx][ny] == '0') {
					visit[nx][ny] = true;
					q.offer(new Point(nx, ny, cnt+1));
				}
				else if(map[nx][ny] == '1') {
					visit[nx][ny] = true;
					q.offer(new Point(nx, ny, cnt));
				}
			}
		}
	}
}
class Point implements Comparable<Point>{
	int x;
	int y;
	int cnt;
	
	public Point(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Point o) {
		return cnt-o.cnt;
	}
}
