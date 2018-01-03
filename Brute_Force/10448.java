import java.util.Scanner;

public class Main {

	public static int arr[] = new int[45];
	public static int V;
	public static boolean chk;
	
	private static void triangle_init()
	{
		for(int i=1; i<45; i++)
			arr[i] = i*(i+1)/2;
	}
	
	public static int eureka(int n)
	{
		for(int i=1; i<45; i++)
			for(int j=1; j<45; j++)
				for(int z=1; z<45; z++)
					if(arr[i] + arr[j] + arr[z] == n)
						return 1;
		return 0;
	}
	
	public static void main(String[] args)
	{	
		triangle_init();
		
		Scanner sc = new Scanner(System.in);

		int n, k;
		n = sc.nextInt();
		
		while(n-- > 0)
		{
			k = sc.nextInt();
			System.out.println(eureka(k));
		}
	
		sc.close();
	}
  }
