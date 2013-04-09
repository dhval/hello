package javaalg;

import java.util.Arrays;

/**
 *
 * @author dm
 */
public class Main {

    static char charArray[];
    static int N;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        charArray = new char[3];
        charArray[0] = 'A';
        charArray[1] = 'B';
        charArray[2] = 'C';
        N = charArray.length - 1;
//        permute(0);
        permute2(2);
        System.out.println(result);
        // TODO code application logic here
    }

    public static void permute(int i) {
        if (i == N)
            System.out.println(Arrays.toString(charArray));
   else
      for (int j = i; j <= N; j++) {
         swap(i, j);
         permute(i+1);
         swap(i, j);
    }
    }

    static int result = 0;

     public static void permute2(int i) {
         if (i == 1) {
             result++;
             System.out.println(Arrays.toString(charArray));
         } else {
             for (int j = N - i + 1; j <= N; j++) {
//                 swap(i, j);
                 permute2(i - 1);
                 swap(i, j);
             }
         }
    }

    public static void swap(int a, int b) {
        char tmp = charArray[a];
        charArray[a] = charArray[b];
        charArray[b] = tmp;
    }
}
