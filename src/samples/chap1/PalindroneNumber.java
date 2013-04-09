/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.chap1;

/**
 *
 * @author c-dmudawal
 */
public class PalindroneNumber {

    public static void main(String[] s) {
        System.out.println(isPalindrone(56565));
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
            highVal = highVal /100;
        }
        return true;
    }
    
     public static boolean isPalindroneRecur(int high, int low) {
         if (high == 0) return true;
         
         
     }
}

