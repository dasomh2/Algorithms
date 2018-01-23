import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static ArrayList<String> list = new ArrayList<>();
	static int N, K, word, max;
	static boolean[] alpha = new boolean[26];

	private static int check()
	{//list에 있는 단어들 읽을 수 있다 없다 체크하는 함수 
		int tmp_cnt=0;

		for(int i=0; i<list.size(); i++)
		{
			boolean flag = false;
			String s = list.get(i);

			for(int j=4; j<s.length()-4; j++)
			{
				char one = s.charAt(j);
				int idx = one - 'a';

				if(!alpha[idx])
				{
					flag = true;
					break;
				}
			}
			if(!flag)
				tmp_cnt++;
		}

		return tmp_cnt;
	}

	private static void recur(int v, int cnt)
	{	
		if(!alpha[v])
		{
			alpha[v] = true;

			if(cnt == word)
			{
				int tmp = check();
				max = Math.max(tmp, max);
			}
			else
			{
				for(int i=v+1; i<26; i++)
					recur(i, cnt+1);
			}
			alpha[v] = false;
		}

	}
	public static void main(String[] args)
	{	
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		word = K-5;

		for(int i=0; i<N; i++)
			list.add(sc.next());

		alpha[0] = true;
		alpha[2] = true;
		alpha[8] = true;
		alpha[13] = true;
		alpha[19] = true;

		if(word < 0)
		{
			System.out.println(0);
			return;
		}
        
        if(word == 0)
        {
            System.out.println(check());
            return;
        }
	
	for(int i=1; i<26; i++)
		recur(i, 1);
		
	System.out.println(max);
	sc.close();
	}
}
