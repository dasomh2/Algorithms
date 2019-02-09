import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int N, M;
	static LinkedList<Integer> queue = new LinkedList<>();
	static ArrayList<Integer> extract = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			queue.add(i);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			extract.add(Integer.parseInt(st.nextToken())-1);
		}
		
		System.out.println(rotate_q());
	}
	private static int rotate_q() {
		int cnt = 0;
		int find = 0;
		
		while(find < M) {
			//1번 연산 수행
			int pop = queue.get(0);
			int curExtract = extract.get(find);
			if(curExtract == pop) {
				queue.remove(0);
				find++;
				continue;
			}
			
			//뽑아내려고 하는 원소가 어디에 있는지 확인
			int idx = queue.indexOf(curExtract);

			int size = queue.size();
			int left = idx;//왼쪽에서 몇번째 
			int right = size-idx;//오른쪽에서 몇번째 
			
			int iter = 0;
			
			if(left > right) {//rotate right
				iter = right;
				for(int i=0; i<iter; i++) {
					queue.addFirst(queue.removeLast());
				}
			}
			else {//rotate left
				iter = left;
				for(int i=0; i<iter; i++) {
					queue.addLast(queue.removeFirst());
				}
			}
			cnt += iter;
		}
		return cnt;
	}
}
