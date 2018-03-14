import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N,M,min=2000001;
	static char[] chess[], chess_8[];
	static char[] origin_1[] = {{'B','W','B','W','B','W','B','W'}, {'W','B','W','B','W','B','W','B'}, {'B','W','B','W','B','W','B','W'}, {'W','B','W','B','W','B','W','B'}, 
			{'B','W','B','W','B','W','B','W'}, {'W','B','W','B','W','B','W','B'}, {'B','W','B','W','B','W','B','W'}, 
			{'W','B','W','B','W','B','W','B'}};
	static char[] origin_2[] = {{'W','B','W','B','W','B','W','B'}, {'B','W','B','W','B','W','B','W'}, {'W','B','W','B','W','B','W','B'}, {'B','W','B','W','B','W','B','W'}, 
			{'W','B','W','B','W','B','W','B'}, {'B','W','B','W','B','W','B','W'}, 
			{'W','B','W','B','W','B','W','B'}, {'B','W','B','W','B','W','B','W'}};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		chess = new char[N][M];
		
		
		for(int i=0; i<N; i++ ) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				chess[i][j] = s.charAt(j);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(i+8<=N && j+8<=M) {
					chess_8 = new char[8][8];
					create(i,j);
					int result = compare();
					if(result == 0)
					{
						System.out.println("0");
						return;
					}
					
					min = Math.min(result, min);
				}
			}
		}
		System.out.println(min);
		br.close();
	}
	private static int compare() {
		int cnt_1 = 0, cnt_2 = 0;
		
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(chess_8[i][j] != origin_1[i][j]) {
					cnt_1++;
				}
			}
		}
		
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(chess_8[i][j] != origin_2[i][j]) {
					cnt_2++;
				}
			}
		}
		return Math.min(cnt_1, cnt_2);
	}
	private static void create(int start_x, int start_y) {
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				chess_8[i][j] = chess[i+start_x][j+start_y];
			}
		}
	}

}
