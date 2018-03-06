import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//3:20~
	static int R,C;
	static char[] road[];
	static int[] dx = {-1,0,1}, dy = {1,1,1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		road = new char[R][C];

		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				road[i][j] = s.charAt(j);
			}
		}

		int numOfpipe = 0;
		
		for(int i=0; i<R; i++) {
			numOfpipe += DFS(i, 0);
		}
		System.out.println(numOfpipe);
	}
	private static int DFS(int x, int y) {

		road[x][y] = '/';
		
		if(y == C-1) {
			return 1;
		}
		else {
			for(int i=0; i<3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(nx<0 || ny<0 || nx>R-1 || ny>C-1 || road[nx][ny] == 'x' || road[nx][ny]== '/') 
					continue;
				
				if(DFS(nx, ny) == 1) {
					return 1;
				}
			}
		}
		return 0;
	}
}
