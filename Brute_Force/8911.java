
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
	static int cur_x, cur_y, cur_d;
	static int max_x, max_y, min_x, min_y;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			String command = br.readLine();
			cur_x = cur_y = max_x = max_y = min_x = min_y = 250;
			cur_d = 0;
			
			for(int i=0; i<command.length(); i++) {
				char tmp = command.charAt(i);
				Race(tmp);
			}
			
			System.out.println((max_x-min_x) * (max_y-min_y));
		}
	}
	private static void Race(char comm) {
		switch(comm) {
		case 'F':
			cur_x = cur_x + dx[cur_d];
			cur_y = cur_y + dy[cur_d];
			
			min_x = Math.min(cur_x, min_x);
			min_y = Math.min(cur_y, min_y);
			max_x = Math.max(cur_x, max_x);
			max_y = Math.max(cur_y, max_y);
			break;
			
		case 'B':
			cur_x = cur_x - dx[cur_d];
			cur_y = cur_y - dy[cur_d];
			
			min_x = Math.min(cur_x, min_x);
			min_y = Math.min(cur_y, min_y);
			max_x = Math.max(cur_x, max_x);
			max_y = Math.max(cur_y, max_y);
			break;
			
		case 'L':
			cur_d = (cur_d+1)%4;
			break;
			
		case 'R':
			cur_d = (cur_d+3)%4;
			break;
		}
	}

}
