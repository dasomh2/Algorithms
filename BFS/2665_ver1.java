import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.PriorityQueue;

public class Main {

	static int N, INF=2000001;
	static char[] map[];
	static int[] dp[];
	static PriorityQueue<Point> q = new PriorityQueue<>();

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		dp = new int[N][N];
		
		for(int[] dp : dp)
			Arrays.fill(dp, -1);
		
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		q.offer(new Point(0,0,0));
		dp[0][0] = 0;
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

			for(int i=0; i<4; i++)
			{
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx == N-1 && ny == N-1)
				{
					System.out.println(dp[x][y]);
					return;
				}
				
				if(nx<0 || ny<0 || nx>N-1 || ny>N-1 || dp[nx][ny] != -1)
					continue;
				
				if(map[nx][ny] == '0') {
					dp[nx][ny] = dp[x][y] + 1;
					q.offer(new Point(nx, ny, cnt+1));
				}
				else if(map[nx][ny] == '1') {
					dp[nx][ny] = dp[x][y];
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
