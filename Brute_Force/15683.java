import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M, min=Integer.MAX_VALUE;
	static int[] map[], dx = {-1,0,1,0}, dy = {0,1,0,-1};
	static int[] newArr[];
	static ArrayList<Point> cctv = new ArrayList<>();//1,2,3,4번 카메라 
	static ArrayList<Point> cctv5 = new ArrayList<>();//5번 카메라 
	static ArrayList<String> dir = new ArrayList<>();//방향 조합 리스트 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		newArr = new int[N][M];//카피본 

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >= 1 && map[i][j] <= 4) {//1,2,3,4
					cctv.add(new Point(map[i][j], i, j));
				}
				else if(map[i][j] == 5) {//5
					cctv5.add(new Point(5, i, j));
				}
			}
		}
		for(int i=0; i<cctv5.size(); i++) {//카메라5만 미리 체크
			
			for(int j=0; j<4; j++) {
				int nx=cctv5.get(i).x, ny=cctv5.get(i).y;
				while(true) {
					nx += dx[j];
					ny += dy[j];

					if(nx <0 || ny<0 || nx>N-1 || ny>M-1 || map[nx][ny] == 6) break;
					if(map[nx][ny] >= 1 && map[nx][ny] <= 5) continue;

					map[nx][ny] = -1;
				}
			}
		}
		copy(map, newArr);//5번카메라 방향까지 설정한 배열
		direction(0,"");//카메라가 볼 수 있는 방향 조합하기(5번 카메라 제외)
		for(int i=0; i<dir.size(); i++) {
			String s = dir.get(i);//조합 꺼냄
			for(int j=0; j<s.length(); j++) {
				int d = s.charAt(j) - 48;//방향
				checkRange(cctv.get(j), d);
			}
			min = Math.min(min, counting(map));//최소값 갱신
			copy(newArr, map);//카메라5까지 세팅되어 있는 상태로 초기화
		}
		System.out.println(min);
	}

	private static void direction(int depth, String com) {
		if(depth == cctv.size()) {//cctv 방향 조합 
			dir.add(com);//ex, 카메라4대면 0123
			return;
		}
		for(int i=0; i<4; i++) {
			direction(depth+1, com+i);
		}
	}
	private static void checkRange(Point p, int dir) {
		int nx=0, ny=0, cam = p.type;

		if(cam >= 1 && cam <= 4) {//한 방향만
			nx=p.x; ny=p.y;
			while(true) {//한방향 
				nx += dx[dir];
				ny += dy[dir];

				if(nx <0 || ny<0 || nx>N-1 || ny>M-1 || map[nx][ny] == 6) break;
				if(map[nx][ny] >= 1 && map[nx][ny] <= 5) continue;
				map[nx][ny] = -1;
			}
		}
		if(cam == 2 || cam == 4) {//지금 방향에 대해 반대 방향
			nx=p.x; ny=p.y;
			while(true) {//2방향 
				int tmp=0;

				switch(dir) {
				case 0:	tmp=2; break;
				case 1:	tmp=3; break;
				case 2:	tmp=0; break;
				case 3:	tmp=1; break;
				}

				nx +=  dx[tmp];
				ny +=  dy[tmp];

				if(nx <0 || ny<0 || nx>N-1 || ny>M-1 || map[nx][ny] == 6) break;
				if(map[nx][ny] >= 1 && map[nx][ny] <= 5) continue;
				map[nx][ny] = -1;
			}
		}
		if(cam == 3 || cam == 4) {//지금 방향에 대해 직각 방향
			nx=p.x; ny=p.y;
			while(true) {//직각방향 
				int tmp = dir+1;
				if(dir==3)
					tmp = 0;

				nx +=  dx[tmp];
				ny +=  dy[tmp];

				if(nx <0 || ny<0 || nx>N-1 || ny>M-1 || map[nx][ny] == 6) break;
				if(map[nx][ny] >= 1 && map[nx][ny] <= 5) continue;
				map[nx][ny] = -1;
			}
		}
	}

	private static void copy(int[][] origin, int[][] copy) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy[i][j] = origin[i][j];
			}
		}
	}
	private static int counting(int[][] arr) {
		int cnt =0 ;

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
class Point {
	int type; int x; int y;
	public Point(int type, int x, int y) {
		this.x = x;
		this.y = y;
		this.type= type;
	}
}
