import java.util.*;
/*
 * https://projecteuler.net/problem=10
 * 4/3/17
 */
public class Euler10 {
	// Find sum of all primes below 2,000,000
	public static void main(String[] args) {
		boolean[] seive = new boolean[2_000_001];
		long sum = 0;
		Arrays.fill(seive, true);
		int i;
		for (i = 2; i*i <= 2_000_000; i++)	{
			if (seive[i])	{
				sum += i;
				for (int j = i*i; j <= 2_000_000; j+=i)	{
					seive[j] = false;
				}
			}
		}
		for (; i <= 2_000_000; i++)	{
			if (seive[i])	{
				sum += i;
			}
		}
		System.out.println(sum);

	}

}
