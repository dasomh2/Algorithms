import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {

	static int R,C,numOfpipe;
	static int[] M = new int[2], dx = {-1,0,1,0}, dy = {0,1,0,-1};
	static char[] map[], block = {'|','-','+','1','2','3','4'};
	static Point[] result = new Point[1];
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] != '.') {
					numOfpipe++;
				}
				if(map[i][j] == 'M') {
					M[0] = i;
					M[1] = j;
				}
			}
		}
		for(int i=0; i<4; i++)
			DFS(M[0], M[1], i, 1);
		br.close();

	}
	private static void DFS(int x, int y, int dir, int depth) {
		
		x = x+dx[dir];
		y = y+dy[dir];
		
		if(Range(x,y)) {
			
			if(map[x][y] == 'Z' && depth >= numOfpipe) {
				System.out.println(result[0].x+" "+result[0].y+" "+result[0].c);
				System.exit(0);
			}
			if(map[x][y] == '.' && !flag) {
				for(int i=0; i<7; i++) {
					flag = true;
					map[x][y] = block[i];
					result[0] = new Point(x+1,y+1, map[x][y]);
					pipe(x,y,dir, depth+1);
					map[x][y] = '.';
					flag = false;
				}
			}
			else {
				pipe(x, y, dir, depth+1);
			}
		}
	}
	private static void pipe(int x, int y, int d, int depth) {
		
		d = (d+2)%4;
		
		switch(map[x][y]) {
		case '|':
			if(d==0)			DFS(x,y,2,depth);
			else if(d==2)	DFS(x,y,0,depth);
			break;
			
		case '-':
			if(d==3)			DFS(x,y,1,depth);
			else if(d==1)	DFS(x,y,3,depth);
			break;
			
		case '+':
			if(d==0)			DFS(x,y,2,depth);
			else if(d==1)	DFS(x,y,3,depth);
			else if(d==2)	DFS(x,y,0,depth);
			else if(d==3)	DFS(x,y,1,depth);
			break;
			
		case '1':
			if(d==1)			DFS(x,y,2,depth);
			else if(d==2)	DFS(x,y,1,depth);
			break;
			
		case '2':
			if(d==0)			DFS(x,y,1,depth);
			else if(d==1)	DFS(x,y,0,depth);
			break;
			
		case '3':
			if(d==0)			DFS(x,y,3,depth);
			else if(d==3)	DFS(x,y,0,depth);
			break;
			
		case '4':
			if(d==3)			DFS(x,y,2,depth);
			else if(d==2)	DFS(x,y,3,depth);
			break;
		}
	}
	private static boolean Range(int x, int y) {
		if(x<0 || y<0 || x>R-1 || y>C-1)
			return false;
		return true;
	}
}
class Point {
	int x; int y; char c;
	public Point(int x, int y, char c) {
		this.x = x;
		this.y = y;
		this.c = c;
	}
}
