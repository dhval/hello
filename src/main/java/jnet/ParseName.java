package jnet;

import java.util.*;
import java.util.regex.*;

/** 
  
 **/

public class ParseName {
    
    static Pattern PATTERN_R21 = Pattern.compile("^/?([^/]+)/?.*");
    static Pattern PATTERN_NAME = Pattern.compile("^\\s*([\\S]*)\\s*([\\S]*)");
    static Matcher matcher = null;
    
    // R2.1 - Extract everything before first '/', if present.
    static String extractSlash(String s) {
        matcher = PATTERN_R21.matcher(s);
        if (matcher.find())
            return matcher.group(1).trim();
        return "";    
    }
    

    // R2.8 - Strip all numbers and special characters except '&', '-' and '/'. Additionally replace multiple spaces with single space.
    static String stripInvalidCharacters(String s) {
        return s.replaceAll("[^\\dA-Za-z&-/ ]", "").replaceAll("\\s+", " ");
    }

    public static void main (String[] s) {
        
        if (s[0] == null || s[0].isEmpty())
            return;
        
        String text = s[0].trim();
        text = stripInvalidCharacters(text);
        text = extractSlash(text);
        
        String[] nameCommaSeparated = text.split(",");
        

        // R2.2 - Validate has at least one ',' separator after first character. (like L, some thing)
        if (nameCommaSeparated.length < 2)
            return;

        System.out.println("INPUT=" + nameCommaSeparated[0] + ", " + nameCommaSeparated[1]);    
        // extract last name    
        matcher = PATTERN_NAME.matcher(nameCommaSeparated[0]);
        if (matcher.find()) {
            String last = matcher.group(1);
            System.out.println("last=" + last);
        }
        
    // R2.10 - Is there a & after the comma and between two first names.
       for(String name : nameCommaSeparated[1].split("&")) {
            matcher = PATTERN_NAME.matcher(name);
            if (matcher.find()) {
                System.out.println("first=" + matcher.group(1));
                String middle = matcher.group(2);
                if (middle != null && !middle.isEmpty() && middle.length() ==1)
                    System.out.println("middle=" + middle);
            }
       }


    }
    
}