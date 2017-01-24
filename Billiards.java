import java.util.*;
/**
 * https://pcs.cs.cloud.vt.edu/problems/150
 * @author Ariana Herbst
 * 1/24/17
 */
public class Billiards {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String first = sc.next();
		StringBuilder sb = new StringBuilder(first);
		sc.nextLine();
		int C = sb.length() - 2;
		int R = 0;
		while (sc.nextLine().charAt(0) != 'o')	{
			R++;
		}
		int[][] table = new int[R][C];
		int r = R;
		int c = -1;
		int dirIndex = 3;
		int clockwise = 1;
		boolean hitSideH = true;
		boolean hitSideV = false;
		while (true)
		{
			r += dirs[dirIndex][0];
			c += dirs[dirIndex][1];
			if (r >= R || r < 0) {
				
				if (hitSideV)
					clockwise *= -1;
				hitSideV = true;	hitSideH = false;
				dirIndex = (dirIndex + 4 + clockwise) % 4;
				r = r == -1 ? 0 : R - 1;
			}
			else if (c >= C || c < 0) {
				if (hitSideH)
					clockwise *= -1;
				hitSideH = true;	hitSideV = false;
				dirIndex = (dirIndex + 4 + clockwise) % 4;
				c = c == -1 ? 0 : C - 1;
			}
			table[r][c] = dirIndex % 2 == 0 ? -1 : 1;
			if ((r == 0 && c == 0 && dirIndex == 2)
					|| (r == 0 && c == C - 1 & dirIndex == 3)
					|| (r == R - 1 && c == C - 1 && dirIndex == 0)){
				break;
			}
		}
		if (r == 0)	{
			if (c == 0)
				sb.replace(0, 1, "o");
			else
				sb.replace(C + 1, C + 2, "o");
		}
		for (int i = 0; i < R; i++)	{
			sb.append("\n|");
			for (int j = 0; j < C; j++)
			{
				switch(table[i][j])	{
				case 0:
					sb.append(" ");
					break;
				case 1:
					sb.append("/");
					break;
				case -1:
					sb.append("\\");
					break;
				}
			}
			sb.append("|");
		}
		sb.append("\n/");
		if (r != 0)
		{
			sb.append(first.substring(2));
			sb.append("o");
		}
		else
			sb.append(first.substring(1));
		System.out.println(sb.toString());
		
	}
	static int[][] dirs = { {1,1}, {1,-1}, {-1,-1}, {-1,1}};
}
