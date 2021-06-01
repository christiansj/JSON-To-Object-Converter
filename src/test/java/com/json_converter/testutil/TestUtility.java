package com.json_converter.testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.stream.Collectors;

public class TestUtility {
	/**
	 * Compares a Java class's .java and .txt files.
	 * .txt is the expected contents while .java is the actual file written by ConversionWriter 
	 * Asserts whether both files have the same contents
	 * 
	 * @param dirPath path to directory holding both .java and .txt file
	 * @param className name of the Java class
	 * @throws Exception
	 */
	public static void evaluateJavaFileContents(String dirPath, String className) throws Exception {		
		String basePath = "src/test/java/com/json_converter/";
		
		String javaFilePath = String.format("%s%s%s.java", 
				basePath, dirPath, className);
	
		final String ACTUAL = fileToString(javaFilePath);
		final String EXPECTED = fileToString(basePath + dirPath + className + ".txt");
	
		new File(javaFilePath).delete();
		
		assertEquals(EXPECTED, ACTUAL);
	}
	
	private static String fileToString(String filePath) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String str = reader.lines().collect(Collectors.joining("\n"));
		reader.close();
		
		return str;
	}
}
