/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prob;

/**
 *
 * @author c-dmudawal
 */
public class Samples {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int array[] = {8, 74, 2, 56, 25, 46, 31, 89, 25, 19};

        for (int i = 1; i < array.length; i++) {
            int pos = i;
            int num = array[i];
            while (pos > 0 && array[pos] < array[pos - 1]) {
                array[pos + 1] = array[pos];
                pos--;
            }
            array[pos] = num;
        }

    }

    private static void swap(int array[], int x, int y) {
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }
}
