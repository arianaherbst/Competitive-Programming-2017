import java.util.Scanner;
/**
 * November 15, 2017
 * Mid-Atlantic Regionals 2017, Problem C: Purple Rain
 * https://open.kattis.com/problems/purplerain
 * DP
 */
public class PurpleRain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] in = sc.next().toCharArray();
		int[] color = new int[in.length+1];
		for (int i = 1; i <= in.length; i++) {
			if (in[i-1] == 'R') {
				color[i] = 1;
			} else {
				color[i] = -1;
			}
		}
		int[] a = getAns(color);
		for (int i = 1; i <= in.length; i++) {
			color[i] *= -1;
		}
		int[] b = getAns(color);
		if (b[0] > a[0]) System.out.println(b[1] + " " + b[2]);
		else if (b[0] == a[0] && b[1] < a[1]) System.out.println(b[1] + " " + b[2]);
		else System.out.println(a[1] + " " + a[2]);
	}
	static int[] getAns(int[] color) {
		int currPref = 0;
		int minPrefix = 0;
		int minPrefixIndex = 1;
		int[] toReturn = new int[3];
		for (int i = 1; i < color.length; i++) {
			currPref += color[i];
			if (currPref < minPrefix) {
				minPrefix = currPref;
				minPrefixIndex = i+1;
			}
			int currDiff = currPref - minPrefix;
			if (currDiff > toReturn[0]) {
				toReturn[0] = currDiff;
				toReturn[1] = minPrefixIndex;
				toReturn[2] = i;
			}
		}
		return toReturn;
	}

}
