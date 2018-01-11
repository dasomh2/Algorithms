import java.util.Scanner;

public class Main {

	static boolean[] arr[];
	static int N;
	
	private static void map()
	{
		for(int k=0; k<N; k++)
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++)
					if(arr[i][k] && arr[k][j])
						arr[i][j] = true;
	}
	
	public static void main(String[] args)
	{	
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		
		arr = new boolean[N][N];
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
			{
				int tmp = sc.nextInt();
				if(tmp == 1)
					arr[i][j] = true;
			}
		
		map();
		
		for(int i=0; i<N; i++)
		{	
			for(int j=0; j<N; j++)
				System.out.print(arr[i][j] ? 1+" " : 0+" ");
			System.out.println();
		}
		sc.close();
	}
}
