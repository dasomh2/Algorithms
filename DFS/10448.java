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
	
	public static void recur(int v, int sum, int cnt, String result)
	{
		if(sum == 0 && cnt == 3)
		{
			chk = true;
			return;
		}
		else
		{
			for(int i=v; i>0; i--)
			{
				if(sum < 0 || chk)
					return;
				recur(i, sum-arr[i], cnt+1, result+" "+i);
			}
		}
	}
	
	public static void main(String[] args)
	{
		triangle_init();
		
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for(int i=0; i<T; i++)
		{
			int K = sc.nextInt();
			for(int j=1; j<arr.length; j++)
			{
				if(arr[j] >= K)
				{
					V = j-1;	
					break;
				}
				else if(K > 990)
				{
					V = 44;
					break;
				}
			}
			for(int j=V; j>0; j--)
			{
				if(chk)
					break;
				recur(j, K-arr[j], 1, j+"");
			}
			System.out.println(chk ? "1" : "0");
			chk = false;
		}

		sc.close();
	}
  }
