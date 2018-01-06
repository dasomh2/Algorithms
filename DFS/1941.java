import java.util.Scanner;

public class Main {
	//12:30~
	static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
	static boolean[][] visit ;
	static char[][] classroom = new char[5][5];
	static boolean[][] map;
	static int total, adjacent, cnt;

	private static void round(int x, int y)
	{
		if(cnt == 7)
			total++;
		
		for(int i=0; i<4; i++)
		{
			int tmp_x = x + dx[i];
			int tmp_y = y + dy[i];

			if(tmp_x<0 || tmp_y<0 || tmp_x>4 || tmp_y>4)
				continue;
			
			if(!visit[tmp_x][tmp_y] && map[tmp_x][tmp_y])
			{//방문하지 않은 점이면서 dfs를 검사해야되는 점인지?
				visit[tmp_x][tmp_y] = true;
				cnt++;
				round(tmp_x, tmp_y);
			}
		}
	}
	private static void find(String[] arr)
	{
		int start = Integer.parseInt(arr[0]);
		
		for(int i=0; i<arr.length; i++)
		{
			int idx = Integer.parseInt(arr[i]);

			int x = idx/5;
			int y = idx%5;

			map[x][y] = true;
		}
		
		visit[start/5][start%5] = true;
		cnt=1;
		round(start/5, start%5);
	}

	private static void member(int v, int cnt, int s, String combi)
	{//making combination (7)
		if(classroom[v/5][v%5] == 'S')
			++s;

		if(cnt == 7)
		{
			if(s >= 4)
			{
				map = new boolean[5][5];
				visit = new boolean[5][5];
				cnt=0;
				//조합된 학생들이 해당 조건에 맞는지 검사하러 가야돼 
				String[] tmp = combi.split(" ");
				find(tmp);
			}
		}
		else
		{
			for(int i=v+1; i<25; i++)
			{
				member(i, cnt+1, s, combi+" "+i);
			}
		}
	}

	public static void main(String[] args)
	{	
		Scanner sc = new Scanner(System.in);

		for(int i=0; i<5; i++)
			classroom[i] = sc.next().toCharArray();

		for(int i=0; i<=18; i++)
			member(i, 1, 0, ""+i);

		System.out.println(total);
		sc.close();
	}
}
