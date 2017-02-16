import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

/**
 * https://pcs.cs.cloud.vt.edu/problems/164
 * https://open.kattis.com/problems/canvas
 * @author Ariana Herbst
 * February 16th, 2017
 */
public class Canvas2 {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int T = sc.nextInt();
		for (int t = 0; t < T; t++)
		{
			int N = sc.nextInt();
			long internalSum = 0;
			PriorityQueue<Long> huffman = new PriorityQueue<Long>();
			for (int n = 0; n < N; n++)
			{
				long l = sc.nextLong();
				internalSum -= l;
				huffman.add(l);
			}
			while (huffman.size() > 1)
			{
				long min = huffman.poll();
				long min2 = huffman.poll();
				huffman.add(min + min2);
				internalSum += min + min2;
			}
			internalSum += huffman.poll();
			System.out.println(internalSum);
			
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
