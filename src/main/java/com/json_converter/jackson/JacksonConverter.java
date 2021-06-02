package com.json_converter.jackson;

import java.util.regex.Pattern;

import com.json_converter.JsonToObjectConverter;
import com.json_converter.util.StringUtility;

public class JacksonConverter extends JsonToObjectConverter {
	public JacksonConverter(String jsonString, String className) throws Exception {
		super(jsonString, className);
	}
	
	public String conversionString() {
		StringBuilder sb = new StringBuilder();
		sb.append("import com.fasterxml.jackson.annotation.JsonProperty;\n");
		sb.append("import com.fasterxml.jackson.annotation.JsonIgnoreProperties;\n\n");
		sb.append("@JsonIgnoreProperties(ignoreUnknown = true)\n");
		sb.append(String.format("public class %s {\n", className));
		
		for(String key : jsonMap.keySet()) {
			sb.append(variableString(key));
		}
		sb.append("}\n");
		
		return sb.toString();
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
		if(jsonMap.get(key) == null) {
			return "Object";
		}else if(objectKeys.contains(key)) {
			return jsonMap.get(key).toString().charAt(0) == '[' ? "Object[]" : "Object";
		}
		String value = jsonMap.get(key).toString();
		
		if(stringKeys.contains(key)) {
			return "String";
		}else if(regexMatches(value, "^-{0,1}\\d+$")) {
			return "int";
		} else if(regexMatches(value, "^-{0,1}\\d+\\.\\d+$")) {
			return "double";
		} else if(value.equals("true") || value.equals("false")) {
			return "boolean";
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
