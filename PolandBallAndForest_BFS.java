import java.util.*;
import java.io.*;
/**
 * http://codeforces.com/problemset/problem/755/C
 * @author Ariana Herbst
 * 3/20/17
 */
public class PolandBallAndForest_BFS {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int N = sc.nextInt();
		List<Integer>[] graph = new ArrayList[N];
		for (int n = 0; n < N; n++) {
			graph[n] = new ArrayList<Integer>();
		}
		for (int n = 0; n < N; n++)	{
			int j = sc.nextInt()-1;
			graph[n].add(j);
			graph[j].add(n);
		}
		int[] comps = new int[N];
		int i = 0;
		Queue<Integer> queue;
		for (int n = 0; n < N; n++)	{
			if (comps[n] == 0) {
				++i;
				queue = new ArrayDeque<Integer>();
				queue.add(n);
				while (!queue.isEmpty())	{
					int u = queue.poll();
					if (comps[u] == 0) {
						comps[u] = i;
						for (Integer v : graph[u])	{
							if (comps[v] == 0)	{
								queue.add(v);
							}
						}
					}
					
				}
				
			}
		}
		System.out.println(i);

	}
	
	public static class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		public FastScanner(Reader in) {
			br = new BufferedReader(in);
		}
		public FastScanner() {
			this(new InputStreamReader(System.in));
		}
		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
		long nextLong() {
			return Long.parseLong(next());
		}
		double nextDouble() {
			return Double.parseDouble(next());
		}
		String readNextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
		int[] readIntArray(int n) {
			int[] a = new int[n];
			for (int idx = 0; idx < n; idx++) {
				a[idx] = nextInt();
			}
			return a;
		}
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int idx = 0; idx < n; idx++) {
				a[idx] = nextLong();
			}
			return a;
		}
	}

}
