package prob.count;

import static prob.Utility.*;
import java.util.*;

public class FindMin {
    
    /**
     * Sort first k elements.
     * 
     * Use bubble sort upto k elements.
     **/ 
    static void findK(Integer[] A, int k) {
        for(int i=0; i<=k; i++) {
            for(int j=i+1; j<A.length; j++){
                if(A[i]>A[j]) {
                    swap(A,i,j);
                }
            }
        }
    }
    
    /**
     * Find kth element from an unsorted array. 
     * 
     **/
    public static int quickSelect(Integer[] A, int start, int end, int k) {
        Random random = new Random();
        int pivot = A[start+random.nextInt(end-start)];
        partition(A, pivot);
        int pivotCount = 0, pivotPosition = 0;
        for(int i=start; i<=end && A[i]<=pivot; i++) {
            if (A[i] == pivot) pivotCount++;
            else pivotPosition++;
        }
        System.out.println("start=" + start + " end=" + end + " pivot=" + pivot + " pivotPosition=" + pivotPosition + " pivotCount=" + pivotCount);
        System.out.println(Arrays.toString(A) + "\n");
        if (k >= pivotPosition && k <= pivotPosition+pivotCount)
            return k;
        else if (k < pivotPosition) {
            return quickSelect(A, start, pivotPosition-1, k);
        } else {
            return quickSelect(A, pivotPosition+pivotCount, end, k-(pivotPosition+pivotCount));
        }    
    }
    
    public static void main (String[] s) {
        Integer[] A = randArray(14);
        System.out.println(Arrays.toString(A));
        System.out.println("Rank 3rd =" + A[quickSelect(A, 0, A.length-1, 3)]);
    }
    
}