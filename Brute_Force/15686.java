import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static int[] map[];
	static int N, M, min=Integer.MAX_VALUE;
	static ArrayList<Point> chicken, home, candidate;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		map = new int[N][N];
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		candidate = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if(map[i][j] == 1) {
					home.add(new Point(i,j));
				}
				else if(map[i][j] == 2) {
					chicken.add(new Point(i,j));
				}
			}
		}
		for(int i=0; i<=chicken.size()-M; i++) {
			Point p = chicken.get(i);
			candidate.add(p);
			combination(1, i);
			candidate.remove(candidate.size()-1);
		}
		System.out.println(min);
	}
	private static void combination(int depth, int v) {
		if(depth == M) {
			min = Math.min(min, calMin());
			return;
		}
		
		for(int i=v+1; i<chicken.size(); i++) {
			Point p = chicken.get(i);
			
			candidate.add(p);
			combination(depth+1, i);
			candidate.remove(candidate.size()-1);
		}
	}
	private static int calMin() {
		int mini, dist=0, sum=0;
		
		for(int i=0; i<home.size(); i++) {
			Point p = home.get(i);//집 좌표 하나 
			mini=Integer.MAX_VALUE;
			
			for(int j=0; j<candidate.size(); j++) {
				Point c = candidate.get(j);//치킨집 좌표들 
				
				dist = Math.abs(p.x-c.x) + Math.abs(p.y-c.y);
				mini = Math.min(mini, dist);
			}
			sum += mini;
		}
		return sum;
	}
}
class Point {
	int x; int y;
	public Point(int x, int y) {
		this.x =x ;
		this.y = y;
	}
}
