package api.regex;

import java.util.regex.*;
import java.util.*;

public class Parser {

  static String INPUT = "ORI/MD1230000 NAM/SMITH, STEVE SEX/M RAC/W POB/MD DOB/19530531 ORI IS HARPERS FERRY PD MD 301 555-1212 DOB/19060405 DOB/19520101 DLU/20080110 0100 EST NO NCIC RECORD VIN/2ADF36BLIG162312";
  static String NULL_MARKER = "XXX/";
  static Pattern DELIMITER = Pattern.compile("\\s*([A-Z]{3})/(.*?)(?=[A-Z]{3}/)");

  public static List<String> findElements(String input) {
     List<String> group = new ArrayList<String>();
     if (input == null || input.isEmpty())
      return group;
    return group;
  }


  public static void main(String[] args) {

    Map<String, String> group1 = new HashMap<String, String>();
    Map<String, List<String>> group2 = new HashMap<String, List<String>>();

    String str1 = null;
    String str2 = "";
    String ORI_Delimiters = "\\s*ORI\\s+IS\\s+";
    INPUT = INPUT.replaceAll("\\r\\n", " ");
    // analyzing the string, check 'ORI IS' present
    String[] tokensVal = INPUT.split(ORI_Delimiters);
    if (tokensVal.length >= 2) {
      str1 = tokensVal[0];
      for (int i = 1; i < tokensVal.length; i++) {
        str2 = str2.concat(tokensVal[i]);
      }
    }
    else {
      str1 = INPUT;
      str2 = null;
    }

    System.out.println("STR1 = " + str1);
    System.out.println("STR2 = " + str2);

    Matcher matcher = DELIMITER.matcher(str1.concat(NULL_MARKER));
    while (matcher.find() &&  matcher.groupCount() == 2) {
      System.out.println("key#" +matcher.group(1) + " val#" +matcher.group(2));
      group1.put(matcher.group(1), matcher.group(2));
    }
    // if group has supplementary data
    if (str2 != null) {
    matcher = DELIMITER.matcher(str2.concat(NULL_MARKER));
    while (matcher.find() &&  matcher.groupCount() == 2) {
      String key = matcher.group(1);
      String val = matcher.group(2);
      System.out.println("key#" +matcher.group(1) + " val#" +matcher.group(2));
      if (group2.containsKey(key)) {
         List<String> list = group2.get(key);
         list.add(val);
         group2.put(key, list);
      }
      else {
        List<String> list = new ArrayList<String>();
        list.add(val);
        group2.put(key, list);
      }
    }
    }


  }

}