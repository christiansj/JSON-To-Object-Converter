package com.json_converter;

import com.json_converter.jackson.JacksonConverter;

public class App {
    public static void main( String[] args ) throws Exception {
    	String json = "{\"pet\": {\"name\": \"dog\"}, \"hobbies\": [{\"name\": \"Soccer\"}, {\"name\": \"Piano\"}], \"is_cool\": true}";
    	
    	JacksonConverter jacksonConverter = new JacksonConverter(json, "Person");
    	jacksonConverter.convert();

    }
}
