import java.io.*;
import java.util.*;
/**
 * https://pcs.cs.cloud.vt.edu/contests/28/problems/C
 * @author Ariana Herbst
 * 1/25/17
 */
public class Top25 {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int N = sc.nextInt();
		Map<String, Integer> as = new HashMap<String, Integer>();
		for (int i = 0; i < N; i++)		{
			as.put(sc.next(), i);
		}
		StringBuilder sb = new StringBuilder();
		int prev = -1;
		int max = 0;
		for (int i = 0; i < N; i++)
		{
			int num = as.get(sc.next());
			max = Math.max(max, num);
			if (max == i)
			{
				sb.append((i - prev)+"\n");
				prev = max;
			}
			
		}
		System.out.println(sb);

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
