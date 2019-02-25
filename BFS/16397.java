import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	static int[][] map = new int[200002][2];
	static boolean[] visit = new boolean[200002];
	static Queue<Integer> q;
	static int N, T, G;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());

		q = new LinkedList<>();
		q.add(N);
		visit[N] = true;

		for(int i=0; i<=T; i++) {

			int qsize = q.size();
			for(int a=0; a<qsize; a++) {

				int num = q.poll();

				if(num == G) {
					System.out.println(i);
					return;
				}
				escape(num);
			}
		}
		System.out.println("ANG");
	}
	private static void escape(int num) {
		int sum = 0;

		sum = num+1;
		if(sum <= 99999 && !visit[sum]) {
			visit[sum] = true;
			q.add(sum);
		}

		if(num*2 <= 99999) {
			String tmp = String.valueOf(num*2);
			char first = tmp.charAt(0);

			if(first != '0') {
				int f =	(first-'0') - 1;
				String newNum = String.valueOf(f);

				for(int i=1; i<tmp.length(); i++) {
					newNum += tmp.substring(i, i+1);
				}

				sum = Integer.parseInt(newNum);

				if(sum <= 99999 && !visit[sum]) {
					visit[sum] = true;
					q.add(sum);
				}
			}
		}
	}
}
