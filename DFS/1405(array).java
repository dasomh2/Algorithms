import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static boolean[] visit[] = new boolean[30][30];
	static double result;
	static double[] prob = new double[4];
	static int[] dx= {0,0,1,-1}, dy= {1,-1,0,0};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int cnt=0;
		N = Integer.parseInt(st.nextToken());

		while(st.hasMoreTokens()) {
			prob[cnt++] = (double) (Integer.parseInt(st.nextToken()))/100;
		}

		visit[15][15] = true;
		dfs(15, 15, 0, 1.0);

		System.out.println(result);
	}
	private static void dfs(int x, int y, int n, double p) {

		
		if(n == N) {
			result += p;
			return;
		}
		else {
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(!visit[nx][ny]) {//list에 없으면 
					visit[nx][ny] = true;
					dfs(nx, ny, n+1, p*prob[i]);
					visit[nx][ny] = false;
				}
			}
			
		}
	}
}
