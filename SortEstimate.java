import java.util.*;
import java.io.*;
/**
 * https://community.topcoder.com/stat?c=problem_statement&pm=3561&rd=6519
 * @author Ariana Herbst
 * Feb 27, 2017
 */
public class SortEstimate {
	static final double eps = .000_000_0001;
	static double C, T;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double C = sc.nextDouble();
		double T = sc.nextDouble();
		
		double low = 0;
		double high = T;
		double x = 0;
		int count = 0;
		while (Math.abs(high - low) > eps && count++ < 1000)
		{
			x = (low + high) / 2.0;
			boolean val = (1.0 * Math.log(x) / Math.log(2) * C * x )<= T;
			if (val)	{
				low = x;
			} else {
				high = x;
			}
			
		}
		System.out.println(x);

	}

}
