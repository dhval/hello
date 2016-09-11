package api;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParseDt {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	public static void main(String[] a) throws ParseException {
		System.out.println(dateFormat.parseObject("20100404040000"));
	}
}
