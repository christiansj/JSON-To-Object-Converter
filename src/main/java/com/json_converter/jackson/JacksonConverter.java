package com.json_converter.jackson;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.json_converter.util.StringUtility;

public class JacksonConverter {
	private Map<String,Object> json;
	private String className;
	
	public JacksonConverter(Map<String, Object> json, String className) {
		this.json = json;
		this.className = className;
	}
	
	public void convert() {
		StringBuilder sb = new StringBuilder();
		sb.append("import com.fasterxml.jackson.annotation.JsonProperty\n\n");
		sb.append(String.format("public class %s {\n", className));
		
		for(String key : json.keySet()) {
			sb.append(variableString(key));
		}
		sb.append("}\n");
		System.out.println(sb.toString());
	}
	
	private String variableString(String key) {
		String varString = jsonProperty(key);
		String declaration = String.format("\tprivate %s %s;\n",
				variableType(key),
				StringUtility.formatJsonKey(key)
		);
		return varString + declaration;
	}
	
	private String variableType(String key) {
		String value = json.get(key).toString();
		
		Pattern numbersOnlyPattern = Pattern.compile("^\\d+$");
		Matcher intMatcher = numbersOnlyPattern.matcher(value);
		
		if(intMatcher.find()) {
			return "int";
		}
		return "String";
	}
	
	private String jsonProperty(String key) {
		return String.format("\t@JsonProperty(\"%s\")\n", key);
	}
}
