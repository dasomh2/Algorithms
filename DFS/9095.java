import java.util.Scanner;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	static int cnt;
	static String result;
	
	private static void recur(int num, int sum, String combi)
	{//num : 4 / sum : combination
		if(num == sum)
		{
			cnt++;
			sb.append(combi+"\n");
		}
		else
		{
			for(int i = 1; i<=3; i++)
			{
				if(sum > num)
					return;
				recur(num ,sum+i, combi+"+"+i);
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		arr = new int[n];
		
		for(int i=0; i<n; i++)
			arr[i] = sc.nextInt();
		
		for(int i=0; i<arr.length; i++)
		{
			for(int j=1; j<=3; j++)
				recur(arr[i], j, j+"");
			System.out.println(cnt);
			cnt=0;
		}
		
		sc.close();
	}
  }
