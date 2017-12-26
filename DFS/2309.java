import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int[] dwarf = new int[9];
	static boolean chk;
	static boolean[] number = new boolean[101];
	
	private static void func(int v, int cnt, String s, int sum)
	{
		int idx = dwarf[v];
		number[idx] = true;
		
		if(cnt == 7)
		{
			if(sum == 100) 
			{
				sb.append(s);
				chk = true;
				return;
			}
		}
		else
		{
			for(int i=v+1; i<9; i++)
			{
				if(chk)
					return;
				func(i, cnt+1, s+dwarf[i]+"\n", sum+dwarf[i]);
			}
		}
		number[idx] = false;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<9; i++)
			dwarf[i] = sc.nextInt();
		
		Arrays.sort(dwarf);

		func(0, 1, dwarf[0]+"\n", dwarf[0]);
		
		System.out.println(sb.toString());
		//String result = sb.toString();
		
		sc.close();
	}
}
