import java.util.*;
/**
 * https://pcs.cs.cloud.vt.edu/submissions/6410
 * @author Ariana Herbst
 * 3/1/17
 */
public class Beru_Taxi {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double x = sc.nextInt();
		double y = sc.nextInt();
		int N = sc.nextInt();
		double min = Double.MAX_VALUE;
		for (int n = 0; n < N; n++){
			min = Math.min(min, Math.sqrt(Math.pow(sc.nextDouble() - x, 2) + Math.pow(sc.nextDouble() - y, 2)) / sc.nextDouble());
		}
		System.out.println(min);
	}
}
