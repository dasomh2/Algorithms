import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int M, N, K, cnt;
	static int[] map[], dx={-1,0,1,0}, dy={0,1,0,-1};

	private static void DFS(int x, int y)
	{
		map[x][y] = -1;
		
		for(int i=0; i<4; i++)
		{
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny < 0 || nx > M-1 || ny > N-1)
				continue;
			
			if(map[nx][ny] == 0)
			{
				cnt++;
				DFS(nx, ny);
			}
		}
	}
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);

		M = sc.nextInt();
		N = sc.nextInt();
		K = sc.nextInt();

		map = new int[M][N];

		int x1, x2, y1, y2;
		
		for(int i=0; i<K; i++)
		{
			x1 = sc.nextInt();
			y1 = sc.nextInt();
			
			x2 = sc.nextInt();
			y2 = sc.nextInt();
			
			for(int p = y1; p < y2; p++)
				for(int q = x1; q < x2; q++)
					map[p][q]++;
		}
			
		
		ArrayList list = new ArrayList();
		
		for(int i=0; i<M; i++)
		{	
			for(int j=0; j<N; j++)
			{	
				if(map[i][j] == 0)
				{
					cnt=1;
					DFS(i,j);
					list.add(cnt);
				}
			}
		}

		Object[] res = list.toArray();
		
		System.out.println(list.size());
		Arrays.sort(res);
		for(int i=0; i<res.length; i++)
			System.out.print(res[i]+" ");

		sc.close();
	}
}
