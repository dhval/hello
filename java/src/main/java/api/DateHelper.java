package api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dxm6974
 */
public class DateHelper {

    private static final String DATE_FORMAT = "EEE, d MMM yyyy 'at' HH:mm:ss z";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public static void main(String[] arg) {
        System.out.println(dateFormat.format(new Date()));
    }
}
