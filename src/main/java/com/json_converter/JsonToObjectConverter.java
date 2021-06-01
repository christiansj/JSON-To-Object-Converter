package com.json_converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonToObjectConverter {
	protected final String jsonString;
	protected final String className;
	protected Map<String,Object> jsonMap;
	protected ArrayList<String> objectKeys = new ArrayList<String>();
	protected final ArrayList<String> orderedKeys = new ArrayList<String>();
	
	public JsonToObjectConverter(String jsonString, String className) throws Exception {
		this.jsonString = jsonString;
		this.className = className;
		
		jsonMap = new ObjectMapper().readValue(jsonString, HashMap.class);
		setOrderedKeys();
	}
	
	private void setOrderedKeys() {
    	Matcher matcher = Pattern.compile("\"[\\da-z_]+\":").matcher(jsonString);
   	 	Matcher nextMatcher = Pattern.compile("\"[\\da-z_]+\":").matcher(jsonString);

   	 	String currentKey = getKey(matcher);
   	 	String nextKey = getKey(nextMatcher);
 
   	 	nextKey = getKey(nextMatcher);
   	 	
  	 	if(nextKey != null) {
  	 		handleObjectValue(currentKey, getValue(matcher.end(), nextMatcher.start()));
   	 	}else if(currentKey != null){
   	 		handleObjectValue(currentKey, getValue(matcher.end(), jsonString.length()-2));
   	 	}
	 	
   	 	while(currentKey != null) {
   		 	if(nextKey != null) {
   		 		handleObjectValue(currentKey, getValue(matcher.end(), nextMatcher.start()));
   	 		}else {
   	 			handleObjectValue(currentKey, getValue(matcher.end(), jsonString.length()-1));
   	 		}
   		 	
   		 	orderedKeys.add(currentKey);
   	 		currentKey = getKey(matcher);
   	 		nextKey = getKey(nextMatcher);
   	 	}
	}
	
	private String getKey(Matcher matcher) {
		while(matcher.find()) {
			String match = matcher.group();
   	 		String key = match.substring(1, match.indexOf("\"",1));
   	 		
   	 		if(!jsonMap.containsKey(key) || orderedKeys.contains(key)) {
   	 			continue;
	 		}
   	 		return key;
		}
		return null;
	}
	
	private void handleObjectValue(String key, String value) {
			try {
				char firstChar = value.trim().charAt(0);

				if(firstChar == '{') {
					new ObjectMapper().readValue(value, HashMap.class);
					objectKeys.add(key);
				}else if(firstChar == '[') {
					new ObjectMapper().readValue(value, HashMap[].class);
					objectKeys.add(key);
				}
			}catch(Exception e) {
	
			}
	}
	
	private String getValue(int startIndex, int endIndex) {
		return jsonString.substring(startIndex, endIndex);
	}
}
