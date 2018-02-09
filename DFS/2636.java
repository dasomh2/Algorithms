import java.util.Scanner;

public class Main {

	static int n,m, cheese;
	static int[] map[] ;
	static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};

	static void dfs(int x, int y)
	{
		map[x][y] = -1;
		int ax, ay;
		
		for(int i=0; i<4; i++)
		{
			ax = x + dx[i];
			ay = y + dy[i];
			
			if(ax < 0 || ax > n-1 || ay < 0 || ay > m-1)
				continue;
			
			//외부공기만 dfs -> 어차피 내부공기는 외부공기와 만나지 않아서 dfs안함 
			if (map[ax][ay] == 0) 
				dfs(ax, ay);
			else if(map[ax][ay] > 0)
				map[ax][ay]++;
		}
	}
	
	private static boolean melt()
	{
		int i, j, cnt=0;
		for(i=0; i<n; i++)
			for(j=0; j<m; j++)
				if(map[i][j] == 1)
				{//안 녹은 치즈 1로 원상복귀 
					map[i][j] = 1;
					cnt++;
				}
				else
				{//공기 (-1) 들 다시 0으로 
					map[i][j] = 0;
				}
		
		if(cnt != 0)
		{
			cheese = cnt;
			return false;
		}
		else
			return true;
	}
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		
		int time=0;
		
		map = new int[n][m];
		
		for(int i=0; i<n; i++)
		{	
			for(int j=0; j<m; j++)
				map[i][j] = sc.nextInt();
			
		}
		for(time=0; !melt(); time++)
		{
			dfs(0,0);
		}

		System.out.println(time+"\n"+cheese);

		sc.close();
	}
}
