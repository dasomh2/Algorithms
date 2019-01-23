import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	static int R,C;
	static int[] map[], dist[];
	static boolean[] visit[][];
	static Queue<Point> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		dist = new int[R][C];
		q = new LinkedList<>();
		
		//0:nuck, 1:theQ, 2:chang
		visit = new boolean[3][R][C];
		
		String s = "";

		for(int i=0; i<R; i++) {
			s = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = s.charAt(j) - 48;
			}
		}
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			q.add(new Point(i, x-1, y-1));
			dist[x-1][y-1] = 1;
			visit[i][x-1][y-1] = true;
		}
		BFS();
	}
	private static void BFS() {
		int step = 0, cnt=0;
		int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
		boolean mamison = false;
		
		while(!q.isEmpty()) {
			
			int qsize = q.size();
			step++;
			
			for(int a=0; a<qsize; a++) {
				
				Point p = q.poll();
				
				for(int i=0; i<4; i++) {
					int name = p.name;
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					
					if(isRange(nx, ny) && !visit[name][nx][ny] && map[nx][ny] == 0) {
						visit[name][nx][ny] = true;
						dist[nx][ny]++;
						q.add(new Point(name, nx, ny));
						
						if(dist[nx][ny] == 3) {
							mamison = true;
						}
					}
				}
			}
			if(mamison) {
				for(int i=0; i<R; i++) {
					for(int j=0; j<C; j++) {
						if(dist[i][j] == 3) {
							cnt++;
						}
					}
				}
				System.out.println(step+"\n"+cnt);
				System.exit(0);
			}
		}
		System.out.println("-1");
	}

	private static boolean isRange(int x, int y) {
		if(x<0 || y<0 || x>R-1 || y>C-1)
			return false;
		return true;

	}
}
class Point {
	int x, y, name;
	public Point(int name, int x, int y) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
}
