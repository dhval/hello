package api;

import java.util.regex.*;

public class Regex {

	static String subjectString = "cn=AppConfig,cn=UserApplication,cn=idm361," +
			"ou=services,o=jnet#0#<assignment><start_tm>20091230162551Z</start_tm>";
	
	
	public static void main(String[] a) {
		System.out.println("start ... ");
		
		subjectString = "cn=criminal-history-users,cn=Application Access,cn=Level20," +
				"cn=RoleDefs,cn=RoleConfig,cn=AppConfig,cn=UserApplication,cn=idm361," +
				"ou=services,o=jnet#0#<assignment><start_tm>20080404040000Z</start_tm>" +
				"<end_tm>20100404040000Z</end_tm><req>cn=userappadmin," +
				"ou=ServiceAccounts,o=jnet</req><req_desc>Request generated " +
				"by policyDriver DN: \\JNET-DEV-IDV\\jnet\\services\\idm361\\JTAC2IDV-2" +
				"Location: vnd.nds.stream://JNET-DEV-IDV/jnet/services/idm361/JTAC2IDV" +
				"-2/Publisher/PubCmdXform-Roles#XmlData:87</req_desc><approval>" +
				"<start_tm>20100112203529Z</start_tm><process_id>" +
				"b07bf53e3a3645f8a41a17799d1b3ea7</process_id><end_tm>20100112203530Z" +
				"</end_tm></approval></assignment>";
		
//		subjectString = "cn=criminal-history-users,cn=Application Access,cn=Level20," +
//		"cn=RoleDefs,cn=RoleConfig,cn=AppConfig,cn=UserApplication,cn=idm361," +
//		"ou=services,o=jnet#0#<assignment><start_tm>20080404040000Z</start_tm>" +
//		"<end_tm>20100404040000Z</end_tm><req>cn=userappadmin," +
//		"ou=ServiceAccounts,o=jnet</req><req_desc>Request generated " +
//		"by policyDriver DN: \\JNET-DEV-IDV\\jnet\\services\\idm361\\JTAC2IDV-2" +
//		"Location: vnd.nds.stream://JNET-DEV-IDV/jnet/services/idm361/JTAC2IDV" +
//		"-2/Publisher/PubCmdXform-Roles#XmlData:87</req_desc></assignment>";
		
		subjectString = "cn=criminal-history-users,cn=Application Access,cn=Level20," +
		"cn=RoleDefs,cn=RoleConfig,cn=AppConfig,cn=UserApplication,cn=idm361," +
		"ou=services,o=jnet#0#<assignment><start_tm>20080404040000Z</start_tm>" +
		"<end_tm>20100404040000Z</end_tm><req>cn=userappadmin," +
		"ou=ServiceAccounts,o=jnet</req><req_desc>Request generated " +
		"by policyDriver DN: \\JNET-DEV-IDV\\jnet\\services\\idm361\\JTAC2IDV-2" +
		"Location: vnd.nds.stream://JNET-DEV-IDV/jnet/services/idm361/JTAC2IDV" +
		"-2/Publisher/PubCmdXform-Roles#XmlData:87</req_desc><approval>" +
		"<start_tm>20100112203529Z</start_tm><process_id>" +
		"b07bf53e3a3645f8a41a17799d1b3ea7</process_id><end_tm>20100112203530Z" +
		"</end_tm></approval></assignment>";
		
		Pattern regex = Pattern.compile("<assignment><start_tm>.*?</start_tm><end_tm>(.*?)</end_tm>", Pattern.DOTALL);
		Matcher matcher = regex.matcher(subjectString);
		Pattern regex2 = Pattern.compile("<([^<>]+)>([^<>]+)</\\1>");
		while (matcher.find()) {
			String DataElements = matcher.group(1);
			System.out.println("Match found ... " + matcher.groupCount() + " --- " + matcher.group(1));
			Matcher matcher2 = regex2.matcher(DataElements);
			while (matcher2.find()) {
				System.out.println(matcher2.group(1) + "  " + matcher2.group(2));
			} 
		}

		Pattern patternJnetURL = Pattern.compile("^(https://|http://)([A-Za-z0-9-.]+)*[:|/]*([\\d]{4})*([\\S]+)");
		String testUrl = "http://172.19.10.199:7001/warrantcorrection/pages/correction.jsf";

		Matcher urlMatcher = patternJnetURL.matcher(testUrl);
		if (urlMatcher.find()) {
				for(int i=0; i <= urlMatcher.groupCount(); i++) {
					System.out.println(urlMatcher.group(i));
			}
		} else {
			System.out.println("NO Match !");
		}
		
		Pattern PALP4 = Pattern.compile("\\w*");
		Matcher MAlP4 = PALP4.matcher(" H HH L");
		while (MAlP4.find()) {
			System.out.println(MAlP4.group(0));
		} 
		System.out.println("--END--" + " dd dd we T jj ()#4 dsfs".replaceAll("\\W", "") + "--");
	}
}
