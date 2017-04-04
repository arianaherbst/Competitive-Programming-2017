import java.util.*;
/*
 * https://projecteuler.net/problem=27
 * 4/3/17
 */
public class Euler27 {

	public static void main(String[] args) {
		boolean[] seive = new boolean[20_000_001];
		long sum = 0;
		Arrays.fill(seive, true);
		int i;
		for (i = 2; i*i <= 20_000_000; i++)	{
			if (seive[i])	{
				sum += i;
				for (int j = i*i; j <= 20_000_000; j+=i)	{
					seive[j] = false;
				}
			}
		}
		int max = 0;
		int A = -1;
		int B = -1;
		for (int a = -999; a < 1000; a++)	{
			loop:
			for (int b = -1000; b <= 1000; b++) {
				int count = 0;
				for (int n = 0;n < 100000000;n++)	{
					int val = n*n + a*n +b;
					if (val > 0 &&seive[val])	{
						count++;
						if (count > max) {
							max = Math.max(count, max);
							A = a;
							B = b;
						}
					} else {
						continue loop;
					}
				}
			}
		}
		System.out.println(A*B);

	}

}
