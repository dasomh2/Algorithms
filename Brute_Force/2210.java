import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1} , map[];
	static Set<String> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[5][5];

		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				DFS(0, i, j, map[i][j]+"");
			}
		}
		System.out.println(set.size());
	}

	private static void DFS(int depth, int x, int y, String s) {
		if(depth == 5) {
			set.add(s);
			return;
		}

		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(isRange(nx, ny)) {
				DFS(depth+1, nx, ny, s+map[nx][ny]);
			}
		}
	}
	private static boolean isRange(int x, int y) {
		if(x<0 || y<0 || x>4 || y>4)
			return false;
		return true;
	}
}
