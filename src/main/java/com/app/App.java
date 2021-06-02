package com.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main( String[] args ) throws Exception {
    	launch(args);
    }
    
    @Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("views/index.fxml"));
		   
	        stage.setTitle("JSON to Java Class Converter");
	        stage.setScene(new Scene(root));
	        stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
