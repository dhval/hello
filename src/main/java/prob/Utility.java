/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prob;

import java.util.Random;

/**
 *
 * @author Dhval
 */
public class Utility {

    /**
     * 
     * @return A random integer array. 
     */
    public static Integer[] randArray(int len) {
        Integer[] A = new Integer[len];
        Random rand = new Random();
        for (int i = 0; i < len; i++) {
            A[i] = rand.nextInt(len*5+1);
        }
        return A;
    }

    /**
     * 
     * @param A, array to be swapped
     * @param i, index of first element
     * @param j, index of second element 
     */
    public static void swap(int[] A, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
}
