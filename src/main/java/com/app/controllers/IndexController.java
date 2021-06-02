package com.app.controllers;

import com.app.models.ObjectClass;
import com.json_converter.jackson.JacksonConverter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class IndexController {

    @FXML
    private TextArea json;

    @FXML
    private TextField className;
    
    @FXML
    private Button convertButton;
    
    @FXML
    private Label invalidJsonError;
    
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
    void handleConvertClick(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./../views/class-preview.fxml"));
        String classContents = null;
        try {
        	 classContents = new JacksonConverter(json.getText(), className.getText()).conversionString();
        }catch(Exception e) {
        	invalidJsonError.setVisible(true);
        	convertButton.setDisable(true);
        	e.printStackTrace();
        	return;
        }
        
        invalidJsonError.setVisible(false);
        ObjectClass objectClass = new ObjectClass(className.getText(), classContents);
        
        fxmlLoader.setController(new PreviewController(objectClass));
     
        Stage stage = new Stage();
        stage.setTitle("Object Preview");
        stage.setScene(new Scene((Pane) fxmlLoader.load()));
        stage.show();
    }    
}
