package api;

import java.util.regex.*;

public class Regex {

	static String NON_CAPTURING_GROUP = "my (?:cat|dog) pooped"; // [...]  is a character set

	static Pattern patternOrg3 = Pattern.compile("^cn=((business partners|county|federal|state)\\s*(Group\\s*[\\d]+))\\s*?,*?");
	static Pattern patternUser = Pattern.compile("^cn=([^,]*),*");
	static Pattern PATTERN_DOT_DT = Pattern.compile("^(\\d{4}\\.\\d{2}\\.\\d{2})$");
	static Pattern patternG20 = Pattern.compile("^cn=((business partners|county|federal|state)-(\\w*)),ou=*");

	static Pattern pattern = Pattern.compile("^t[r|s]p");

	public static void checkRegex(Pattern pattern, String input) {
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			System.out.println("Match found ... counts#" + matcher.groupCount() + " --- " + matcher.group(0));
			for (int i = 1; i <= matcher.groupCount(); i++)
				System.out.println("Group " + i + " --- " + matcher.group(i));
		}
		else
		System.out.println("... No Match ...");
	}

	static String toDate(String input) {
		if (input == null || input.isEmpty())
			 return "";
		return input.replace("-", "\\");
	}

	static String parse(String input) {
		if (input == null || input.isEmpty())
			 return "";
			System.out.println(input);
			Pattern pattern = Pattern.compile("(.*?)(BACK|CHEST|HEAD|LEFT ARM|LEFT HAND)(.*)");
			Matcher matcher = pattern.matcher(input);
			if (matcher.find() && matcher.groupCount()==3) {
					System.out.println("Match found ... counts#" + matcher.groupCount() + " --- " + matcher.group(0));
				 return matcher.group(1) + "," + matcher.group(2) + "," + matcher.group(3);
			}
			//unable to parse
			return input.concat(" d,,");
	}

	public static void main(String[] args) {
		if (args.length==2) {
			Pattern p = Pattern.compile(args[0]);
			checkRegex(p, args[1]);
		} else
		 // checkRegex(pattern, args[0]);
		 System.out.println(toDate(args[0]));
	}

	/**
	 * Simple look ahead for any known key-word as delimiter, .* -> to match any string.
	 **/
	static String LOOK_AHEAD =  "(.*?)(BACK|CHEST|HEAD|LEFT ARM|LEFT HAND)(.*)";
	static String NON_CAPTURING_GROUP = "my (?:cat|dog) pooped"; // [...]  is a character set
	/**
	 * Since \\1 represents part matched by group 1 th
	 **/
    static String CONSECUTIVE_NUM = "(\\d)\\1";

	static Pattern patternOrg3 = Pattern.compile("^cn=((business partners|county|federal|state)\\s*(Group\\s*[\\d]+)).*");
	static Pattern patternUser = Pattern.compile("^cn=([^,]*),*");
	static Pattern PATTERN_DOT_DT = Pattern.compile("^(\\d{4}\\.\\d{2}\\.\\d{2})$");

	static Pattern TEST_PATTERN = Pattern.compile("^t[r|s]p");

	public static void checkRegex(Pattern pattern, String input) {
		Matcher matcher = pattern.matcher(input);
		boolean isMatch = matcher.find();
		do {
			if (!isMatch) { 
				System.out.println("... No Match ...");		
			} else {
				System.out.println("Match found ... counts#" + matcher.groupCount() + " --- " + matcher.group(0));
				for (int i = 1; i <= matcher.groupCount(); i++)
					System.out.println("Group " + i + " --- " + matcher.group(i));
			}
		} while(isMatch = matcher.find());
	}

	public static void main(String[] args) {
		if (args.length==2) {
			Pattern p = Pattern.compile(args[0]);
			checkRegex(p, args[1]);
		} else
		 checkRegex(TEST_PATTERN, args[0]);
		 // System.out.println(toDate(args[0]));
	}

}