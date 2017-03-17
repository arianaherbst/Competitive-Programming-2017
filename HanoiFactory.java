import java.util.*;
import java.io.*;
/**
 * Hanoi Factory
 * Segment Trees, Longest Increasing Subsequence, Coordinate Compression
 * http://codeforces.com/contest/777/problem/E
 * @author Ariana Herbst
 * March 17, 2017
 */
public class HanoiFactory {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int N = sc.nextInt();
		Ring[] rings = new Ring[N];
		List<Long> coordList = new ArrayList<Long>();
		for (int n = 0; n < N; n++)	{
			long l = sc.nextLong();
			long r = sc.nextLong();
			long h = sc.nextLong();
			rings[n] = new Ring(l, r, h);
			coordList.add(l);
			coordList.add(r);
		}
		Arrays.sort(rings);
		Compressor<Long> comp = new Compressor<Long>(coordList);
		MaxSegTree segTree = new MaxSegTree(0, 2 * N -1);
		long next;
		int compL, compR;
		for (int n = 0; n < N; n++)	{
			compL = comp.compress(rings[n].left);
			compR = comp.compress(rings[n].right);
			next = segTree.query(compL+1, 2 * N - 1);
			segTree.update(compR, next + rings[n].height);
		}
		System.out.println(segTree.query(0, 2 * N - 1));
	}

	static class Compressor<T extends Comparable<T>>	{
		List<T> real;
		Compressor(List<T> real)	{
			Collections.sort(this.real = real);
		}

		//compression, usually called once per realval
		int compress(T realval) {
			return Collections.binarySearch(real, realval);
		}

		T uncompress(int compval)	{  return real.get(compval); }
	}



	public static class Ring implements Comparable<Ring> {
		long left, right, height;
		public Ring(long left, long right, long height)	{
			this.left = left;
			this.right = right;
			this.height = height;
		}
		public int compareTo(Ring other)	{
			int c = Long.compare(this.right, other.right);
			return c != 0 ? c : Long.compare(this.left, other.left);
		}
	}
	public static class MaxSegTree	{
		MaxSegTree lTree, rTree;
		long value;
		long lBound, rBound;
		public MaxSegTree(long l, long r)	{
			lBound = l;
			rBound = r;
			value = 0;
			if (l != r)	{
				lTree = new MaxSegTree(l,(l + r)/2);
				rTree = new MaxSegTree((l + r)/2 + 1, r);
			}
		}
		public long query(long ql, long qr)	{
			//completely contained
			if (ql <= lBound && rBound <= qr)	{
				return value;
			} else if (ql > rBound || qr < lBound)	{
				return 0;
			} else {
				return Math.max(lTree.query(ql, qr), rTree.query(ql, qr));
			}
		}
		public void update(long loc, long height)	{
			// contained
			if (lBound <= loc && loc <= rBound)	{
				value = Math.max(value, height);
				if (lBound != rBound)	{
					lTree.update(loc, height);
					rTree.update(loc, height);
				}
			}
			
		}
		public void print()	{
			System.out.println(lBound + ", " + rBound + " value: " + value );
			if (lBound != rBound)	{
				lTree.print();
				rTree.print();
			}
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