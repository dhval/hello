package sol.chap6;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * Sort array at A[i] position.
 *
 * @author Dhval
 */
public class DutchPartition {
    
    
    private static void dutchPart(int[] A, int lo, int hi) {
        int len = A.length-1;
        int loIndex = -1;
        for(int i=0; i<len; ) {
            if (A[i] > hi) {
                swap(A, i, len);
                len--;
            } else if (A[i] < lo) {
                swap(A, ++loIndex, i);
                i++;
            } else {
                i++;
            }
        }
    }

    static final int[] A = {3, 9, 56, 1, 34, 2, 72, 5, 3, 9};
    
    static final int size = A.length;

    private static void swap (int[] A, int i, int j) {
        if (i ==j)
            return;
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    public static void main(String[] s) {
        int pivot = 9;
        int small = -1;
        int mid = -1;
        int big = -1;

        for (int i = 0; i < A.length; i++) {
            if (A[i] < pivot) {
                swap(A, ++small, i);
                if (mid >= small) 
                    swap(A, ++mid, i);
                else
                    mid++;
                swap(A, ++big, i);
            } else if (A[i] == pivot) {
                swap (A, ++mid, i);
               swap(A, ++big, i);
            } else {
               swap(A, ++big, i);
            }
        }

        System.out.println(Arrays.toString(A));
    }
}
