import java.util.*;
/**
 * https://pcs.cs.cloud.vt.edu/problems/157
 * @author Ariana Herbst
 * 1/23/17
 */
public class BackOfTheLine {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String[] str = new String[N];
		for (int n = 0; n < N; n++)
		{
			String a = sc.next();
			StringBuilder sb = new StringBuilder(a);
			sb.reverse();
			str[n] = a.compareTo(sb.toString()) > 0 ? a : sb.toString();
		}
		Arrays.sort(str, new Comparator<String>(){
			public int compare(String a, String b)
			{
				return b.compareTo(a);
			}
			
		});
		StringBuilder ans = new StringBuilder();
		for (int n = 0; n < N; n++)
			ans.append(str[n]);
		String c = ans.substring(0, 1);
		ans.delete(0, 1);
		c = c.toUpperCase();
		ans.insert(0, c);
		System.out.println(ans.toString().compareTo("Acorn") > 0 ? ans : "Acorn");

	}

}
