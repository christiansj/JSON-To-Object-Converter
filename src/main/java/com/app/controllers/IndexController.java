package com.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class IndexController {

    @FXML
    private TextArea json;

    @FXML
    private TextField className;
    
    @FXML
    private Button convertButton;
    
    @FXML
    void handleClassNameInput(KeyEvent event) {
    	toggleConvertButton();
    }
    
    @FXML
    void handleJsonInput(KeyEvent event) {
    	toggleConvertButton();
    }
    
    @FXML
    private void toggleConvertButton() {
    	boolean disable = !(!isEmpty(json.getText()) && !isEmpty(className.getText()));
    	convertButton.setDisable(disable);
    }
    
    private boolean isEmpty(String s) {
    	return s.trim().isEmpty();
    }
    
    @FXML
    void handleConvertClick(ActionEvent event) {
    	
    }
    
}