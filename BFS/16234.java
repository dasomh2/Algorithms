
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int N, L, R, cnt;
	static boolean[] moved[], visit[];
	static boolean flag = false;
	static int[] map[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while(true) {
			//인구이동 할 수 있는만큼 이동 (현재 판에서)
			//중복방지를 visit배열 하나로 함 -> 현재 점에서 방문하지 않은 점들은 어차피 재방문X 
			visit = new boolean[N][N];

			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visit[i][j]) {
						visit[i][j] = true;
						if(BFS(i, j))
							flag = true;
					}
				}
			}

			//인구이동이 전혀 없으면 바로 종료
			if(!flag) {
				System.out.println(cnt);
				return;
			}
			
			cnt++;
			flag = false;
		}
	}
	private static boolean BFS(int x, int y) {
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};
		int res = 0;

		//BFS하려는 큐 
		Queue<Point> q = new LinkedList<>();

		//인구이동할 나라들 전체 모아둔 큐
		Queue<Point> united = new LinkedList<>();

		q.offer(new Point(x,y));
		united.offer(new Point(x, y));

		visit[x][y] = true;
		
		//총 인구수 누적
		res += map[x][y];

		while(!q.isEmpty()) {

			Point p = q.poll();

			for(int i=0; i<4; i++) {

				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if(isRange(nx, ny) && !visit[nx][ny]) {
					int dist = Math.abs(map[p.x][p.y]-map[nx][ny]);
					
					//범위 안에 들면 방문체크 & 누적 & 큐에 추가 
					if(dist >= L && dist <= R) {
						visit[nx][ny] = true;
						res += map[nx][ny];
						q.add(new Point(nx, ny));
						united.add(new Point(nx, ny));
					}
				}
			}
		}

		int size = united.size();

		//united 사이즈가 1이면 국경선 열 나라가 없었다는 뜻 
		if(size > 1) {
			int value = res/size;

			//값 갱신 
			while(!united.isEmpty()) {
				Point p = united.poll();
				map[p.x][p.y] = value;
			}
			return true;
		}
		else
			return false;
	}
	private static boolean isRange(int x, int y) {
		if(x<0 || y<0 || x>N-1 || y>N-1) {
			return false;
		}
		return true;
	}
}
class Point {
	int x; int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
