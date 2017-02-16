import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

/**
 * https://pcs.cs.cloud.vt.edu/problems/164
 * https://open.kattis.com/problems/canvas
 * @author Ariana Herbst
 * February 15th, 2017
 */
public class Canvas {
	static int ind;
	public static void main(String[] args)
	{
		FastScanner sc = new FastScanner();
		int T = sc.nextInt();
		for (int t = 0; t < T; t++)	{
			int N = sc.nextInt();
			ind = 0;
			TreeSet<Tree> canvasses = new TreeSet<Tree>();
			for (int n = 0; n < N; n++)	{
				canvasses.add(new Tree(sc.nextLong()));
			}
			while (canvasses.size() > 1)
			{
				Tree min = canvasses.first();
				canvasses.remove(min);
				Tree min2 = canvasses.first();
				canvasses.remove(min2);
				Tree next = new Tree(min.value + min2.value);
				next.left = min;
				next.right = min2;
				canvasses.add(next);
			}
			System.out.println(canvasses.first().sumOfInternalNodes());
		}
	}
	public static class Tree implements Comparable<Tree> {
		long value;
		Tree left, right;
		int index;
		public Tree(long value)
		{
			this.value = value;
			index = ind++;
		}
		public int compareTo(Tree other)
		{
			int c = Long.compare(value, other.value);
			return c == 0 ? Integer.compare(index, other.index) : c;
		}
		
		public long sumOfInternalNodes()	{
			long sum = 0;
			if (left != null || right != null)	{
				sum += value;
				sum += left.sumOfInternalNodes();
				sum += right.sumOfInternalNodes();
			}
			return sum;
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
