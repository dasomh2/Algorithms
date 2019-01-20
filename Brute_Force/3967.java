import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static char[] map[];
	static boolean[] visit = new boolean[12];
	static ArrayList<Point> arr = new ArrayList<>();
	static int N=5, M=9, cnt=0;
	static boolean isFinish = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st;

		map = new char[N][M];

		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				char cur = s.charAt(j);

				if(cur >= 'A' && cur <='L') {
					int d = cur - 'A';
					visit[d]= true;
				}
				else if(cur == 'x' ) {
					arr.add(new Point(i,j));
					cnt++;
				}
				map[i][j] = cur;
			}
		}
		dfs(0, 0);
	}
	private static void dfs(int depth, int loc) {
		if(isFinish) {
			return;
		}

		if(depth == cnt) {
			if(check()) {
				isFinish = true;
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						System.out.print(map[i][j]);
					}
					System.out.println();
				}
			}
			return;
		}

		for(int i=0; i<12; i++) {
			if(!visit[i]) {//0+'A' = 65, (char) (0+65) = A
				visit[i] = true;
				map[arr.get(loc).x][arr.get(loc).y] = (char) (i + 'A');
				dfs(depth+1, loc+1);
				map[arr.get(loc).x][arr.get(loc).y] = 'x';
				visit[i] = false;
			}

		}
	}

	private static boolean check() {
		int sum = (map[0][4]-'A') + (map[1][3]-'A') + (map[2][2]-'A') + (map[3][1]-'A');
		if(sum != 22) {
			return false;
		}

		sum = (map[3][1]-'A') + (map[3][3]-'A') + (map[3][5]-'A') + (map[3][7]-'A');
		if(sum != 22) {
			return false;
		}
		
		sum = (map[3][7]-'A') + (map[2][6]-'A') + (map[1][5]-'A') + (map[0][4]-'A');
		if(sum != 22) {
			return false;
		}

		sum = (map[1][1]-'A') + (map[2][2]-'A') + (map[3][3]-'A') + (map[4][4]-'A');
		if(sum != 22) {
			return false;
		}

		sum = (map[4][4]-'A') + (map[3][5]-'A') + (map[2][6]-'A') + (map[1][7]-'A');
		if(sum != 22) {
			return false;
		}

		sum = (map[1][7]-'A') + (map[1][5]-'A') + (map[1][3]-'A') + (map[1][1]-'A');
		if(sum != 22) {
			return false;
		}

		return true;
	}
}
class Point {
	int x,y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
