/*
You are given as input an unsorted array of n distinct numbers, where n is a power of 2. 
Give an algorithm that identifies the second-largest number in the array, and that uses at most n+log₂(n)−2 comparisons.

http://stackoverflow.com/questions/9889679/find-second-largest-number-in-array-at-most-nlog%E2%82%82n%E2%88%922-comparisons
*/

package sol.chap6;

import java.util.Arrays;

/**
 *
 * @author Dhval
 */
public class QuickSort {
    /*
        Rearranges the array so that A[i] = pivot, A[0..i-1] < pivot > A[i+1..n] 
    */
    static void dutchPartition(int A[], int pivot) {
        
        int len = A.length;
        int sml = -1, mid = -1;
        for(int i = 0; i < len; i++) {
            int element = A[i];
            if (element < pivot) {
                sml++;
                if (sml == mid) {
                    swap(A, ++sml, i);
                    sml++;mid++;
                    continue;
                }
                swap(A, ++sml, i);
                swap(A, ++mid, i);
            } else if (element == pivot) {
                swap(A, ++mid, i); 
            }
        }
        System.out.println(sml + " " + mid);
    }
    
    /*
        Find k small elements in array.
    */
    static void qSort(int[] A, int k) {
        
    }
    
    static int[] array = {7,58,9,3,6,2,9,45,8,23,12,8,3,5,6,1};
    
    static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    public static void main(String[] s) {
        dutchPartition(array, 8);
        System.out.println(Arrays.toString(array));
    }
    
}
