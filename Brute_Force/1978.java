import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1978
public class Main {

	//소수 카운트 변수 
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력 첫 줄 N
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			//주어진 수 
			int num = Integer.parseInt(st.nextToken());
			
			//숫자가 1이면 소수인지 판별하지 않고 PASS
			if(num == 1) continue;
			//그 외 경우는 소수인지 판별해봄 
			prime(num);
		}
		
		br.close();
	
		//소수 개수 출력 
		System.out.println(cnt);
	}
	
	private static void prime(int N) {
		//소수인지 아닌지 판별하는 flag 변수 
		boolean isPrime = true;
		
		//2부터 N-1까지 반복하면서 나눴을 때 0이 되면 소수가 아님
		//소수가 자기 자신(N)과 1을 제외하면 약수가 없기 때문
		for(int i=2; i<N; i++) {
			
			if(N%i == 0) {
				//나눠 떨어지는 수가 있으면 소수가 아님 - break
				isPrime = false;
				break;
			}
		}
		//소수면 카운트 
		if(isPrime)
			cnt++;
	}
}
