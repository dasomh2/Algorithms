import java.util.Scanner;

public class Main {

	static int N, S, total;
	static int[] arr;

	private static void find(int v, int sum)
	{

		for(int i=v; i<N; i++)
		{
			sum += arr[i];
			
			if(sum == S)
				total++;
			
			find(i+1, sum);
			
			sum -= arr[i];
		}

	}

	public static void main(String[] args)
	{	
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		S = sc.nextInt();

		arr = new int[N];

		for(int i=0; i<N; i++)
			arr[i] = sc.nextInt();
        
		find(0, 0);

		System.out.println(total);
		sc.close();
	}
}
