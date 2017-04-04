import java.util.*;
/*
 * https://projecteuler.net/problem=37
 * 4/3/17
 */
public class Euler37 {


	public static void main(String[] args) {
		boolean[] seive = new boolean[200_000_001];
		Arrays.fill(seive, true);
		seive[0] = false;
		seive[1] = false;
		for (int i = 2; i*i <= 200_000_000; i++)	{
			if (seive[i])	{
				for (int j = i*i; j <= 200_000_000; j+=i)	{
					seive[j] = false;
				}
			}
		}
		int count = 0;
		long sum = 0;
		loop:
			for (int i = 11; i < 200_000_000; i++)	{
				if (seive[i])	{
					Integer k = i;
					//truncate from the right
					for (; k >= 1; k/=10)	{
						if (!seive[k])	{
							continue loop;
						}
					}
					// truncate from the left
					k = i;
					for (int j = k.toString().length()-1; j > 0; j--)	{
						k %=(int) Math.pow(10, j);
						if (!seive[k])	{
							continue loop;
						}
					}
					System.out.println(i);
					count++;
					sum += i;
					if (count == 11)	{
						System.out.println(sum);
						System.exit(0);
					}
				}
			}
	}
}
