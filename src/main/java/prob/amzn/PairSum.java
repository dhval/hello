package prob.amzn;

import java.util.*;

/**
 * Find pair of numbers in array that add to given sum.
 *
 * https://www.evernote.com/l/AAvto1S2KoJPfoe0Av4a4m0bjLM9x8xEb0M
 * */
public class PairSum {

  /**
   *  Maintain start and end pointer and run until they cross each other.
   **/
  static boolean findSum(Integer[] array, int num) {
    for(int i=0, j=array.length-1; i<j;) {
      int sum = array[i] + array[j];
      if (sum == num)
        return true;
      else if (sum > num)
        j--;
      else
        i++;
    }
    return false;
  }

  public static void main (String[] s) {
    Integer[] array = prob.Utility.randArray(14);
    Arrays.sort(array);
    Random random = new Random();
    int p1=0, p2=0;
    while (p1 == p2) {
      p1 = random.nextInt(14);
      p2 = random.nextInt(14);
    }
    System.out.println(Arrays.toString(array));
    int sum = array[p1] + array[p2];
    System.out.format("\n sum = %d ans=%s \n", sum, findSum(array, sum));
  }

}