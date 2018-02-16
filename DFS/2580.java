import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	static int[] map[] = new int[9][9];
	static boolean[] ch[], cv[], cx[];
	static final int N = 9;

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line;

		ch = new boolean[N][10];
		cv = new boolean[N][10];
		cx = new boolean[N][10];

		int tmp;

		for(int i=0; i<N; i++)
		{	
			line = br.readLine().split(" ");
			for(int j=0; j<N; j++)
			{
				map[i][j] = tmp = Integer.parseInt(line[j]);

				if(map[i][j] != 0)
				{
					ch[i][tmp] = true;
					cv[j][tmp] = true;
					cx[box(i,j)][tmp] = true;
				}
			}
		}
		br.close();

		sudoku(0);
	}

	private static void sudoku(int d)
	{
		if(d == 81)
		{
			for(int i=0; i<N; i++)
			{	
				for(int j=0; j<N; j++)
					System.out.print(map[i][j]+" ");
				System.out.println();
			}
			return;
		}

		int x = d/N;
		int y = d%N;

		if(map[x][y] != 0)
			sudoku(d+1);
		else
		{
			for(int num=1; num<=N; num++)
			{
				if(!ch[x][num] && !cv[y][num] && !cx[box(x,y)][num])
				{
					ch[x][num] = cv[y][num] = cx[box(x,y)][num] = true;
					map[x][y] = num;

					sudoku(d+1);

					map[x][y] = 0;
					ch[x][num] = cv[y][num] = cx[box(x,y)][num] = false;
				}
			}
		}

	}

	private static int box(int x, int y)
	{
		return (x/3)*3 + (y/3);
	}
}
