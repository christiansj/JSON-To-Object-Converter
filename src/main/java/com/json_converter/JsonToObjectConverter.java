package com.json_converter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.json_converter.types.VariableType;
import com.json_converter.util.StringUtility;

public abstract class JsonToObjectConverter implements ClassWriter {
	protected final String jsonString;
	protected final String className;
	
	protected LinkedHashMap<String,Object> jsonMap;
	protected HashMap<String, VariableType> keyTypeMap = new HashMap<String, VariableType>(); 
	private ArrayList<String> keys = new ArrayList<String>();
	
	public JsonToObjectConverter(String jsonString, String className) throws Exception {
		this.jsonString = jsonString;
		this.className = StringUtility.uppercase(className);
		jsonMap = new ObjectMapper().readValue(jsonString, LinkedHashMap.class);
		
		setObjectKeys();
	}
	
	public void write(String outputPath) throws Exception{
		final String FILE_STRING = conversionString();
		String fullPath = String.format("%s/%s.java", outputPath, className);
		BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath));
		
		writer.write(FILE_STRING);
		writer.close();
	}
	
	private void setObjectKeys() {
		final String JSON_KEY_REGEX = "\"[\\da-z_]+\":";
    	Matcher matcher = Pattern.compile(JSON_KEY_REGEX).matcher(jsonString);
   	 	Matcher nextMatcher = Pattern.compile(JSON_KEY_REGEX).matcher(jsonString);

   	 	String currentKey = getKey(matcher);
   	 	keys.add(currentKey);
   	 	
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
			assignKeyType(currentKey, getValue(matcher.end(), nextMatcher.start()));
	 	}else {
	 		assignKeyType(currentKey, getValue(matcher.end(), jsonString.length()-1));
	 	}
	}
	
	private void assignKeyType(String key, String value) {

			try {
				if(jsonMap.get(key) == null) {
					keyTypeMap.put(key, VariableType.OBJECT);
				}
				
				char firstChar = value.trim().charAt(0);
				if(firstChar == '{') {
					new ObjectMapper().readValue(value, HashMap.class);
					keyTypeMap.put(key, VariableType.OBJECT);
				}else if(firstChar == '[') {
					new ObjectMapper().readValue(value, Object[].class);
					keyTypeMap.put(key, VariableType.ARRAY);
				}else if(firstChar == '\"') {
					keyTypeMap.put(key, VariableType.STRING);
				}
				
				String mapValue = jsonMap.get(key).toString();
				
				if(regexMatches(mapValue, "^-{0,1}\\d+$")) {
					keyTypeMap.put(key, VariableType.INT);
				}else if(regexMatches(mapValue, "^-{0,1}\\d+\\.\\d+$")) {
					keyTypeMap.put(key, VariableType.DOUBLE);
				} else if(mapValue.equals("true") || mapValue.equals("false")) {
					keyTypeMap.put(key, VariableType.BOOLEAN);
				}
			}catch(Exception e) {
				
			}
	}
	
	private boolean regexMatches(String string, String regex) {
		return Pattern.compile(regex).matcher(string).find();
	}
	
	private String getValue(int startIndex, int endIndex) {
		return jsonString.substring(startIndex, endIndex);
	}
}
