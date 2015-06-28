package prob.euler;

/**
 * Each new term in the Fibonacci sequence is generated by adding the previous
 * two terms. By starting with 1 and 2, the first 10 terms will be:
 * 
 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
 * 
 * By considering the terms in the Fibonacci sequence whose values do not exceed
 * four million, find the sum of the even-valued terms.
 * 
 * @author Dhval
 * 
 */

public class Problem2 {

	public static void main(String[] s) {

		System.out.println(fibo(1, 2, 0));
	}

	public static int fibo(int x, int y, int sum) {
		if (y >= 4 * 1000 * 1000)
			return sum;

		if (y % 2 == 0)
			sum += y;

		return fibo(y, x + y, sum);
	}

}
