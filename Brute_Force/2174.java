import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int A,B, robot, command;
	static Robot meet;
	static String[] four_d = {"", "N", "E", "S", "W"};
	static int[] land[];
	static ArrayList<Robot> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		land = new int[A][B];

		st = new StringTokenizer(br.readLine());

		robot = Integer.parseInt(st.nextToken());
		command = Integer.parseInt(st.nextToken());

		for(int i=1; i<=robot; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Robot(i, Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					st.nextToken()));
		}
		for(int i=0; i<command; i++) {
			st = new StringTokenizer(br.readLine());
			operation(Integer.parseInt(st.nextToken()), st.nextToken(), Integer.parseInt(st.nextToken()));
		}
		System.out.println("OK");
	}
	private static void operation(int type, String command, int count) {
		Robot select = list.get(type-1);
		int tmp=0;
		
		if(select.direct.equals("N"))		tmp = 1;
		if(select.direct.equals("E"))		tmp = 2;
		if(select.direct.equals("S"))		tmp = 3;
		if(select.direct.equals("W"))		tmp = 4;

		if(command.equals("L")) {//왼쪽으로 count번 회전 
			switch(count%4) {
			
			case 1:
				tmp = tmp-1;
				if(tmp<1)	tmp = tmp+4;
				select.direct = four_d[tmp];
				break;
			case 2:
				tmp = tmp-2;
				if(tmp<1)	tmp = tmp+4;
				select.direct = four_d[tmp];
				break;
			case 3:
				tmp = tmp-3;
				if(tmp<1)	tmp = tmp+4;
				select.direct = four_d[tmp];
				break;
			}
		}
		else if(command.equals("R")) {//오른쪽으로 count번 회전 
			switch(count%4) {
			
			case 1:
				tmp = tmp+1;
				if(tmp>4)	tmp = tmp-4;
				select.direct = four_d[tmp];
				break;
			case 2:
				tmp = tmp+2;
				if(tmp>4)	tmp= tmp-4;
				select.direct = four_d[tmp];
				break;
			case 3:
				tmp = tmp+3;
				if(tmp>4)	tmp= tmp-4;
				select.direct = four_d[tmp];
				break;
			}
		}
		else {
			while(count > 0) {
				if(select.direct.equals("N")) {
					select.y++;
				}
				else if(select.direct.equals("S")) {
					select.y--;
				}
				else if(select.direct.equals("E")) {
					select.x++;
				}
				else {
					select.x--;
				}
				count--;
				
				if(meet(select)) {//Crash into another robot(meet)
					System.out.println("Robot "+select.type+" crashes into robot "+meet.type);
					System.exit(0);
				}
			}

			if(select.x<1 || select.y<1 || select.x>A || select.y>B) {//out of the wall~
				System.out.println("Robot "+select.type+" crashes into the wall");
				System.exit(0);
			}

		}
	}
	private static boolean meet(Robot robot) {
		for(int i=0; i<list.size(); i++) {
			if(robot != list.get(i)) {
				if(robot.x == list.get(i).x && robot.y == list.get(i).y) {
					meet = list.get(i);
					return true;
				}
			}
		}
		return false;
	}
}
class Robot {
	int type;
	int x;
	int y;
	String direct;

	public Robot(int type, int x, int y, String direct) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
}
