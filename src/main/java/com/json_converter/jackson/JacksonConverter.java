package com.json_converter.jackson;

import java.util.regex.Pattern;

import com.json_converter.JsonToObjectConverter;
import com.json_converter.util.StringUtility;

public class JacksonConverter extends JsonToObjectConverter {
	public JacksonConverter(String jsonString, String className) throws Exception {
		super(jsonString, className);
	}
	
	public void convert() {
		StringBuilder sb = new StringBuilder();
		sb.append("import com.fasterxml.jackson.annotation.JsonProperty\n\n");
		sb.append(String.format("public class %s {\n", className));
		
		for(String key : orderedKeys) {
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
		String value = jsonMap.get(key).toString();
		
		if(regexMatches(value, "^-{0,1}\\d+$")) {
			return "int";
		} else if(regexMatches(value, "^-{0,1}\\d+\\.\\d+$")) {
			return "double";
		}
	
		return "String";
	}
	
	private boolean regexMatches(String string, String regex) {
		return Pattern.compile(regex).matcher(string).find();
	}
	
	private String jsonProperty(String key) {
		return String.format("\t@JsonProperty(\"%s\")\n", key);
	}
}
