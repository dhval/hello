package prob;

import java.util.*;

public class Prob1 {

    /**
     * http://www.geeksforgeeks.org/print-missing-elements-that-lie-in-range-0-99/
     * 
     Input: {9, 6, 900, 850, 5, 90, 100, 99}
     Output: 0-4
             7-8
             10-89
             91-98
     **/
    public static void main(String[] args) {
        int[][] input = {
           {9, 6, 900, 850, 5, 90, 100, 99},
           {88, 105, 3, 2, 200, 0, 10}
        };
        
        int min = 0;
        int max = 99;
        int[] array = input[0];
        Set<Integer> set = new HashSet<>();


        for (int i = 0; i < array.length; i++) {
            int item = array[i];
            if (item >= min && item <= max)
                set.add(item);
        }
        
        // add element to pad end.
        set.add(max+1);
        
        int start = min;
        int end = min;
        for(int i=min; i<=max+1; i++) {
            if (set.contains(i)) {
                if (start<i) System.out.println(start + " - " + (i-1));
                start = i+1;
            }
        }
    }

}
