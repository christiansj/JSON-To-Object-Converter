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
	protected final ArrayList<String> orderedKeys = new ArrayList<String>();
	
	public JsonToObjectConverter(String jsonString, String className) throws Exception {
		this.jsonString = jsonString;
		this.className = className;
		jsonMap = new ObjectMapper().readValue(jsonString, HashMap.class);
		setOrderedKeys();
	}
	
	private void setOrderedKeys() {
    	Matcher matcher = Pattern.compile("\"[\\da-z_]+\":").matcher(jsonString);
   	 	
    	while (matcher.find()) {
   	 		String match = matcher.group();
   	 		orderedKeys.add(match.substring(1, match.indexOf("\"",1)));
   	 	}
	}
}
