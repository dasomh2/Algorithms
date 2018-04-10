import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] q1 = new int[8], q2 = new int[8], q3 = new int[8], q4 = new int[8];
	static int[] arr = new int[6];
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		String s3 = br.readLine();
		String s4 = br.readLine();
		for(int i=0; i<8; i++) {
			q1[i] = s1.charAt(i)-48;
			q2[i] = (s2.charAt(i)-48);
			q3[i] = (s3.charAt(i)-48);
			q4[i] = (s4.charAt(i)-48);
		}

		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int numOfwheel=Integer.parseInt(st.nextToken());
			int direction=Integer.parseInt(st.nextToken());

			arr[0] = q1[2];
			arr[1] = q2[6];
			arr[2] = q2[2];
			arr[3] = q3[6];
			arr[4] = q3[2];
			arr[5] = q4[6];

			move(numOfwheel, direction);
		}
		int score=0;
		
		if(q1[0] == 1) {
			score+=1;
		}
		if(q2[0] == 1) {
			score+=2;
		}
		if(q3[0] == 1) {
			score+=4;
		}
		if(q4[0] == 1) {
			score+=8;
		}
		
		System.out.println(score);
		br.close();
	}
	private static void spin(int dir, int[] tmp) {
		if(dir == 1) {
			int tail = tmp[tmp.length-1];
			System.arraycopy(tmp, 0, tmp, 1, tmp.length-1);
			tmp[0] = tail;
		}
		else {
			int head = tmp[0];
			System.arraycopy(tmp, 1, tmp, 0, tmp.length-1);
			tmp[tmp.length-1] = head;
		}
	}
	private static void move(int wheelNum , int dir) {

		switch(wheelNum) {
		
		case 1:
			if(dir == 1) {
				if(arr[0] != arr[1]) {
					if(arr[2] != arr[3]) {
						if(arr[4] != arr[5]) {
							spin(-1, q4);
						}
						spin(1, q3);
					}
					spin(-1, q2);
				}
				spin(1, q1);
			}
			else if(dir == -1) {
				if(arr[0] != arr[1]) {
					if(arr[2] != arr[3]) {
						if(arr[4] != arr[5]) {
							spin(1, q4);
						}
						spin(-1, q3);
					}
					spin(1, q2);
				}
				spin(-1, q1);
			}
			break;
		case 2://1번하고 3번봐야함 
			if(dir == 1) {
				if(arr[0] != arr[1]) {
					spin(-1, q1);
				}
				if(arr[2] != arr[3]) {
					if(arr[4] != arr[5]) {
						spin(1, q4);
					}
					spin(-1, q3);
				}
				spin(1, q2);
			}
			else if(dir == -1) {
				if(arr[0] != arr[1]) {
					spin(1, q1);
				}
				if(arr[2] != arr[3]) {
					if(arr[4] != arr[5]) {
						spin(-1, q4);
					}
					spin(1, q3);
				}
				spin(-1, q2);
			}
			break;
		case 3://2번하고 4번 봐야함 
			if(dir == 1) {
				if(arr[4] != arr[5]) {
					spin(-1, q4);
				}
				if(arr[2] != arr[3]) {
					if(arr[0] != arr[1]) {
						spin(1, q1);
					}
					spin(-1, q2);
				}
				spin(1, q3);
			}
			else if(dir == -1) {
				if(arr[4] != arr[5]) {
					spin(1, q4);
				}
				if(arr[2] != arr[3]) {
					if(arr[0] != arr[1]) {
						spin(-1, q1);
					}
					spin(1, q2);
				}
				spin(-1, q3);
			}
			break;
		case 4:
			if(dir == 1) {
				if(arr[4] != arr[5]) {
					if(arr[2] != arr[3]) {
						if(arr[0] != arr[1]) {
							spin(-1, q1);
						}
						spin(1, q2);
					}
					spin(-1, q3);
				}
				spin(1, q4);
			}
			else if(dir == -1) {
				if(arr[4] != arr[5]) {
					if(arr[2] != arr[3]) {
						if(arr[0] != arr[1]) {
							spin(1, q1);
						}
						spin(-1, q2);
					}
					spin(1, q3);
				}
				spin(-1, q4);
			}
			break;
		}
	}
}
