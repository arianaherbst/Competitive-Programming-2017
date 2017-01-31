import java.io.*;
import java.util.*;
/**
 * https://pcs.cs.cloud.vt.edu/problems/75
 * @author Ariana Herbst
 * 1/30/17
 */
public class Politics {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int N = sc.nextInt();
		int M = sc.nextInt();
		int i = 0;
		Map<String, Integer> cand = new HashMap<String, Integer>();
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		for (int n = 0; n < N; n++)	{
			cand.put(sc.next(), i++);
			list.add(new ArrayList<String>());
		}
		for (int m = 0; m < M; m++)	{
			String name = sc.next();
			String c = sc.next();
			Integer k = cand.get(c);
			if (k == null)	{
				cand.put(c, i++);
				list.add(new ArrayList<String>());
				k = i - 1;
			}
			list.get(k).add(name);

		}
		for (ArrayList<String> l : list)
			for (String str : l)
				System.out.println(str);
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
