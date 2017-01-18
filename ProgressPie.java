import java.util.*;
/**
 * "Progress Pie"
 * https://www.facebook.com/hackercup/problem/1254819954559001/
 * @author Ariana Herbst
 * January 7th, 2017
 */
public class ProgressPie {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		StringBuilder ans = new StringBuilder();
		for (int n = 0; n < N; n++)
		{
			double angle = (sc.nextDouble() / 100.0) * 2 * Math.PI;
			//System.out.println("angle = " + angle * 180.0 / Math.PI);
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			double dist = Math.sqrt(Math.pow(x - 50, 2) + Math.pow(y - 50, 2));
			boolean black = false;
			if (dist > 50)
			{
				ans.append("Case #" + (n + 1) + ": white\n");
				continue;
			}
			if (x >= 50 && y >= 50)	//upper right
			{
				double pAng = Math.atan((x - 50) / (y - 50));
				if (pAng <= angle)
					black = true;
			}
			else if (x >= 50 && y <= 50)	//lower right
			{
				double pAng = Math.atan((50 - y) /(x - 50) );
				if (pAng <= angle - Math.PI / 2)
					black = true;
			}
			else if (x <= 50 && y <= 50)	//lower left
			{
				double pAng = Math.atan((50 - x) /(50 - y) );
				if (pAng <= angle - Math.PI)
					black = true;
			}
			else 	//upper left
			{
				double pAng = Math.atan((y - 50) /(50 - x) );
				
				
				if (pAng <= angle - 3.0 * Math.PI / 2.0) {
					black = true;
					//System.out.println(pAng * 180.0 / Math.PI + " " + (angle - 3.0 * Math.PI / 2.0) * 180.0 / Math.PI);
					//System.out.println("KEK");
				}
			}
			ans.append("Case #" + (n + 1) + ": " + (black ? "black" : "white") + "\n");
			
		}
		System.out.print(ans);

	}

}
