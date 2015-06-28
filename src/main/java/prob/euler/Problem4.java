package prob.euler;

/**
 * A palindromic number reads the same both ways. The largest palindrome made
 * from the product of two 2-digit numbers is 9009 = 91 99.
 * 
 * Find the largest palindrome made from the product of two 3-digit numbers.
 * 
 * @author Dhval
 * 
 */
public class Problem4 {

	public static void main(String[] s) {

		System.out.println(isPalindrome(9228229));

		int number = 999;
		int x = 0;
		int y = 0;
		int count = 0;
		boolean isPalindrome = false;
		while(!isPalindrome) {
			//isPalindrome;
		}
	}

	public static boolean isPalindrome(int number) {
		if (number < 0) {
			number = number * (-1);
		}
		if (number <= 10)
			return false;
		// reverse the number
		int temp_number = number;
		int power = 1;
		int reverse_number = 0;
		while (temp_number > reverse_number) {
			int last_digit = temp_number % 10;
			temp_number = temp_number / 10;
			reverse_number = (10 * reverse_number) + last_digit;
			power *= 10;
			if (reverse_number == temp_number)
				return true;
		}
		if (temp_number == (reverse_number / 10))
			return true;
		else
			return false;

	}

}