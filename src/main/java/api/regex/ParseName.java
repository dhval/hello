package api.regex;

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
    
    // R2.9 - Strip all hyphens after the first hyphen.
    static String replaceHyphen(String s) {
        int index = s.indexOf("-");
        System.out.println(index);
        s = s.replaceAll("[-]+", "");
        System.out.println(s);
        if (index > 0) {
            s = s.substring(0, index) + "-" + s.substring(index, s.length());
        }
        return s;
    }
    
    static class Name {
        String first;
        String last;
        String middle;
        Name(String last) {
            this.last = last;
        }
        public String toString() {
            return "first=" + first + ", last=" + last + ", middle=" + middle; 
        }
    }

    public static List<Name> parse (String s) {
        
        List<Name> names = new ArrayList<>();
        String lastName = null;
        
        if (s == null || s.isEmpty())
            return names;
        
        String text = s.trim();
        text = stripInvalidCharacters(text);
        text = replaceHyphen(text);
        text = extractSlash(text);
        
        String[] nameCommaSeparated = text.split(",");
        

        // R2.2 - Validate has at least one ',' separator after first character. (like L, some thing)
        if (nameCommaSeparated.length < 2)
            return names;

        System.out.println("INPUT=" + nameCommaSeparated[0] + ", " + nameCommaSeparated[1]);    
        // extract last name    
        matcher = PATTERN_NAME.matcher(nameCommaSeparated[0]);
        if (matcher.find()) {
            lastName = matcher.group(1).replaceAll("&", "");
        }
        
    // R2.10 - Is there a & after the comma and between two first names.
       for(String str : nameCommaSeparated[1].split("&")) {
            matcher = PATTERN_NAME.matcher(str);
            Name name = new Name(lastName);
            if (matcher.find()) {
                name.first = matcher.group(1);
                String middle = matcher.group(2);
                if (middle != null && !middle.isEmpty() && middle.length() ==1)
                    name.middle = middle;
                names.add(name);    
            }
       }

        return names;
    }
    
    public static void main (String[] s) {
        if (s.length !=1)
            return;
       List<Name> names =  parse(s[0]);
       System.out.println(names);
    }
    
}