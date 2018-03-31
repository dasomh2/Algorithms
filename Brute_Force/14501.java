import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, max;
	static int[] arr[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][2];

		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int period = Integer.parseInt(st.nextToken());
			int money = Integer.parseInt(st.nextToken());

			arr[i][0] = period;
			arr[i][1] = money;

		}
		for(int i=1; i<=N; i++) {
			Work(i, arr[i][0], arr[i][1]);
		}
		System.out.println(max);
	}
	private static void Work(int start, int day, int pay) {
		if(start+day-1 > N) {
			return;
		}
		for(int i=start+day; i<=N; i++) {
			Work(i, arr[i][0], pay+arr[i][1]);
		}
		if(pay > max) {
			max = pay;
		}
	}
}
