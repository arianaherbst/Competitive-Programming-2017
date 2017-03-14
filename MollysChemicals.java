import java.io.*;
import java.util.*;
import java.util.Map.Entry;
/**
 * Prefix Sums
 * http://codeforces.com/problemset/problem/776/C
 * @author Ariana Herbst
 * March 13, 2017
 */

public class MollysChemicals {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int N = sc.nextInt();
		int K = sc.nextInt();
		Map<Long, Long> table = new HashMap<Long, Long>();		
		long[] presums = new long[N+1];
		long sum = 0;
		long max = 0;
		table.put(0L, 1L);
		Long k, l, elem;
		for (int n = 1; n <= N; n++)	{
			elem = sc.nextLong();
			sum += elem;
			presums[n] = sum;
			k = table.get(sum);
			table.put(sum, k == null ? 1 : k + 1);
			max += Math.abs(elem);
		}
		long ans = 0;
		loop:
		for (int n = 1; n <= N; n++) {
			long prev = 0;
			int first = 0;
			l = table.get(presums[n-1]);
			if (l != null && l <= 1)	{
				table.remove(presums[n-1]);
			} else  if (l != null){
				table.put(presums[n-1], l-1);
			}
			for (long target = 1; Math.abs(target) <= Math.abs(max); target *= K)	{
				if (Math.abs(prev)-Math.abs(target) > 1 || (K == 1 && first == 1) || (K == -1 && first == 2))	{
					continue loop;
				}				
				k = table.get(target + presums[n-1]);
				ans += k == null ? 0 : k;
				prev = target;
				first++;
			}
		}
		System.out.println(ans);

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
