import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, cnt;
	static int[][] map;
	static boolean[][] check;
	static Queue<Point> q = new LinkedList<>();

	private static int BFS()
	{
		int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
		int cnt=1;
		
		while(!q.isEmpty())
		{
			int x = q.peek().x;
			int y = q.poll().y;
			
			map[x][y] = -1;

			for(int i=0; i<4; i++)
			{
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(nx < 0 || ny < 0 || nx > N-1 || ny > N-1)
					continue;

				if(map[nx][ny] == 1 && !check[nx][ny])
				{
					q.offer(new Point(nx, ny));
					check[nx][ny] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];
		check = new boolean[N][N];
		
		ArrayList<Integer> list = new ArrayList<>();

		String tmp ="";
		
		for(int i=0; i<N; i++)
		{
			String s = sc.next();
			for(int j=0; j<N; j++)
				map[i][j] = Integer.parseInt(s.substring(j, j+1));
		}

		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				if(map[i][j] == 1)
				{
					q.offer(new Point(i,j));
					check[i][j] = true;
					list.add(BFS());
				}


		Object[] res = list.toArray();
		Arrays.sort(res);
		
		System.out.println(list.size());
		for(int i=0; i<res.length; i++)
			System.out.println(res[i]);
		sc.close();
	}
}
class Point {
	int x;
	int y;

	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
