import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;
/**
 * http://codeforces.com/problemset/problem/740/B
 * @author Ariana Herbst
 * March 13, 2017
 */

public class AlyonaAndFlowers {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int N = sc.nextInt();
		int M = sc.nextInt();
		long[] prenums = new long[N+1];
		long sum = 0;
		for (int n = 1; n <= N; n++) {
			sum += sc.nextLong();
			prenums[n] = sum;
		}
		int l, r;
		long diff;
		long count = 0;
		for (int m = 0; m < M; m++)	{
			l = sc.nextInt()-1;
			r = sc.nextInt();
			diff = prenums[r] - prenums[l];
			count += diff > 0 ? diff : 0;
		}
		System.out.println(count);

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
