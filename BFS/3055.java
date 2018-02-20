import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static char[] map[];
	static int[] visit[];
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1}, S = new int[2];

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visit = new int[R][C];

		for(int i=0; i<R; i++)
		{	
			String s = br.readLine();
			for(int j=0; j<C; j++)
			{
				map[i][j] = s.charAt(j);
				if(map[i][j] == '*')
				{
					q.add(new Point(i,j, "*"));
				}
				if(map[i][j] == 'S')
				{
					S[0] = i;
					S[1] = j;
				}
			}
		}
		q.add(new Point(S[0], S[1], "S"));
		visit[S[0]][S[1]] = 1;
		BFS();

		br.close();
	}

	private static void BFS()
	{
		while(!q.isEmpty())
		{	
			String type = q.peek().type;
			int x = q.peek().x;
			int y = q.poll().y;

			for(int i=0; i<4; i++)
			{
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(nx<0 || ny<0 || nx>R-1 || ny>C-1)
					continue;

				if(type == "*")
				{
					if(map[nx][ny] == '.')
					{
						map[nx][ny] = '*';
						q.offer(new Point(nx, ny, "*"));
					}
				}
				else if(type == "S")
				{
					if(x == S[0] && y == S[1])
					{
						map[S[0]][S[1]] = '.';
					}

					if(map[nx][ny] == 'D')
					{
						visit[nx][ny] = visit[x][y] + 1;
						System.out.println(visit[nx][ny]-1);
						return;
					}

					if(map[nx][ny] == '.' && visit[nx][ny] == 0)
					{	
						visit[nx][ny] = visit[x][y] + 1;
						q.offer(new Point(nx, ny, "S"));

					}
				}

			}
		}
		System.out.println("KAKTUS");
	}

}
class Point {
	int x;
	int y;
	String type;

	public Point(int x, int y, String type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
