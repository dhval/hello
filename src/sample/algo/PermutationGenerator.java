package sample.algo;

import java.util.Arrays;

/**
 * Permutation generator, generates permutations without repetitions.
 * Using recursive algorithm.
 * http://www.cse.ohio-state.edu/~gurari/course/cis680/cis680Ch19.html
 * permute(i)
if i == N  output A[N]
else
for j = i to N do
swap(A[i], A[j])
permute(i+1)
swap(A[i], A[j])
 * @author dxm6974
 */
public class PermutationGenerator {

    char[] array = {'A', 'B', 'C', 'D'};
    ;
    int arrayLength = array.length;

    public static void main(String ar[]) {
        PermutationGenerator instance = new PermutationGenerator();
        instance.permute(0);
    }

    private void permute(int i) {
        System.out.println("Level :- " + i + " " + Arrays.toString(array));
        if (i == arrayLength - 1)
            System.out.println(Arrays.toString(array));
        else
            for (int j = i; j < arrayLength; j++) {
                swap(i, j);
                permute(i + 1);
                swap(j, i);
            }
    }

    private void swap(int i, int j) {
        char t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
