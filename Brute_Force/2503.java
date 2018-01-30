import java.util.Scanner;

class Baseball
{
	String num;
	int strike;
	int ball;
	
	public Baseball(String num, int strike, int ball)
	{
		this.num = num;
		this.strike = strike;
		this.ball = ball;
	}
}

public class Main {

	static int N, cnt;
	static Baseball list[];
	
	private static boolean game(String num)
	{
		boolean flag = true;
		
		for(int i=0; i<list.length; i++)
		{
			int ball=0, strike=0;
			String standard = list[i].num;
			
			//Check
			for(int j=0; j<3; j++)
			{
				String a = num.substring(j, j+1);
				
				for(int k=0; k<3; k++)
				{
					String b = standard.substring(k, k+1);
					
					if(a.equals(b))
					{
						if(j == k)
						{
							strike++;
							break;
						}
						ball++;
					}
				}
			}
			if(list[i].ball != ball || list[i].strike != strike)
			{
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	private static boolean check(String num)
	{//To check there are same numbers
		String num1 = num.substring(0,1);
		String num2 = num.substring(1,2);
		String num3 = num.substring(2,3);
		
		if(num1.equals(num2) || num1.equals(num3) || num2.equals(num3) 
				|| num1.equals("0") || num2.equals("0") || num3.equals("0"))
			return false;
		
		
		return true;
	}
	public static void main(String[] args)
	{	
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		
		list = new Baseball[N];
		
		for(int i=0; i<N; i++)
		{
			String num = sc.next();
			int strike = sc.nextInt();
			int ball = sc.nextInt();
			list[i] = new Baseball(num, strike, ball);
		}
			
		for(int i=123; i<=987; i++)
		{
			String a = String.valueOf(i);
			
			if(check(a))
				if(game(a))
					cnt++;
		}
		
		System.out.println(cnt);

		sc.close();
	}
}
