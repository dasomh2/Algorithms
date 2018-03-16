import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, start_x, start_y, direct, result, nx, ny, cnt_wall;
	static int[] map[], dx = {-1,0,1,0}, dy = {0,1,0,-1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		st = new StringTokenizer(br.readLine());

		start_x = Integer.parseInt(st.nextToken());
		start_y = Integer.parseInt(st.nextToken());
		direct = Integer.parseInt(st.nextToken());

		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		clean(start_x, start_y, direct);
	}
	static void clean(int x, int y, int d) {

		if(map[x][y] == 0) {
			map[x][y] = 2;
			result++;
		}
		for(int i=0; i<4; i++) {

			d = d-1;
			if(d < 0) d = 3;

			nx = x + dx[d];
			ny = y + dy[d];

			if(nx<0 || ny<0 || nx>N-1 || ny>M-1 || map[nx][ny] == 1 || map[nx][ny] == 2) {
				cnt_wall++;
				continue;
			}
			//청소하지 않은 공간 있으면 그 d로 전진한다 
			if(map[nx][ny] == 0) {
				cnt_wall=0;
				clean(nx, ny, d);
			}
			//왼쪽에 없으면 왼쪽으로 또회전하고 넘어감 - 그대로 유지하면 되는거
		}
		if(cnt_wall == 4) {
			//네 방향이 모두 청소되어 있거나 벽이면 
			int tmp_d = 0;
			//후진하는 방향으로 만들어놓고 
			if(d == 0)	tmp_d = 2;
			else if(d == 1) tmp_d = 3;
			else if(d == 2) tmp_d = 0;
			else	 if(d == 3) tmp_d = 1;

			int next_x = x+dx[tmp_d];
			int next_y = y+dy[tmp_d];

			//후진하려는 곳이 벽이면 작동멈춤 
			if(next_y<0 || next_x<0 || next_x>N-1 || next_y>M-1 || map[next_x][next_y] == 1) {
				System.out.println(result);
				System.exit(0);
			}
			//후진하려는 곳이 청소 되어있는 곳이면
			cnt_wall=0;
			clean(next_x, next_y, d);
		}

	}
}
