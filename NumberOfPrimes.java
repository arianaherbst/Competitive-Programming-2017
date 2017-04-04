import java.util.*;
public class NumberOfPrimes {

	public static void main(String[] args)	{
		Scanner sc = new Scanner(System.in);
		System.out.println(numberOfPrimes(sc.nextInt()));
	}
	public static int numberOfPrimes(int N)	{
		boolean[] isPrime = new boolean[N+1];
		Arrays.fill(isPrime, true);
		int primes = 0;
		int i;
		for (i = 2; i*i <= N; i++)	{
			if (isPrime[i])	{
				primes++;
				for (int j = i*i; j <= N; j+=i )	{
					isPrime[j] = false;
				}
			}
		}
		for (; i <= N; i++)	{
			if (isPrime[i])
				primes++;
		}
		return primes;
	}
}
