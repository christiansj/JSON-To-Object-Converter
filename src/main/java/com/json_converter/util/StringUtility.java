package com.json_converter.util;

public class StringUtility {
	public static String uppercase(String s) {
		if(s.length() == 1) {
			return s.toUpperCase();
		}
		return s.substring(0,1).toUpperCase() + s.substring(1, s.length());
	}
	
	public static String formatJsonKey(String s) {
		String[] TOKENS = s.split("_");
		String newString = TOKENS[0].toLowerCase();
		
		for(int i = 1; i < TOKENS.length; i++) {
			newString += StringUtility.uppercase(TOKENS[i]);
		}
		return newString;
	}
}
