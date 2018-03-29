import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, K, F;
	static boolean[] check[];
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());

		int v1=0, v2=0;

		check = new boolean[N+1][N+1];

		for(int i=0; i<F; i++) {
			st = new StringTokenizer(br.readLine());

			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());

			check[v1][v2] = check[v2][v1] = true;
		}

		for(int i=1; i<=N-K+1; i++) {
			list = new ArrayList<>();
			combination(i);
		}
		System.out.println(-1);
		br.close();
	}
	private static void combination(int v) {

		list.add(v);

		if(list.size() == K) {
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
			System.exit(0);
		}
		else {
			for(int i=v+1; i<=N; i++) {
					boolean flag = true;
					for(int j=0 ;j<list.size(); j++) { 
						if(!check[i][list.get(j)]) {
							flag = false;
							break;
						}
					}
					if(flag) {
						combination(i);
					}
			}
		}
	}
}
