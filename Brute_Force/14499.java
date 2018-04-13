import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,M,s_x,s_y,K;
	static Queue<Integer> list = new LinkedList<>();
	static int[] map[], dice = {0,0,0,0,0,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		s_x = Integer.parseInt(st.nextToken());
		s_y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());


		map = new int[N][M];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			int d = Integer.parseInt(st.nextToken());
			if(move(s_x, s_y, d)) //이동
				System.out.println(dice[0]);
		}

	}
	private static boolean move(int x, int y, int d) {
		int[] dx = {0,0,0,-1,1}, dy = {0,1,-1,0,0};

		x = x + dx[d];
		y = y + dy[d];

		if(x<0 || y<0 || x>N-1 || y>M-1) {//주사위 모양 원상복귀 
			return false;
		}
		else {
			shape(d);

			if(map[x][y] == 0) {
				map[x][y] = dice[5];
			}
			else {
				dice[5] = map[x][y];
				map[x][y] = 0;
			}

			s_x = x;
			s_y = y;
			return true;
		}
	}
	private static void shape(int d) {
		int[] copy = new int[6];
		for(int i=0; i<6; i++)
			copy[i] = dice[i];

		switch(d) {
		case 1://동 
			dice[0] = copy[3];
			dice[2] = copy[0];
			dice[3] = copy[5];
			dice[5] = copy[2];
			break;
		case 2://서 
			dice[0] = copy[2];
			dice[2] = copy[5];
			dice[3] = copy[0];
			dice[5] = copy[3];
			break;
		case 3://북 
			dice[0] = copy[4];
			dice[1] = copy[0];
			dice[4] = copy[5];
			dice[5] = copy[1];
			break;
		case 4://남 
			dice[0] = copy[1];
			dice[1] = copy[5];
			dice[4] = copy[0];
			dice[5] = copy[4];
			break;
		}

	}

}
