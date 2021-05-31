package com.json_converter;

import com.json_converter.jackson.JacksonConverter;

public class App {
    public static void main( String[] args ) throws Exception {
    	String json = "{\"id\": 1, \"first_name\": \"Johnny\", \"age\": 20, \"gpa\": 3.6, \"rank\": 1}";

    	JacksonConverter jacksonConverter = new JacksonConverter(json, "Person");
    	jacksonConverter.convert();
    }
}
