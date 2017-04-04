import java.util.*;
public class IsPrime {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(isPrime(sc.nextLong()));
		
	}
	
	public static boolean isPrime(long N)	{
		for (int i = 2; i * i <= N; i++)	{
			if (N % i == 0)
				return false;
		}
		return true;
		
	}

}
