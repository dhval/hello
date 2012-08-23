package sample

import java.util.regex.Pattern

class RegularETest {

    final static String LXK_MODEL_PATTERN = '^(.*\\d+)\\D+$';

    static main(args) {
	println " ${Pattern.compile(LXK_MODEL_PATTERN).matcher('Xf sdhh').matches()} ";
    }
}
