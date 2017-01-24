import java.util.*;
/**
 * https://pcs.cs.cloud.vt.edu/problems/158
 * @author Ariana Herbst
 * 1/23/17
 */
public class UnbalancedParenthesis {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] p =sc.next().toCharArray();
		int s = 0;
		for (int i = 0; i < p.length; i++)
		{
			if (p[i] == '(')
				s++;
			else if (s <= 0)	{
				System.out.println("Unbalanced");
				System.exit(0);
			}
			else
				s--;
		}
		System.out.println(s == 0 ? "Balanced" : "Unbalanced");
	}

}
