import java.util.*;
import java.util.Map.Entry;
/**
 * https://pcs.cs.cloud.vt.edu/contests/28/problems/A
 * @author Ariana Herbst
 * 1/25/17
 */
public class Polling {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < N; i++)
		{
			String s = sc.next();
			Integer k =map.get(s);
			map.put(s, k == null ? 1 : k + 1);
		}
		int max = 0;
		ArrayList<String> names = new ArrayList<String>();
		for (Entry<String, Integer> entry : map.entrySet())
		{
			if (entry.getValue() > max)
			{
				names = new ArrayList<String>();
				max = entry.getValue();
				names.add(entry.getKey());
			}
			else if (entry.getValue() == max)
			{
				names.add(entry.getKey());
			}
		}
		Collections.sort(names);
		for (String str : names)
			System.out.println(str);

	}

}
