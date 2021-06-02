package com.app.controllers;

import com.app.models.ObjectClass;
import com.json_converter.util.StringUtility;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PreviewController {
	private ObjectClass objectClass;
	
	@FXML
    private Label heading;
	
	@FXML
    private TextArea contents;
	
	
	public PreviewController(ObjectClass objectClass) {
		this.objectClass = objectClass;
		
	}
	
	@FXML
	public void initialize() {
		heading.setText(StringUtility.uppercase(objectClass.getClassName()) + ".java");
		contents.setText(objectClass.getContents());	
	}
}
