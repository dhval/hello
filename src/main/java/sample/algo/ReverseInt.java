/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.algo;

/**
 *
 * @author c-dmudawal
 */
public class ReverseInt {

    public static void main(String[] s) {

        int input = 10;
        String out = "";
        final int size = 8-1;

        int mask = (int) Math.pow(2, size);
         for (int i = size; i >= 0; i--) {
        System.out.println(mask);
           out = out + (input & mask);
            mask = mask >> 1;
        }

        System.out.println(out);

    }
}
