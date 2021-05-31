package com.json_converter;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.json_converter.jackson.JacksonConverter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	String json = "{\"id\": [], \"first_name\": \"Johnny\", \"age\": 20}";
    	Map<String,Object> jsonMap =
    	        new ObjectMapper().readValue(json, HashMap.class);
    	
    	JacksonConverter jacksonConverter = new JacksonConverter(jsonMap, "Person");
    	jacksonConverter.convert();
    }
}
