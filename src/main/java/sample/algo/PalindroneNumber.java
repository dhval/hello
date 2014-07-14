package sample.algo;

/**
 * Check whether given integer is palindrone or not.
 * 
 * @author c-dmudawal
 */
public class PalindroneNumber {

	public static void main(String[] s) {
		int number = 56565;

		if (s.length > 0) {
			// check for number format exception.
			number = Integer.parseInt(s[0]);
		}
		System.out.println("isPalindrone: " + isPalindrone(number));
	}

	public static boolean isPalindrone(int number) {
		if (number <= 10) {
			return false;
		}

		int highVal = (int) Math.pow(10, 9);
		while (number / highVal == 0) {
			highVal = highVal / 10;
		}
		while (number > 10) {
			int leftVal = number / highVal;
			int rightVal = number % 10;
			System.out.println(number + " " + leftVal + " " + rightVal);

			if (leftVal != rightVal) {
				return false;
			}
			number = number % highVal;
			number = number / 10;
			highVal = highVal / 100;
		}
		return true;
	}

}
