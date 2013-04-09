package dhval.demo.algo;

import java.util.Arrays;

/**
 *
 * @author dhval
 */
public class StringPermutation {
        static char[] chArray = {'a','b','c'};
    
    public static void main(String[] args) {
        new StringPermutation().permutate(0);
    }
    
    public void permutate(int n) {
        if (n < chArray.length) {
            for(int i=n; i<chArray.length; i++) {
                swap(i,n);
                permutate(n+1);
                swap(i,n);
            }
            
        } else
            System.out.println(Arrays.toString(chArray));
    }
    
    public void swap(int i, int j) {
        char tmp = chArray[i];
        chArray[i] = chArray[j];
        chArray[j] = tmp;
    }

}
