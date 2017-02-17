import java.util.*;
/**
 * https://pcs.cs.cloud.vt.edu/problems/10
 * @author Ariana Herbst
 * 2/16/17
 */
public class ColoringGraphs {
	static int[] colors;
	static ArrayList<Integer>[] graph;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		graph = new ArrayList[N];
		colors = new int[N];
		sc.nextLine();
		for (int n = 0; n < N; n++)	{
			graph[n] = new ArrayList<Integer>();
		}
		for (int n = 0; n < N; n++)	{
			String[] line = sc.nextLine().split(" ");
			for (String str : line)	{
				int v = Integer.parseInt(str);
				graph[n].add(v);
				graph[v].add(n);
			}
		}
		explore(0);
		Set<Integer> colorSet = new HashSet<Integer>();
		for (int c : colors)
			colorSet.add(c);
		System.out.println(colorSet.size());
	}
	public static void explore(int node)
	{
		int tryColor = 1;
		loop:
		while(true)	{
			colors[node] = tryColor++;
			for (int i = 0; i < graph[node].size(); i++)	{
				int v = graph[node].get(i);
				if (colors[v] == 0)	{
					explore(v);
				} else if (colors[v] == colors[node])	{
					continue loop;
				}
			}
			break;
		}
	}
}
