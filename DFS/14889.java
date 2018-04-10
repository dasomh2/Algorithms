import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, min = Integer.MAX_VALUE;
	static int[] map[];
	static ArrayList<Integer> team1=new ArrayList<>(), team2=new ArrayList<>();
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		visit = new boolean[N+1];

		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}


		makeTeam(1,0);

		System.out.println(min);
	}
	private static void makeTeam(int v, int cnt) {

		if(cnt == N/2) {
			for(int i=1; i<visit.length; i++) {
				if(!visit[i])
					team1.add(i);
				else
					team2.add(i);
			}
			calculation(team1, team2);
			team1=new ArrayList<>(); team2=new ArrayList<>();
		}
		else {		
			for(int i=v; i<=N; i++) {
				if(!visit[i]) {
					visit[i] = true;
					makeTeam(i, cnt+1);
				}
			}
		}
		visit[v] = false;
	}
	private static void calculation(ArrayList<Integer> list, ArrayList<Integer> list2) {

		int sum1 = 0, sum2=0;

		for(int i=0; i<list.size()-1; i++) {
			for(int j=i+1; j<list.size(); j++) {
				sum1 += (map[list.get(i)][list.get(j)]+map[list.get(j)][list.get(i)]);
				sum2 += (map[list2.get(i)][list2.get(j)]+map[list2.get(j)][list2.get(i)]);
			}
		}
		
		min = Math.min(Math.abs(sum1-sum2), min);
	}
}
