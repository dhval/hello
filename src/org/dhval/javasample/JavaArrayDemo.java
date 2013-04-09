package org.dhval.javasample;

import java.util.Arrays;
import org.apache.commons.lang.StringUtils;

public class JavaArrayDemo {
    static char[] chArray = {'0','1','2','3','4','5','6','7','8','9'};
    
    public static void main (String[] a) {
        JavaArrayDemo demo = new JavaArrayDemo();
//        demo.arrayCopy();
        System.out.println(StringUtils.isNumeric(""));
        int e = Integer.parseInt("");
        System.out.println(e);
    }

    void arrayCopy() {
        int start = 2;
        int numToCopy = 5;
        char[] chTempArray = {'a','b','c','d','e','f','g','h','i','j'};
        chTempArray = new char[numToCopy];
        System.arraycopy(chArray, start, chTempArray, 0, numToCopy);
        System.out.println(Arrays.toString(chTempArray));
    }

}
