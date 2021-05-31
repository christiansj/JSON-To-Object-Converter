package com.json_converter.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringUtilityTest {
	@Test
	@DisplayName("uppercase should uppercase first character of String")
	public void testUppercase() {
		assertEquals("Red", StringUtility.uppercase("red"));
		assertEquals("The fox jumped over the lazy dog.", StringUtility.uppercase("the fox jumped over the lazy dog."));
	}
	
	@Test
	@DisplayName("formatJsonKey should remove underscores and camelcase String")
	public void testUnderscoreToVariableName() {
		assertEquals("color", StringUtility.formatJsonKey("Color"));
		assertEquals("myAge", StringUtility.formatJsonKey("my_age"));
	}
}
