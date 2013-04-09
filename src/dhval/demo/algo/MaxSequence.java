/*
Longest Monotone Sequence and Palindromes
Problem: Given an array of integers, find the longest subsequence of elements
which monotonically increases. for ex. array = {1 4 8 2 5 7 3 4 6}, the longest
subsequence = {1 2 3 4 6}
*/

package dhval.demo.algo;

/**
 *
 * @author dhval
 */
public class MaxSequence {
    static int[] intArray = {1, 4, 8, 2, 5, 7, 3, 4, 6};
    static int sizeArray = intArray.length;
    static MaxSequence instance = new MaxSequence();
    
    public static void main (String[] ar) {
        int maxMatchCount = 0;
        int maxPosition = -1;
        for(int i=0; i<sizeArray; i++) {
            int matchCount = instance.countMaxSeq(i);
            if (matchCount > maxMatchCount)
            {
                maxMatchCount = matchCount;
                maxPosition = i;
            }
        }
        System.out.println("Position Start " + maxPosition);
        int lastMinNum = intArray[maxPosition];
        for(int i=maxPosition+1; i<sizeArray; i++) {
            if (lastMinNum <= intArray[i]) 
            {
                lastMinNum = intArray[i];
                System.out.print(lastMinNum);
                
            }
        }
        
    }

    public int countMaxSeq(int position) {
        if (position >= sizeArray) return 0;
        int lastMinNum = intArray[position];
        int count = 0;
        for(int i=position+1; i<sizeArray; i++) {
            if (lastMinNum <= intArray[i])  {
                count++;
                lastMinNum = intArray[i];
            }
        }
       return count;
    }
}
