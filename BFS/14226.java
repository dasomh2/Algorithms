import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
        
		System.out.println(bfs_emoji());
	}
	private static int bfs_emoji() {
		int time = 0;
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[2001][2001];
		visit[1][0] = true;//visit[screen][clip]
		q.add(new Point(1,0));

		while(!q.isEmpty()) {

			int qsize = q.size();
			for(int a=0 ;a<qsize; a++) {
				
				Point p = q.poll();
				int screen = p.s;
				int clip = p.c;

				if(screen == S) {
					return time;
				}

				//copy from screen, paste to clip
				if(!visit[screen][screen]) {
					visit[screen][screen] = true;
					q.add(new Point(screen, screen));
				}

				//paste from clip to screen
				int total = clip+screen;
				if(clip != 0 && total <= 1000 && !visit[total][clip]) {
					visit[total][clip] = true;
					q.add(new Point(total, clip));
				}

				//remove 1 emoji from screen
				if(screen-1 >= 0 && !visit[screen-1][clip]) {
					visit[screen-1][clip] = true;
					q.add(new Point(screen-1, clip));
				}
			}
			time++;
		}
		return time;
	}
}
class Point {
	int s,c;
	public Point(int s, int c) {
		this.s = s;
		this.c = c;
	}
}
