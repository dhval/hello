package prob.euler;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we
 * get 3, 5, 6 and 9. The sum of these multiples is 23.
 * 
 * Find the sum of all the multiples of 3 or 5 below 1000.
 * 
 * @author Dhval
 * 
 */

public class Problem1 {

	public static void main(String[] s) {

		System.out.println(findSum1(1000));
	}

	public static int findSum1(int number) {
		int sum = 0;

		for (int count = 1; count < number; count++) {
			if (count % 3 == 0 || count % 5 == 0) {
				sum += count;
			}
		}
		return sum;
	}

}
