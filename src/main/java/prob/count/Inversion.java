package prob.count;

import java.util.Arrays;

/**
  Finding inversion in array using merge sort.

  http://www.geeksforgeeks.org/counting-inversions/
  https://www.quora.com/How-do-you-count-split-inversions-with-the-merge-sort-algorithm
  http://www.spoj.com/problems/INVCNT/
  http://codesays.com/2014/solution-to-array-inversion-count-by-codility/
**/

public class Inversion {

// merge two array and return inversion count
static int merge(Integer[] array, int start, int end) {
  if (start == end) return 0;
  System.out.println("start=" + start + " end=" + end);
  // in place merging two array
  int mid = (start + end)/2;
  int inversion = 0;
  int i = start;
  int j = mid;
  int pos = 0;
  Integer[] tmp = new Integer[end-start+1];
  while(i < mid && j < end) {
    if (array[i] > array[j]) {
      tmp[pos++] = array[j++];
      // all elements in right are inversion.
      inversion += (mid-i);
    } else {
      tmp[pos++] = array[i++];
    }
  }
  while (i < mid) { tmp[pos++] = array[i++]; }
  while (j < end) { tmp[pos++] = array[j++]; }
  System.out.println(Arrays.toString(array));
  for (i=start; i<end; i++) { array[i] = tmp[i-start]; }
  System.out.println(Arrays.toString(array));
  System.out.println();
  return inversion;
}

static int findInversion(Integer[] array, int start, int end) {
  if (end-start > 1) {
    int mid = (start + end)/2;
    int left = findInversion(array, start, mid);
    int right = findInversion(array, mid, end);
    // merge arrays and check for inversion
    int inversion = merge(array, start, end);
    System.out.println("left=" + left + " right=" + right + " LR=" + inversion);
    return left + right + inversion;
  }
  return 0;
}

public static void main(String[] s) {
  //Integer[] array = {7, 8, 9, 3, 4, 5};
  Integer[] array = {2, 4, 1, 3, 5};
  int inversion = findInversion(array, 0, array.length);
  System.out.println("I=" + inversion);

}

}