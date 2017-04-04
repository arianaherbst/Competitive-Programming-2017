import java.util.*;
/*
 * https://projecteuler.net/problem=35
 * 4/3/17
 */
public class Euler35 {

	public static void main(String[] args) {
		boolean[] seive = new boolean[1_000_001];
		long sum = 0;
		Arrays.fill(seive, true);
		for (int i = 2; i*i <= 1_000_000; i++)	{
			if (seive[i])	{
				sum += i;
				for (int j = i*i; j <= 1_000_000; j+=i)	{
					seive[j] = false;
				}
			}
		}
		Integer k;
		int count = 0;
		loop:
		for (int i = 2; i <= 1_000_000; i++)	{
			if (seive[i])	{
				k = i;
				for (int j = 0; j < k.toString().length()-1; j++)	{
					int low = k % 10;
					k /= 10;
					k += (int)(low * Math.pow(10, k.toString().length()));
					
					if (!seive[k])
						continue loop;
				}
				count++;
			}
		}
		System.out.println(count);

	}

}
