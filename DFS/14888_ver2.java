import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] op = new int[4], numbers;

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
				op[i] = Integer.parseInt(st.nextToken());;
		}

		DFS(1, numbers[0]);
		System.out.println(max+"\n"+min);
	}
	private static void DFS(int idx, int result) {
		
		if(idx == N) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}

		if(op[0] > 0) {
			op[0]--;
			DFS(idx+1, result+numbers[idx]);
			op[0]++;
		}
		if(op[1] > 0) {
			op[1]--;
			DFS(idx+1, result-numbers[idx]);
			op[1]++;
		}
		if(op[2] > 0) {
			op[2]--;
			DFS(idx+1, result*numbers[idx]);
			op[2]++;
		}
		if(op[3] > 0) {
			op[3]--;
			DFS(idx+1, result/numbers[idx]);
			op[3]++;
		}
	}
}
