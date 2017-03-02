import java.io.*;
import java.util.*;
/**
 * https://pcs.cs.cloud.vt.edu/problems/172
 * @author Ariana Herbst
 * 3/1/17
 */
public class ParalellogramIsBack {

	static class Point{
		int x, y;
		public Point(int x, int y)	{
			this.x = x; this.y = y;
		}
	}
	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int[] X = new int[3];
		int[] Y = new int[3];
		for (int i = 0; i < 3; i++)	{
			X[i] = sc.nextInt();
			Y[i] = sc.nextInt();
		}
		System.out.println(3);
		for (int i = 0; i < 3; i++)		{
			int a = i;
			int b = (i + 1) % 3;
			int c = (i + 2) % 3;
			System.out.println((X[b] + X[a] - X[c]) + " " + (Y[b] + Y[a] - Y[c]));
		}
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
