/*
F(n) = F(n-1) + F(n-2)
 */
package dhval.demo.algo;

/**
 *
 * @author dhval
 */
public class FibonacciDynPrgmg {

    public static void main(String[] arg) {
        final int fn = 5;
        int[] f = new int[fn+1];
        f[1] = f[2] = 1;
        for (int i = 3; i <= fn; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        System.out.println(f[fn]);

    }
}
