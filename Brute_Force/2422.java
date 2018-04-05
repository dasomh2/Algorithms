import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N,M,count;
	static int[] res = new int[3];
	static boolean[] visit[] = new boolean[10001][10001];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			visit[a][b] = visit[b][a] = true;
		}

		for(int i=1; i<N; i++) {
			res[0] = i;
			combination(i,1);
			res[0] = 0;
		}
		System.out.println(count);
	}
	private static void combination(int v, int cnt) {
		if(cnt == 3) {
			if(check(res[0], res[1]) && check(res[1], res[2]) && check(res[0], res[2])) {
				count++;
			}
			return;
		}
		else {
			for(int i=v+1; i<=N; i++) {
				res[cnt] = i;
				combination(i, cnt+1);
				res[cnt] = 0;
			}
		}
	}
	private static boolean check(int v1, int v2) {
		if(visit[v1][v2] && visit[v2][v1]) {
			return false;
		}
		return true;
	}
}
