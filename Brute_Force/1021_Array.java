import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int N, M;
	static ArrayList<Integer> queue = new ArrayList<>();
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
			
			int idx = 0;
			//뽑아내려고 하는 원소가 어디에 있는지 확인
			for(int i=0; i<queue.size(); i++) {
				if(queue.get(i) == curExtract) {
					idx = i;
					break;
				}
			}
			
			int size = queue.size();
			int standard = size/2;
			int iter = 0;
			int[] tmp = new int[size];
			//가운데를 기준으로 값이 크면 오른쪽으로 회전, 아니면 왼쪽 회전
			if(standard < idx) {//rotate right
				iter = size-idx;
				for(int i=0; i<size; i++) {
					int cur = queue.get(i);
					int nxt = i+iter;
					if(nxt >= size) {
						nxt -= size;
					}
					tmp[nxt] = cur;
				}
			}
			else {//rotate left
				iter = idx;
				for(int i=0; i<size; i++) {
					int cur = queue.get(i);
					int nxt = i-iter;
					
					if(nxt < 0) {
						nxt = size+nxt;
					}
					tmp[nxt] = cur;
				}
			}
			//큐에 정리
			queue.clear();
			for(int i=0; i<size; i++) {
				queue.add(tmp[i]);
			}
			cnt += iter;
		}
		return cnt;
	}
}
