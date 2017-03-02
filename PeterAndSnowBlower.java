import java.util.*;
import java.io.*;
/**
 * http://codeforces.com/problemset/problem/613/A
 * @author Ariana Herbst
 * 3/1/17
 */
public class PeterAndSnowBlower {
	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int N = sc.nextInt();
		double X = sc.nextDouble();
		double Y = sc.nextDouble();
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		double[] x = new double[N];
		double[] y = new double[N];
		for (int n = 0; n < N; n++) {
			double a = sc.nextDouble();
			double b = sc.nextDouble();
			double dist = Math.pow(a - X, 2) + Math.pow(b - Y, 2);
			x[n] = a;
			y[n] = b;
			max = Math.max(max, dist);
		}
		//ternary search
		for (int n = 0; n < N; n++)	{
			int m = (n+1)%N;
			double smallx = x[n];
			double smally = y[n];
			double bigx = x[m];
			double bigy = y[m];			
			int k = 0;
			double dx, dy, x1, x2, y1, y2, dist1, dist2;
			do {
				dx = bigx - smallx;
				dy = bigy - smally;
				x1 = smallx + dx / 3.0;
				y1 = smally + dy / 3.0;
				x2 = smallx + 2.0 * dx / 3.0;
				y2 = smally + 2.0 * dy / 3.0;
				dist1 = Math.pow(x1 - X,2) + Math.pow(y1 - Y, 2);
				dist2 = Math.pow(x2 - X,2) + Math.pow(y2 - Y, 2);
				if (dist1 < dist2)	{
					bigx = x2;
					bigy = y2;
				} else	{
					smallx = x1;
					smally = y1;
				}
				
			}//THIS ONE DOESN'T WORK// while (Math.pow(x1 - x2,2) + Math.pow(y1 - y2, 2) > // ((dist1 < dist2) ? Math.ulp(dist1) * 100 : Math.ulp(dist2) * 100 ) );
			//THIS ONE WORKS  // 100*Math.ulp(Math.max(Math.abs(y1), Math.abs(y2))+Math.max(Math.abs(x1), Math.abs(x2))));
			//For loop for 100+ for ternary search (or 60+ for binary search) is a good bet for everything except in which
			//you have to find an answer within the relative error of a very small answer.
			//But that doesn't really happen so basically always use a for-loop for binary and ternary searches.
			while(k++ < 100);
			min = Math.min(min, Math.pow(x1 - X, 2) + Math.pow(y1 - Y, 2));
		}
		double ans = Math.PI * (max-min);
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
