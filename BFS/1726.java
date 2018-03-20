import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, step;
	static int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
	static int[] map[];
	static boolean[] visit[][];
	static Point S, D;
	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visit = new boolean[N][M][4];

		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		for(int i=0; i<2; i++) {
			String[] s = br.readLine().split(" ");
			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);
			int dir = Integer.parseInt(s[2]);

			if(i==0) {
				S = new Point(x-1, y-1, dir-1);
			}
			else {
				D = new Point(x-1, y-1, dir-1);
			}
		}
		BFS();
	}
	private static void BFS() {
		q.add(S);
		visit[S.x][S.y][S.dir] = true;

		while(!q.isEmpty()) {

			int qsize = q.size();

			for(int a=0; a<qsize; a++) {

				Point p = q.poll();

				if(p.x == D.x && p.y == D.y && p.dir == D.dir) {
					System.out.println(step);
					return;
				}

				Go(p, 1);
				Go(p, 2);
				Go(p, 3);
				Turn(p, 0);
				Turn(p, 1);
			}
			step++;
		}
	}
	private static void Go(Point p, int k) {

		if(Range(p, p.dir, k)) {
			int nx = p.x + k*dx[p.dir];
			int ny = p.y + k*dy[p.dir];

			if(!visit[nx][ny][p.dir]) {
				visit[nx][ny][p.dir] = true;
				q.add(new Point(nx, ny, p.dir));
			}
		}
	}
	private static void Turn(Point p, int dir) {
		int turn_dir = 0;

		if(dir == 0) {//left
			switch(p.dir) {
			case 0:		turn_dir = 3; break;
			case 1:		turn_dir = 2; break;
			case 2:		turn_dir = 0; break;
			case 3:		turn_dir = 1; break;
			}
		}
		else if(dir == 1) {//right
			switch(p.dir) {
			case 0:		turn_dir = 2; break;
			case 1:		turn_dir = 3; break;
			case 2:		turn_dir = 1; break;
			case 3:		turn_dir = 0; break;
			}
		}
		if(!visit[p.x][p.y][turn_dir]) {
			q.add(new Point(p.x, p.y, turn_dir));
			visit[p.x][p.y][turn_dir] = true;
		}
	}
	private static boolean Range(Point p, int dir, int k) {
		//이동하려는 포인트가 범위 안에 맞고 이동하는 와중에 1도없니?
		int mv_x = p.x+k*dx[dir], mv_y = p.y+k*dy[dir];
		int nx=p.x, ny=p.y;

		if(mv_x<0 || mv_y<0 || mv_x>N-1 || mv_y>M-1) {
			return false;
		}

		for(int i=0; i<k; i++) {
			nx = nx + dx[dir];
			ny = ny + dy[dir];


			if(map[nx][ny] == 1) {
				return false;
			}
		}
		return true;
	}
}
class Point {
	int x; int y; int dir;
	public Point(int x, int y, int dir) {
		this.x = x;
		this.y = y ;
		this.dir = dir;
	}
}
