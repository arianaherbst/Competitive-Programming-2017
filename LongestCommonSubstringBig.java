import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
/**
 * https://pcs.cs.cloud.vt.edu/problems/227
 * Rolling Hash, Binary Search
 * @author Ariana Herst
 * 4/12/17
 */
public class LongestCommonSubstringBig {
	static Map<Long, Integer> map = new HashMap<Long, Integer>();
	static long mod = 1000_000_000_000_0061L;  // 10^16 + 61
	static long base = 29L;
	static char[] h1;
	static char[] h2;
	static String haystack1, haystack2;
	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		haystack1 = sc.next();
		haystack2 = sc.next();
		h1 = haystack1.toCharArray();
		h2 = haystack2.toCharArray();
		int length = binarySearch();
		System.out.println(getAnsL(h2,length));
		
	}
	
	public static String getAnsL(char[] haystack, int L) {
		Set<Long> set1 = hashLengthL(h1, L);
//		if (haystack.length < L) {
//			return set;
//		}
		long hash = 0;
		long B = 1;
		for (int size = 0; size < L; size++) {
			if (size != L-1) B *= base;
			B %= mod;
			hash *= base;
			hash %= mod;
			hash += haystack[size] - 'A' + 1;
			hash %= mod;
		}
		if (set1.contains(hash)) {
			return haystack2.substring(0, L);
		}
		// remove character on the left : subtract by base ^ (length of string - 1)
		for (int i = L; i < haystack.length; i++) { //starting index
			//System.out.println(hash +  "HASH");
			hash -= B * (haystack[i-L] - 'A' + 1);
			hash = ((hash%mod) + mod)%mod;
			hash *= base;
			hash += haystack[i] - 'A' + 1;
			hash %= mod;
			Integer K = map.get(hash);
			//map.put(hash, K == null ? 1 : K + 1);
			if (set1.contains(hash)) {
				return haystack2.substring(i-L+1, i+1);
			}
		}
		return "oops";
	}
	
	public static int binarySearch() {
		int little = 0;
		int big = Math.min(h1.length, h2.length);
		for (int i = 0; i < 60; i++)	{
			int val = (little + big) / 2;
			if (works(val))	{
				little = val;
			} else {
				big = val;
			}
		}
		
		return works(big+1) ? big+1 : works(big) ? big : little;
	}
	
	public static boolean works(int L) {
		Set<Long> map1 = hashLengthL(h1, L);
		Set<Long> map2 = hashLengthL(h2, L);
		int size1 = map1.size();
		int size2 = map2.size();
		map1.addAll(map2);
		return (map1.size() < size1 + size2);
	}
	public static Set<Long> hashLengthL(char[] haystack, int L) {
		Set<Long> set = new HashSet<Long>();
		if (haystack.length < L) {
			return set;
		}
		long hash = 0;
		long B = 1;
		for (int size = 0; size < L; size++) {
			if (size != L-1) B *= base;
			B %= mod;
			hash *= base;
			hash %= mod;
			hash += haystack[size] - 'A' + 1;
			hash %= mod;
		}
		set.add(hash);
		// remove character on the left : subtract by base ^ (length of string - 1)
		for (int i = L; i < haystack.length; i++) { //starting index
			//System.out.println(hash +  "HASH");
			hash -= B * (haystack[i-L] - 'A' + 1);
			hash = ((hash%mod) + mod)%mod;
			hash *= base;
			hash += haystack[i] - 'A' + 1;
			hash %= mod;
			Integer K = map.get(hash);
			//map.put(hash, K == null ? 1 : K + 1);
			set.add(hash);
		}
		return set;
		
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
