package api.date;

import java.util.*;

public class MyDate {
    static Calendar c = GregorianCalendar.getInstance(Locale.US);
    
    static Date firstDayOfYear() {
         c.set(Calendar.MONTH,0);
        c.set(Calendar.DAY_OF_MONTH,1);
    
 //       c.clear(Calendar.MONTH);
 //       c.clear(Calendar.DAY_OF_MONTH);
        return c.getTime();
    }

    public static void main(String[] s) {
        System.out.println(" -- " + firstDayOfYear());

        // search until wednesday
        while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            c.add(Calendar.DATE, -1);
        }
        
        Date dt = c.getTime();

        System.out.println(" -- " +dt);
        System.out.println(" -- " + c.getFirstDayOfWeek());
        
        int[] A = {2,3,4};
        System.out.println(Arrays.toString(A));
    }
}