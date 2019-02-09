import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] schedule = new int[K];
		int[] use;
		boolean[] visit = new boolean[K+1];
		ArrayList<Integer> multitab = new ArrayList<>();

		int cnt = 0;

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			schedule[i] = Integer.parseInt(st.nextToken());
		}

		int pluggedTool=0, pluggedIdx=0;
		
		for(int i=0; i<K; i++) {
			int curTool = schedule[i];

			if(visit[curTool]) //이미 꽂혀있으면 PASS
				continue;
			
			if(multitab.size() < N) {//빈 곳이 있으면 꽂고 visit처리 
				visit[curTool] = true;
				multitab.add(curTool);
				continue;
			}

			//꽂혀 있지 않고, 빈 곳도 없는 경우 
			use = new int[K+1];
			boolean isZero = false;
			pluggedTool = multitab.get(0); pluggedIdx = 0;

			//멀티탭에 있는 기구들의 다음 순서가 언제인지 찾음 
			for(int j=0; j<multitab.size(); j++) {

				int tool = multitab.get(j);//멀티탭에 있는 기구 

				for(int k=i; k<K; k++) {
					//멀티탭에 꽂혀 있는 기구가 다시 쓰이게 될 순서 저장 
					if(tool == schedule[k]) {
						use[tool] = k;
						break;
					}
				}
				if(use[tool] == 0) {//앞으로 안 쓰일 기구 
					pluggedTool = tool;
					pluggedIdx = j;
					isZero = true;
					break;
				}
			}

			if(!isZero) {//멀티탭에 있는 기구가 모두 언젠가는 쓰일 경우 
				for(int j=1; j<multitab.size(); j++) {
					int tmp = multitab.get(j);
					if(use[pluggedTool] < use[tmp]) {
						pluggedTool = tmp;
						pluggedIdx = j;
					}
				}
			}
			visit[pluggedTool] = false;
			visit[curTool] = true;
			multitab.set(pluggedIdx, curTool);

			cnt++;
		}
		System.out.println(cnt);
	}
}
