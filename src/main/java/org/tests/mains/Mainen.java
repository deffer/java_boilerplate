package org.tests.mains;

import org.apache.commons.lang.StringUtils;


/**
 * author: Irina Benediktovich - http://plus.google.com/+IrinaBenediktovich
 */
public class Mainen {
	public static void main(String[] args) {
		System.out.println("Args are " + StringUtils.join(args, ',')+". Zero arg is "+args[0]);
		for (String arg: args){
			System.out.println(arg);
		}
	}
}
