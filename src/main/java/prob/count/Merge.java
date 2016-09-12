package prob.count;

import java.util.Arrays;

// In place merge for two sorted sub arrays.
// Input i=0,j=3, A=[ 1,3,5,2,4,6 ]
// Output [1,2,3,4,5,6]

public class Merge {

  static void swap(Integer[] A, int i, int j) {
    int tmp = A[i];
    A[i] = A[j];
    A[j] = tmp;
  }

  public static void merge(Integer[] A, int i, int j) {

    while (i < j) {
      if (A[i] > A[j]) {
        // insert tmp in the correct, sorted place.
        swap(A, i, j);
        int k = j;
        while (k < A.length - 1 && A[k] > A[k + 1]) {
          swap(A, k, k + 1);
          k++;
        }
      }
      else {
        i++;
      }
    }

  }

  public static void main(String[] s) {
    Integer[] A = {
      1, 3, 5, 2, 4, 6
    };
    merge(A, 0 , 3);
    System.out.println(Arrays.toString(A));
  }

}