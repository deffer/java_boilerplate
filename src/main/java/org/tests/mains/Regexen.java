package org.tests;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: Irina Benediktovich - http://plus.google.com/+IrinaBenediktovich
 */
public class Regexen {
	static final Pattern PUPPY_MATCHER = Pattern.compile("puppy\\s+(\\w{1,})", Pattern.DOTALL);
	public static void main(String[] args) {
		if (args.length<1 || args[0].trim().length()==0){
			System.err.println("Expected at least 1 parameter: non-empty string to test against predefined patterns, Try 'puppy spot, puppy spock puppy A'");
			System.exit(0);
		}

		String against = StringUtils.join(args, ' ');

		Matcher m = PUPPY_MATCHER.matcher(against);
		if (m.matches()){
			System.out.print("Total match!");
		} else{
			m.reset();
			while (m.find()){
				System.out.println("Found: puppy "+m.group(1));
			}
		}
	}
}
