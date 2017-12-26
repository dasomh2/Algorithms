import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int[] dwarf = new int[9];
    	static int sum;
	
	private static void func()
	{
		for(int i=0; i<8; i++)
			for(int j=i+1; j<9; j++)
			{
				if(sum - (dwarf[i]+dwarf[j]) == 100)
				{
					dwarf[i] = -1;
					dwarf[j] = -1;
					return;
				}
			}
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<9; i++)
		{
			dwarf[i] = sc.nextInt();
			sum += dwarf[i];
		}
		
		Arrays.sort(dwarf);
		func();
		
		for(int i=0; i<dwarf.length; i++)
		{
			if(dwarf[i] != -1)
				System.out.println(dwarf[i]);
		}
		
		sc.close();
	}
}
