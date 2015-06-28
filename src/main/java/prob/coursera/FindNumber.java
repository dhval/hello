package prob.coursera;

/**
 * You are given as input an unsorted array of n distinct numbers, where n is a power of 2.
 * Give an algorithm that identifies the second-largest number in the array,
 * and that uses at most n + lg (n) - 2 comparisons.
 * */
 

public class FindNumber {

    static int count =0;

    static int min(int a, int b) {
        count++;
        return a>b ? b : a;
    }

    public static int findMin(int[] array, int s, int e) {
        if (s >=e ) return array[s];
        if ( e-s == 1) return min(array[s], array[e]);
        int m = s + (e-s)/2;
        return min (findMin(array,s,m), findMin(array,m+1,e));
    }
    

public static void main (String[] s) {
    int[] array = {36,41,86,22,19,23,45,41,562,9,8,9};
    int min = Integer.MIN_VALUE;
    int[] answer = {min, min};
    
        
    for (int i=0; i<array.length; i++) {
        
    }

    System.out.println(" Min " + findMin(array, 0, array.length-1));
    System.out.println(count +   " i ");
}
}