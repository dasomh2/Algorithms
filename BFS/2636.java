import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	//8:22~

	static int[] panel[];
	static int row, col;
	static final int HOLE = -1;
	static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
	static Queue<Element> melt = new LinkedList<>();
	static boolean[] check[] ;

	private static void outside()
	{//Turn into '-1' if it's outside's air
		Queue<Element> air = new LinkedList<>();
		boolean[] visit[] = new boolean[row][col];
		
		air.offer(new Element(0,0));
		visit[0][0] = true;

		while(!air.isEmpty())
		{
			int x = air.peek().x;
			int y = air.poll().y;

			panel[x][y] = -1;

			for(int i=0; i<4; i++)
			{
				int tmp_x = x + dx[i];
				int tmp_y = y + dy[i];

				if(tmp_x < 0 || tmp_y < 0 || tmp_x > row-1 || tmp_y > col-1)
					continue;

				if(panel[tmp_x][tmp_y] <= 0 && !visit[tmp_x][tmp_y])
				{
					air.offer(new Element(tmp_x, tmp_y));
					visit[tmp_x][tmp_y] = true;
				}
			}
		}
	}
	private static void bfs(int x, int y)
	{
		Queue<Element> q = new LinkedList<>();
		q.offer(new Element(x, y));
		
		while(!q.isEmpty())
		{
			x = q.peek().x;
			y = q.poll().y;

			check[x][y] = true;

			if(isEdge(x, y))
			{
				panel[x][y] = 2;
				melt.offer(new Element(x, y));
			}
			for(int i=0; i<4; i++)
			{
				int tmp_x = x + dx[i];
				int tmp_y = y + dy[i];

				if(tmp_x < 0 || tmp_y < 0 || tmp_x > row-1 || tmp_y > col-1)
					continue;

				if(panel[tmp_x][tmp_y] == 1 && !check[tmp_x][tmp_y])
				{
					q.offer(new Element(tmp_x, tmp_y));
					check[tmp_x][tmp_y] = true;
				}
			}
		}

	}
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);

		row = sc.nextInt();
		col = sc.nextInt();

		panel = new int[row][col];
		check = new boolean[row][col];

		int howMany=0, time=0;
		
		for(int i=0; i<row; i++)
			for(int j=0; j<col; j++)
			{
				panel[i][j] = sc.nextInt();
				if(panel[i][j] == 1)
					howMany++;
			}

		sc.close();
		
		while(true)
		{//outside : -1, cheese : 1, inside : 0
			outside();

			for(int i=1; i<row-1; i++)
				for(int j=1; j<col-1; j++)
					if(panel[i][j] == 1 && isEdge(i,j))
						bfs(i,j);

			int cnt = melt.size();
			
			if(cnt == 0)
				break;
			else
			{
				if(howMany-cnt != 0)
					howMany -= cnt;
				time++;
			}
			
			while(!melt.isEmpty())
				panel[melt.peek().x][melt.poll().y] = -1;
		}
		
		System.out.println(time+"\n"+howMany);
	}
	private static boolean isEdge(int x, int y)
	{
		for(int i=0; i<4; i++)
			if(panel[x+dx[i]][y+dy[i]] == -1)
				return true;
		return false;
	}
}
class Element
{
	int x;
	int y;

	public Element(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
