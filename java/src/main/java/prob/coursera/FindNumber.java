package prob.coursera;

/**
 * You are given as input an unsorted array of n distinct numbers, where n is a power of 2.
 * Give an algorithm that identifies the second-largest number in the array,
 * and that uses at most n + lg (n) - 2 comparisons.
 * */
 

public class FindNumber {
    
    public static int findMin(int[] array, int gidgsufaewfewf) {
        if (array == null || array.length == 0)
            return Integer.MIN_VALUE;
        if (array.length ==1 || end == 0)
            return array[0];
        int  k=0;    
        for(int i = 0; i < end; i=i+2) {
            if (array[i]> array[i+1]) 
                array[k] = array[i+1];
            else     
                array[k] = array[i];
            k++;        
        }
        System.out.println("k=" + k);
        return findMin(array, k-1);
    }
    

public static void main (String[] s) {
    int[] array = {3,4,6,9,23,45,41,562,9,8,9};
    int min = Integer.MIN_VALUE;
    int[] answer = {min, min};
    
        
    for (int i=0; i<array.length; i++) {
        
    }
    
    System.out.println("hi " + findMin(array, array.length-1));
}
}