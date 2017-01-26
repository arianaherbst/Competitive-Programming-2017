import java.util.*;
/**
 * https://pcs.cs.cloud.vt.edu/contests/28/problems/D
 * @author Ariana Herbst
 * 1/25/17
 */
public class LongestSharedSubstring {

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String X = sc.next();
		String Y = sc.next();
		String ans = "";
		loop:
		for (int i = 0; i < Y.length(); i++)
		{
			for (int j = i + ans.length() + 1; j <= Y.length(); j++)
			{
				String needle = Y.substring(i, j);
				if (KMP(X, needle))	{
					ans = needle;
				}
				else
					continue loop;
			}
		}
		System.out.println(ans);
	}
	static boolean KMP(String haystack, String needle)	{
		if (haystack.length() < needle.length())	{
			return false;	}

		int[] S = prefixTable(needle.toCharArray());
		int t = 0, p = 0;

		while (p + t < haystack.length())	{
			if (haystack.charAt(t + p) != needle.charAt(p))	{
				t = t + p - S[p];
				p = -1 < S[p] ? S[p] : 0;
			} else if (p++ == needle.length() - 1)	{
				return true;
			}
		}
		return false;
	}

	public static int[] prefixTable(char[] P) {
		int[] T = new int[P.length+1];
		T[0] = -1;
		int k = 0;
		for (int i = 1; i < P.length; i++)	{
			while (k > 0 && P[k] != P[i])
				k = T[k];
			if (P[k] == P[i])
				++k;
			T[i+1] = k;
		}
		return T;
	}
}




