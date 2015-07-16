package prob;

import java.util.Arrays;
import static prob.Utility.*;

/**
 *
 * Shift an array. When a number moves off the right side of the array, it becomes the first element.
a. [1,2,3] rotated right by 1 would be [3, 1, 2].
b. [1, 2, 3, 4, 5, 6, 7] rotated right by 3 would be [3, 4, 5, 6, 7, 1, 2]

*/
public class Prob2 {

    static void shift(Integer[] A, int k) {
        if (k <= 0) return;
        int length = A.length - 1;
        while (k > 0) {
            for (int i = length; i > 0; i--)
                swap(A, i, i - 1);
            System.out.println(Arrays.toString(A));
            k--;
        }
    }

    public static void main(String[] args) {
        int max = 13;
        Integer[] array = new Integer[max];
        for (int i = 0; i < max; i++)
            array[i] = i + 1;
        shift(array, 3);
    }
}
