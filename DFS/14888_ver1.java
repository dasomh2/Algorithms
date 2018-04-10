import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] op = new int[11], numbers;
	static boolean[] visit = new boolean[11];

	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		int idx=0;

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			int total = Integer.parseInt(st.nextToken());
			for(int j=0; j<total; j++) {
				op[idx++] = i+1;
			}
		}

		DFS(numbers[0], 1, 0);
		System.out.println(max+"\n"+min);
	}
	private static void DFS(int num, int next, int cnt) {

		int sum = 0;

		if(cnt == N-1) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}

		for(int i=0; i<N-1; i++) {
			if(!visit[i]) {
				visit[i] = true;
				sum = calculation(op[i], num, numbers[next]);
				DFS(sum, next+1, cnt+1);
				visit[i] = false;
			}
		}
	}
	private static int calculation(int cal, int sum, int num) {
		int result = 0;
		
		switch(cal) {
		case 1:
			result = sum+num;
			break;
		case 2:
			result = sum-num;
			break;
		case 3:
			result = sum*num;
			break;
		case 4:
			result = sum/num;
			break;
		}
		
		return result;
	}
}
