import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N,M,max;
	static boolean flag = false;
	static int[] square[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		square = new int[N][M];

		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				square[i][j] = s.charAt(j)-48;
			}
		}

		if(N==1 && M==1) {
			System.out.println("1");
			return;
		}

		max = Math.min(N,M);
		
		while(true)
		{
			divide(max);
			max--;
		}
	}
	private static void divide(int n) {

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(i+n-1 < N && j+n-1 < M && chk(i,j,n)) {
					System.out.println(n*n);
					System.exit(0);
				}
			}
		}
	}

	private static boolean chk(int x, int y, int n) {
		if((square[x][y] == square[x][y+n-1])
				&& square[x][y+n-1] == square[x+n-1][y]
						&& square[x+n-1][y] == square[x+n-1][y+n-1]) {
			return true;
		}
		return false;
	}
}
