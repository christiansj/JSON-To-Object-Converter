package com.json_converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonToObjectConverter {
	protected final String jsonString;
	protected final String className;
	
	protected LinkedHashMap<String,Object> jsonMap;
	private ArrayList<String> keys = new ArrayList<String>();
	protected ArrayList<String> objectKeys = new ArrayList<String>();
	
	public JsonToObjectConverter(String jsonString, String className) throws Exception {
		this.jsonString = jsonString;
		this.className = className;
		
		jsonMap = new ObjectMapper().readValue(jsonString, LinkedHashMap.class);
		setObjectKeys();
	}
	
	private void setObjectKeys() {
    	Matcher matcher = Pattern.compile("\"[\\da-z_]+\":").matcher(jsonString);
   	 	Matcher nextMatcher = Pattern.compile("\"[\\da-z_]+\":").matcher(jsonString);

   	 	String currentKey = getKey(matcher);
   	 	String nextKey = getKey(nextMatcher);
 
   	 	nextKey = getKey(nextMatcher);
   	 	handleObjectValue(currentKey, nextKey, matcher, nextMatcher);
	 	
   	 	while(currentKey != null) {
   		 	handleObjectValue(currentKey, nextKey, matcher, nextMatcher);
   	 		currentKey = getKey(matcher);
   	 		nextKey = getKey(nextMatcher);
   	 		
   	 		keys.add(currentKey);
   	 	}
	}
	
	private String getKey(Matcher matcher) {
		while(matcher.find()) {
			String match = matcher.group();
   	 		String key = match.substring(1, match.indexOf("\"",1));
   	 		
   	 		if(!jsonMap.containsKey(key) || keys.contains(key)) {
   	 			continue;
	 		}
   	 		return key;
		}
		return null;
	}
	
	private void handleObjectValue(String currentKey, String nextKey, Matcher matcher, Matcher nextMatcher) {
		if(nextKey != null) {
			if(matcher.end() > nextMatcher.start()) {
				nextMatcher.find();
			}
			addObjectKey(currentKey, getValue(matcher.end(), nextMatcher.start()));
	 	}else {
	 		addObjectKey(currentKey, getValue(matcher.end(), jsonString.length()-1));
	 	}
	}
	
	private void addObjectKey(String key, String value) {
			try {
				char firstChar = value.trim().charAt(0);

				if(firstChar == '{') {
					new ObjectMapper().readValue(value, HashMap.class);
					objectKeys.add(key);
				}else if(firstChar == '[') {

					new ObjectMapper().readValue(value, Object[].class);
					objectKeys.add(key);
				}
			}catch(Exception e) {
	
			}
	}
	
	private String getValue(int startIndex, int endIndex) {
		return jsonString.substring(startIndex, endIndex);
	}
}
