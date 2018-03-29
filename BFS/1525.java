import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
public class Main {

	static final int N = 3;
	static Queue<String> q = new LinkedList<>();
	static HashMap<String, Integer> h = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String start = "";

		for(int i=0; i<N; i++) {
			String s[] = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				String tmp = s[j];
				start += tmp;
			}
		}
		BFS(start);
		
		if(h.containsKey("123456780")) {
			System.out.println(h.get("123456780"));
		}
		else
			System.out.println("-1");
	}
	private static void BFS(String start) {
		int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
		q.add(start);
		h.put(start, 0);

		while(!q.isEmpty()) {

			String s = q.poll();

			int w = s.indexOf("0");
			int x = w/3;
			int y = w%3;

			for(int i=0; i<4; i++) {

				int nx = x + dx[i];
				int ny = y + dy[i];

				if(nx<0 || ny<0 || nx>=N || ny>=N)		continue;

				String str = s;
				char a = str.charAt(x*3+y);
				char b = str.charAt(nx*3+ny);
				str = str.replace(a, 'a');
				str = str.replace(b, 'b');
				str = str.replace('a', b);
				str = str.replace('b', a);

				if(!h.containsKey(str)) {
					q.offer(str);
					h.put(str, h.get(s)+1);
					
					if(str.equals("123456780")) {return;}
				}
			}
		}
	}
}
