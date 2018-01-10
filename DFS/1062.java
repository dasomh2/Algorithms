import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static int N, K, cnt, max;
	static ArrayList<String> list = new ArrayList<>();
	
	private static int combination(boolean[] arr)
	{
		int cnt = 0;
		
		for(int i=0; i<list.size(); i++)
		{
			String s = list.get(i);
			boolean flag = true;
			
			for(int j=0; j<s.length(); j++)
			{
				char word = s.charAt(j);
				int idx = word-'a';
				if(!arr[idx])
				{
					flag = false;
					break;
				}
			}
			if(flag)
				cnt++;
		}
		return cnt;
	}

	private static void find(int v, boolean[] arr)
	{
		arr[v-97] = true;
		
		if(cnt == K)
		{
			int tmp = combination(arr);
			max = Math.max(max, tmp);
		}
		else
		{
			for(int i=v+1; i<123; i++)
			{
				if(!arr[i-97])
				{
					cnt++;
					find(i, arr);
				}
			}
		}
		
		arr[v-97] = false;
		cnt--;
	}
	public static void main(String[] args)
	{	
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		//make 'word' list
		for(int i=0; i<N; i++)
		{
			String s = sc.next();
			list.add(s);
		}
		
		if(K == 0)
		{
			System.out.println(0);
			return;
		}

		int start = 97;
		
		for(int i=0; i<26-K; i++)
		{
			boolean alpha[] = new boolean[26];
			alpha[0] = true;
			alpha[2] = true;
			alpha[8] = true;
			alpha[13] = true;
			alpha[19] = true;
			cnt=5;
			if(!alpha[i])
				cnt++;
			find(start, alpha);
			start++;
		}
		System.out.println(max);
		sc.close();
	}
}
