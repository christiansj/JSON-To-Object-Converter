package com.json_converter;

import com.json_converter.jackson.JacksonConverter;

public class App {
    public static void main( String[] args ) throws Exception {
    	String json = "{\"id\": 10, \"name\": \"Bob\", \"pet\": {\"name\": \"dog\"}, \"hobbies\": [{\"name\": \"Soccer\"}, {\"name\": \"Piano\"}], \"is_cool\": true, \"pet\": {\"name\": \"Max\", \"is_cool\": true}, \"age\": 11, \"fish\": {\"name\": \"Fin\"}, \"rating\": 9.5, \"colors\": [{\"name\": \"blue\"}]}";
    	
    	JacksonConverter jacksonConverter = new JacksonConverter(json, "Person");
    	jacksonConverter.convert();

    }
}
