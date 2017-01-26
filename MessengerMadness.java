import java.util.*;
/**
 * https://pcs.cs.cloud.vt.edu/contests/28/problems/B
 * @author Ariana Herbst
 * 1/25/17
 */
public class MessengerMadness {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		//to real names
		Map<String, String> map = new HashMap<String, String>();
		for (int n = 0; n < N; n++)
		{
			String s = sc.next();
			map.put(s, s);
		}
		for (int m = 0; m < M; m++)
		{
			sc.next();
			String from = sc.next();
			String to = sc.next();
			String real = map.get(from);
			map.remove(from);
			map.put(to, real);
		}
		ArrayList<String> nicks = new ArrayList<String>(map.keySet());
		Collections.sort(nicks);
		if (map.keySet().size() == N)	{
			for (String nick : nicks)
				System.out.println(nick+":"+map.get(nick));
		}
		else
		{
			System.out.println("Invalid");
		}

	}

}
