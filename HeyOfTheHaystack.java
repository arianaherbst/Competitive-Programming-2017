import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
/**
 * https://pcs.cs.cloud.vt.edu/problems/229
 * Rolling Hash
 * @author Ariana Herbst
 * 4/12/17
 */
public class HeyOfTheHaystack {
	static Map<Long, Integer> map = new HashMap<Long, Integer>();
	static long mod = 1000_000_000_000_0061L;  // 10^16 + 61
	static long base = 29L;
	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		String haystack = sc.next();
		char[] chars = haystack.toCharArray();
		long hash = 0;
		Integer K;
		for (int i = 0; i < haystack.length(); i++) { //starting index
			hash = chars[i] - 'A' + 1;
			K = map.get(hash);
			map.put(hash, K == null ? 1 : K + 1);
			for (int size = 1; size < haystack.length() - i && size < 21; size++) {
				hash *= base;
				hash %= mod;
				hash += chars[i+size] - 'A' + 1;
				hash %= mod;
				K = map.get(hash);
				map.put(hash, K == null ? 1 : K + 1);
			}
		}
		int N = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < N; n++) {
			hash = getHash(sc.next());
			K = map.get(hash);
			sb.append((K != null ? K : 0)+"\n");
		}
		System.out.print(sb);
	}
	
	public static long getHash(String str) {
		long hash = 0;
		for (int i = 0; i < str.length(); i++) {
			hash *= base;
			hash %= mod;
			hash += str.charAt(i) - 'A' + 1;
			hash %= mod;
		}
		return hash;
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
