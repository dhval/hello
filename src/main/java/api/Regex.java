package api;

import java.util.regex.*;

public class Regex {
	
	String NON_CAPTURING_GROUP = "my (?:cat|dog) pooped"; // [...]  is a character set

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

	public static void main(String[] args) {
		if (args.length==2) {
			Pattern p = Pattern.compile(args[0]);
			checkRegex(p, args[1]);
		} else
		 checkRegex(pattern, args[0]);
	}

}
