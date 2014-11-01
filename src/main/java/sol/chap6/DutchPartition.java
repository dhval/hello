package sol.chap6;

import java.util.Arrays;
import java.util.Random;

/**
 * Dijkstra's Dutch patition or 3 way partition invloves dividing an array in 3
 * parts. It is used by JDK7's  quick sort for dealing with duplicate keys.
 *
 * Input a random array with with two pivot k<=l.
 * e.g. [2,9,5,0,0,7,7,1,2,3,6] and pivots k=3, l=5
 *
 * Output [2,0,0,1,2,5,3,6,7,7,9]
 * elements A[0..4]<A[5..6]<A[7..10]
 *
 * Read More :
 * http://permalink.gmane.org/gmane.comp.java.openjdk.core-libs.devel/2628
 * http://java.dzone.com/articles/algorithm-week-quicksort-three
 * http://stackoverflow.com/questions/941447/quicksort-with-3-way-partition
 */
public class DutchPartition {


    private static int[] dutchPart(int[] A, int piv1, int piv2) {
    		// check array not empty and length >1 and piv1 <= piv2
				int lo = 0;
        int hi = A.length - 1;
        for(int i=0; i<=hi; ) {
            if (A[i] < piv1) {
                swap(A, i, lo);
                lo++; i++;
            } else if (A[i] >= piv1 && A[i] <= piv2) {
                i++;
            } else {
                swap(A, i, hi);
                hi--;
            }
        }
        return A;
    }

    private static int[] randArray() {
			int[] A = new int[10];
			Random rand = new Random();
			for (int i=0; i<10; i++) {
					A[i] = rand.nextInt(11);
			}
			return A;
    }

    private static void swap (int[] A, int i, int j) {
        if (i ==j)
            return;
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String[] s) {
        int pivot1 = 4;
        int pivot2 = 6;
        int[] A = randArray();
        System.out.println(Arrays.toString(A));
				A = dutchPart(A, pivot1, pivot2);
        System.out.println(Arrays.toString(A));
    }
}
