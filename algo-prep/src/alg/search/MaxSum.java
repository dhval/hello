package alg.search;

/**
 * Max Possible Sum Of Non-Consecutive Elements
 * 
 *  Posted by Akash in Sunday, May  * 17, 2009 Question: 
 * 
 * There is an integer array consisting positive numbers
 * only. Find maximum possible sum of elements such that there are no 2
 * consecutive elements present in the sum. (2nd Question form Amazon 1st
 * telephonic)
 * 
 * Example: If given array is (6, 4, 2, 8, 1), the answer will be 14 (8+6). This
 * is the maximum sum we can obtain without taking two consecutive elements.
 * 
 * @author c-dmudawal
 * 
 */

public class MaxSum {
	
	public static void main(String[] args) {
		 int[] array = {6, 4, 2, 8, 1};
		 
		 int min = array[0];
		 int maxSum = array[0];
		 int curSum = array[0];
			
			for(int i = 1; i < array.length; i++) {
				int currentElem = array[i];
				if (currentElem >= min) {
					curSum += currentElem;
				} else {
					min = curSum = currentElem;					
				}
				if (curSum > maxSum) {
					maxSum = curSum;
				}
			}

	}
}
