import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static double result;
	static double[] prob = new double[4];
	static int[] dx= {0,0,1,-1}, dy= {1,-1,0,0};
	static ArrayList<Point> list;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int cnt=0;
		N = Integer.parseInt(st.nextToken());

		while(st.hasMoreTokens()) {
			prob[cnt++] = (double) (Integer.parseInt(st.nextToken()))/100;
		}

		list = new ArrayList<>();
		list.add(new Point(0,0));

		dfs(0, 0, 0, 1.0);

		System.out.println(result);
	}
	private static void dfs(int x, int y, int n, double p) {

		
		if(n == N) {
			result += p;
            return;
		}
		else {
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(!isExist(nx,ny)) {//list에 없으면 
					list.add(new Point(nx, ny));
					dfs(nx, ny, n+1, p*prob[i]);
					list.remove(list.size()-1);
				}
			}
			
		}
	}
	private static boolean isExist(int x, int y) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).x == x && list.get(i).y == y) {
				return true;
			}
		}
		return false;
	}
}
class Point {
	int x; int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
