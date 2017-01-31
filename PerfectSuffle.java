import java.util.*;
/**
 * https://pcs.cs.cloud.vt.edu/contests/31/problems/A
 * @author Ariana Herbst
 * 1/30/17
 */
public class PerfectSuffle {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String[] s = new String[N];
		for (int n = 0; n < N; n++)
			s[n] = sc.next();
		int k = N % 2 == 1 ? 1 : 0;
		for (int i = 0; i < Math.ceil(N / 2.0); i++)
		{
			System.out.println(s[i]);
			if (i + N/2 + k < N)
				System.out.println(s[i + N /2 + k]);
		}

	}

}
