import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, K;
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		q.add(N);
		
		int visit[] = new int[200001];
		
		visit[N] = -1;
		
		while(!q.isEmpty())
		{
			int x = q.poll();
			if(x == K)
			{
				System.out.println(visit[K]+1);
				System.exit(0);
			}
			else
			{
				if(x-1 >= 0 && visit[x-1] == 0)
				{
					visit[x-1] = visit[x]+1;
					q.add(x-1);
				}
				if(x+1 <= 100000 && visit[x+1] == 0)
				{
					visit[x+1] = visit[x]+1;
					q.add(x+1);
				}
				if(x*2 <= 100000 && visit[x*2] == 0)
				{
					visit[x*2] = visit[x]+1;
					q.add(x*2);
				}
			}
		}

		sc.close();
	}
}
