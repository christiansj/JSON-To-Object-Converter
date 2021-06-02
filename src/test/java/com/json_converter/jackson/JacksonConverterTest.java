package com.json_converter.jackson;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.json_converter.testutil.TestUtility;

public class JacksonConverterTest {
	@Test
	@DisplayName("write() should convert JSON to Jackson Java class and write it to a file")
	public void testWrite() throws Exception{
		String json = "{\"id\": 7, \"first_name\": \"Bill\", \"last_name\": \"Board\", \"is_cool\": true, \"is_tall\": false, \"school\": null, \"gpa\":3.5, \"courses\": [{\"name\": \"Algebra\"}], \"transcript\": {\"name\": \"Bill Board\"}}";
		JacksonConverter jacksonConverter = 
				
				new JacksonConverter(json, "Student");
		jacksonConverter.write("src/test/java/com/json_converter/jackson/test_files");
		TestUtility.evaluateJavaFileContents("jackson/test_files/", "Student");
	}
}
