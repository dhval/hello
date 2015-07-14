package sample.algo.count;

import java.util.*;

/**
 *  Sort by LSB to MSB to avoid recursion. We can just have an array[10] if numbers are unique.
 **/

public class RadixSort {
    
    
    static void sortK(Integer[] A, int k) {
        List<Integer>[] list = new ArrayList[10];
        for(int i=0; i<10; i++) list[i] = new ArrayList<Integer>();
        for(int i=0; i<A.length; i++) {
            int elem = A[i];
            int digit = elem/((int) Math.pow(10, k)) % 10; 
            list[digit].add(elem);
        }
        int count=0;
        for(int i=0; i<10; i++) {
            Iterator<Integer> itr = list[i].iterator();
            while(itr.hasNext()) A[count++]=itr.next();
        }
        System.out.println("k=" + k + " " + Arrays.toString(A));
    }
    
    static void radix(Integer[] A, int max) {
        // max digit size
        int k = (int) Math.log10(max);
        for(int i=0; i<k; i++)
            sortK(A, i);
    }
    
    public static void main(String[] s) {
        Integer[] rand = prob.Utility.randArray(25);
        System.out.println(Arrays.toString(rand));
        radix(rand, 1000);
    }
    
}