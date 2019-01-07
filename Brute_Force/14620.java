import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	static int[] map[], dx = {-1,0,1,0}, dy = {0,1,0,-1};
	static int[] visit[];
	static int MIN = Integer.MAX_VALUE, N, sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new int[N][N];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		findWay(0);
		System.out.println(MIN);
	}
	private static void findWay(int depth) {

		if(depth == 3) {
			MIN = Math.min(MIN, sum);
			return;
		}

		for(int i=1; i<N-1; i++) {
			for(int j=1; j<N-1; j++) {
				if(visit[i][j] == 0) {
					
					visit[i][j] = 1;
					sum += map[i][j];
					
					int chk = 0;
					
					for(int d=0; d<4; d++) {
						if(visit[i+dx[d]][j+dy[d]]==0) {
							chk++;
						}
						else
							break;
					}
					
					if(chk == 4) {
						for(int d=0; d<4; d++) {
							visit[i+dx[d]][j+dy[d]] = visit[i][j]+1;
							sum += map[i+dx[d]][j+dy[d]];
						}
						findWay(depth+1);
						for(int d=0; d<4; d++) {
							visit[i+dx[d]][j+dy[d]] = 0;
							sum -= map[i+dx[d]][j+dy[d]];
						}	
					}
					visit[i][j] = 0;
					sum -= map[i][j];
				}
			}
		}
	}
}
