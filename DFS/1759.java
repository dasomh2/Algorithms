import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static char[] alpha;
	static int L, C;
	
	private static boolean condition(String s)
	{
		int consonant=0, vowel=0, idx=0;
		
		for(int i=0; i<s.length(); i++)
		{
			char tmp = s.charAt(i);
			idx = tmp-'a';
			
			if(idx == 0 || idx == 4 || idx == 8 || idx == 14 || idx == 20)
				vowel++;
			else
				consonant++;
		}
		
		if(consonant >= 2 && vowel >= 1)
			return true;
		else
			return false;
	}
	
	private static void dfs(int v, int cnt, String s)
	{
		if(cnt == L)
		{
			if(condition(s))
				sb.append(s+"\n");
		}
		else
		{
			for(int i=v+1; i<C; i++)
			{
				dfs(i, cnt+1, s+alpha[i]);
			}
		}
		
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		L = sc.nextInt();
		C = sc.nextInt();
		
		alpha = new char[C];
		
		for(int i=0; i<C; i++)
			alpha[i] = sc.next().charAt(0);
		
		Arrays.sort(alpha);

		for(int i=0; i<=C-L; i++)
		{
			dfs(i, 1, ""+alpha[i]);
		}
		
		System.out.println(sb.toString());
		
		sc.close();
	}
}
