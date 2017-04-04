import java.util.*;
/*
 * https://projecteuler.net/problem=10
 * 4/3/17
 */
public class Euler7 {
	public static void main(String[] args)	{
		int primes = 0;
		boolean[] isPrime = new boolean[10_000_001];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		int i;
		for (i = 2; i*i <= 10_000_000; i++)	{
			if (isPrime[i])	{
				primes++;
				if (primes == 10_001)	{
					System.out.println(i);
					System.exit(0);
				}
				for (int j = i*i; j <= 10_000_000; j+=i)	{
					isPrime[j] = false;
				}
			}
		}
		for (; i <= 10_000_000; i++)	{
			if (isPrime[i])	{
				primes++;
				if (primes == 10_001)	{
					System.out.println(i);
					System.exit(0);
				}
			}
		}
	}
}
