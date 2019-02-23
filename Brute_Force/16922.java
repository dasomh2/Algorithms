import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	static int N, cnt;
	static int[] value = {1, 5, 10, 50};
	static boolean[] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int size = 50*N;
		num = new boolean[size+1];

		rome_num(0, 0, 0);
		System.out.println(cnt);
	}
	private static void rome_num(int v, int depth, int sum) {
		if(depth == N) {
			if(!num[sum]) {
				num[sum] = true;
				cnt++;
			}
            return;
		}
		for(int i=v; i<4; i++) {
			rome_num(i, depth+1, sum+value[i]);
		}
	}
}
