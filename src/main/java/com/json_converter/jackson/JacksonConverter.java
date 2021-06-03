package com.json_converter.jackson;

import java.util.HashMap;

import com.json_converter.JsonToObjectConverter;
import com.json_converter.types.VariableType;
import com.json_converter.util.StringUtility;

public class JacksonConverter extends JsonToObjectConverter {
	private HashMap<VariableType, String> javaTypeMap = new HashMap<VariableType, String>();
	
	public JacksonConverter(String jsonString, String className) throws Exception {
		super(jsonString, className);
		initMap();
	}
	
	private void initMap() {
		javaTypeMap.put(VariableType.STRING, "String");
		javaTypeMap.put(VariableType.INT, "int");
		javaTypeMap.put(VariableType.DOUBLE, "double");
		javaTypeMap.put(VariableType.BOOLEAN, "boolean");
		javaTypeMap.put(VariableType.OBJECT, "Object");
		javaTypeMap.put(VariableType.ARRAY, "Object[]");
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
		VariableType variableType = keyTypeMap.get(key);
		String declaration = String.format("\tprivate %s %s;\n",
				javaTypeMap.get(variableType),
				StringUtility.formatJsonKey(key)
		);
		
		return varString + declaration;
	}
	
	
	private String jsonProperty(String key) {
		return String.format("\t@JsonProperty(\"%s\")\n", key);
	}
}
