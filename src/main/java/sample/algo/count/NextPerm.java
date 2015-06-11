package sample.algo.count;

import java.util.Arrays;

/**
 * Input    1 2 5 4 3
 * Output   1 3 2 4 5
**/

public class NextPerm {
    
    static Integer A[] = { 2, 3, 4, 5, 1 };
    
    static Integer[] next(Integer A[]) {
        // find first left element smaller than one of the right element.
        int size = A.length - 1;
        int k = -1;
        outer:
        for(int i=size; i>0; i--) {
          int select = i;    
          for(int j = i-1; j>=0; j--)
            if (A[j]<A[i]) {
              int tmp = A[j];
              A[j] = A[i];
              A[i] = tmp;
              k = j+1;
              break outer;
            }
        }
        System.out.println("k = " + k);
        Arrays.sort(A, k, size+1);
        return A;
    }
    
    public static void main(String s[]) {
        next(A);
        System.out.println(Arrays.toString(A));
    }
    
}