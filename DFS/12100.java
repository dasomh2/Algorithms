import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, max;
	static int[] map[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
			}
		}
		dfs(0);
		System.out.println(max);
	}
	private static void dfs(int cnt) {
		if(cnt==5) {
			return;
		}
		else {
			int[][] copy = new int[N][N];
			copy(copy, map);

			for(int i=0; i<4; i++) {
				do_2048(i);
				dfs(cnt+1);
				copy(map, copy);
			}
		}
	}
	private static void do_2048(int d) {
		Queue<Integer> q = new LinkedList<>();

		switch(d) {
		case 0://up
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[j][i] != 0) {
						q.add(map[j][i]);
						map[j][i] = 0;
					}
				}
				int idx=0;
				while(!q.isEmpty()) {

					int pop = q.poll();

					if(map[idx][i] == 0) {
						map[idx][i] = pop;
					}
					else if(map[idx][i] == pop) {
						map[idx][i] += pop;
						max = Math.max(max, map[idx++][i]);
					}
					else
						map[++idx][i] = pop;
				}
			}
			break;

		case 1://down
			for(int i=0; i<N; i++) {
				for(int j=N-1; j>=0; j--) {
					if(map[j][i] != 0) {
						q.add(map[j][i]);
						map[j][i] = 0;
					}
				}
				int idx=N-1;
				while(!q.isEmpty()) {

					int pop = q.poll();

					if(map[idx][i] == 0) {
						map[idx][i] = pop;
					}
					else if(map[idx][i] == pop) {
						map[idx][i] += pop;
						max = Math.max(max, map[idx--][i]);
					}
					else
						map[--idx][i] = pop;
				}
			}
			break;

		case 2://left
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] != 0) {
						q.add(map[i][j]);
						map[i][j] = 0;
					}
				}
				int idx=0;
				while(!q.isEmpty()) {

					int pop = q.poll();

					if(map[i][idx] == 0) {
						map[i][idx] = pop;
					}
					else if(map[i][idx] == pop) {
						map[i][idx] += pop;
						max = Math.max(max, map[i][idx++]);
					}
					else
						map[i][++idx] = pop;
				}
			}
			break;
		case 3://right
			for(int i=0; i<N; i++) {
				for(int j=N-1; j>=0; j--) {
					if(map[i][j] != 0) {
						q.add(map[i][j]);
						map[i][j] = 0;
					}
				}
				int idx=N-1;
				while(!q.isEmpty()) {

					int pop = q.poll();

					if(map[i][idx] == 0) {
						map[i][idx] = pop;
					}
					else if(map[i][idx] == pop) {
						map[i][idx] += pop;
						max = Math.max(max, map[i][idx--]);
					}
					else
						map[i][--idx] = pop;
				}
			}
			break;
		}
	}
	private static void copy(int[][] arr, int[][] arr2) {//arr2를 arr1에 복사 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				arr[i][j] = arr2[i][j];
			}
		}
	}
}
