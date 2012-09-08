package alg.problem;

/**
 * Given a number, find the next higher number which has the exact same set of
 * digits as the original number. For example: given 14532 return 15234
 * 
 * 9368205/given-a-number-find-the-next-higher-number-which-has-the-exact-same-
 * set-of-digi
 * 
 * 1. From Right to Left find first non-increasing number here 4
 * 
 * 2. Find smallest number greater than this number here 5
 * 
 * 3. Replace the larger number in place
 * 
 * 4. Sort the array in descending order from right of larger number
 */

public class NextPerm {

	public static void nextPerm(int[] array) {
		int length = array.length - 1;
		int i = length;
		for (; i > 0; i--) {
			if (array[i] > array[i - 1])
				break;

		}
		
		if ( i == 0) { //not found 
			return;
		}
	
		int s1 = array[i];
		int s2 = array[i-1];

		// from right to left find first element less than right-most element.
		int swapPos = array.length - 2;
		while (swapPos >= 0) {
			if (array[swapPos] < array[array.length - 1]) {
				break;
			}
			swapPos--;
			if (swapPos < 0) {
				// not found
				return;
			}
		}

		for (int i = array.length - 1; i > 0; i++) {

		}

	}

	public static void main(String[] s) {
		int array[] = { 1, 2, 3, 4, 5 };

		// check array not empty & length > 1

		do {

		} while (true);

	}

}
