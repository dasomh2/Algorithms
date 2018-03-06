import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] quadTree[];
	static int N;
	static StringBuffer sb;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		quadTree = new int[N][N];
		sb = new StringBuffer();
		
		for(int i=0 ; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				quadTree[i][j] = s.charAt(j)-48;
			}
		}
		compress(0,0,N);
		System.out.println(sb.toString());
	}
	
	private static void compress(int x, int y, int n) {

		boolean chk = true;
		
		for(int i=x; i<x+n; i++) {
			for(int j=y; j<y+n; j++) {
				if(quadTree[x][y] != quadTree[i][j]) {
					chk = false;
					break;
				}
			}
			if(!chk) break;
		}
		if(chk) {
			sb.append((quadTree[x][y]));
		}
		else {
			sb.append("(");
			int small = n/2;
			for(int i=0; i<2; i++) {
				for(int j=0; j<2; j++) {
					compress(x+small*i, y+small*j, small);
				}
			}
			sb.append(")");
		}
	}
}
