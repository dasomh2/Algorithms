import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	//3:07~
	static int N;
	static int[] map[];
	static int[] res = new int[3];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			String[] a = s.split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(a[j]);
			}
		}
		
		paper(0,0,N);
		System.out.println(res[0]+"\n"+res[1]+"\n"+res[2]);
	}
	
	private static void paper(int x, int y, int n) {
		
		boolean chk = true;
		
		for(int i=x; i<x+n; i++) {
			for(int j=y; j<y+n; j++) {
				if(map[x][y] != map[i][j]) {
					chk = false;
					break;
				}
			}
			if(!chk)
				break;
		}
		
		if(chk) {
			res[map[x][y]+1]++;
		}
		else {
			int small = n/3;
			for(int i=0; i<3; i++) {
				for(int j=0 ; j<3; j++) {
					paper(x+small*i, y+small*j, small);
				}
			}
		}
	}
}
