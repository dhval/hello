package api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author c-dmudawal
 */
public class RegexDate {

    public static final String[] EXAMPLE_TEST = {"2013.06.01", " 2013.16.01", "2013.06.01 ", "2013.06.01 1", "201.06.01", "201306.01r"};
    private static Pattern PATTERN_DOT_DT = Pattern.compile("^(\\d{4}\\.\\d{2}\\.\\d{2})$");
    private static final SimpleDateFormat FORMAT_DOT_DT = new SimpleDateFormat("yyyy.MM.dd");
    private static final SimpleDateFormat FORMAT_DISPLAY_DT = new SimpleDateFormat("dd MMM, yyyy");

    public static void main(String[] args) {
        for (String strTest : EXAMPLE_TEST) {
            System.out.println(strTest + " : " + prettyDtFormat(strTest));
        }
    }

    private static String prettyDtFormat(String inDate) {
        if (StringUtils.isEmpty(inDate)) {
            return null;
        }
        String textDt = inDate.trim();
        try {
            textDt = (PATTERN_DOT_DT.matcher(textDt).find())
                    ? FORMAT_DISPLAY_DT.format(FORMAT_DOT_DT.parse(textDt)) : textDt;
        } catch (ParseException pe) {
            System.out.println("ParseException for: " + inDate + pe.getMessage());
        }
        return textDt;
    }
}
