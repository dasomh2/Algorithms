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
	/*static char[] alpha;
	static int L,C;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	private static void func(int v, int cnt, String s)
	{
		//방문체크를 위해서 'a'만큼 빼면 순서대로 0부터 체크할 수 있음 
		int idx = alpha[v] - 'a';
		
		//해당 알파벳 방문했다고 체크 
		visited[idx] = true;
		
		//길이 L만큼 문자 완성했니? 
		if(L == cnt)
		{
			if(isSatisfy())
				sb.append(s+"\n");
		}
		//L만큼 안됐으면 조합 마저 해야지 
		else
		{
			// 조합들 마다 알파벳 개수만큼 반복해야되니까 C까지 카운트함
			//v부터 하는거는 조합할 알파벳 가리켜야 하니까  
			for(int i=v+1; i<C; i++)
			{
				//해당 알파벳 방문했니?
				//방문했으면 카운트 하나 올려주고 기존 문자에 더하자 
				if(!visited[alpha[i]-'a'])
					func(i, cnt+1, s+alpha[i]);
				
			}
		}
		
		visited[idx] = false;
	}
	
	private static boolean isSatisfy()
	{
		int vowel = 0, cons=0;
		
		if(visited[0])
			++vowel;
		if(visited[4])
			++vowel;
		if(visited[8])
			++vowel;
		if(visited[14])
			++vowel;
		if(visited[20])
			++vowel;
		
		for(int i=0; i<26; i++)
		{
			if(i==0 || i==4 || i==8 || i==14 || i == 20)
				continue;
			if(visited[i])
				++cons;
		}
		
		if(vowel > 0 && cons > 1)
			return true;
		return false;
	}
	
	public static void main(String[] args)
	{//increasing sequence
		Scanner sc = new Scanner(System.in);
		
		L = sc.nextInt();
		C = sc.nextInt();
		
		alpha = new char[C];
		
		//배열에 알파벳들 넣어놓는다 
		for(int i=0; i<C; i++)
			alpha[i] = sc.next().charAt(0);
		
		//알파벳 순으로 정렬한다 
		Arrays.sort(alpha);
		
		//visited는 해당 알파벳을 방문했는지 체크(조합마다 체크해야되니까 매번 초기화)
		/*왜 C-L? 어차피 총 C개중에 L개의 조합 만들어야 되는데 이미 정렬해놓은 상태에
		  서 시작하니까 전부다 돌 필요가 없음 그래서 C-L 번째까지만 체크해도 된다
		  C-1번째까지 해도 되는데 시간낭비자냐..  
		
		for(int i=0; i<= C-L; i++)
		{
			visited = new boolean[26];
			//0번째부터 시작 (s에 첫번째 문자 저장해둠 - cnt=1부터 )
			func(i, 1, ""+alpha[i]);
		}
		
		System.out.println(sb.toString());
		
		sc.close();
	}*/
	
}