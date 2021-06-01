package com.app;

import com.json_converter.jackson.JacksonConverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main( String[] args ) throws Exception {
//    	String json = "{\r\n" + 
//    			"    \"id\": 4448809940,\r\n" + 
//    			"    \"id_str\": \"4448809940\",\r\n" + 
//    			"    \"name\": \"ayah\",\r\n" + 
//    			"    \"screen_name\": \"lovbyun\",\r\n" + 
//    			"    \"location\": \"bbh iu jjh pcy kjd dks\",\r\n" + 
//    			"    \"url\": \"https://curiouscat.me/baekhyun-l\",\r\n" + 
//    			"    \"description\": \"hi hello I love exo\",\r\n" + 
//    			"    \"translator_type\": \"none\",\r\n" + 
//    			"    \"protected\": false,\r\n" + 
//    			"    \"verified\": false,\r\n" + 
//    			"    \"followers_count\": 1142,\r\n" + 
//    			"    \"friends_count\": 125,\r\n" + 
//    			"    \"listed_count\": 20,\r\n" + 
//    			"    \"favourites_count\": 5712,\r\n" + 
//    			"    \"statuses_count\": 4011,\r\n" + 
//    			"    \"created_at\": \"Fri Dec 04 03:44:59 +0000 2015\",\r\n" + 
//    			"    \"utc_offset\": -28800,\r\n" + 
//    			"    \"time_zone\": \"Pacific Time (US & Canada)\",\r\n" + 
//    			"    \"geo_enabled\": false,\r\n" + 
//    			"    \"lang\": \"en\",\r\n" + 
//    			"    \"contributors_enabled\": false,\r\n" + 
//    			"    \"is_translator\": false,\r\n" + 
//    			"    \"profile_background_color\": \"000000\",\r\n" + 
//    			"    \"profile_background_image_url\": \"http://abs.twimg.com/images/themes/theme1/bg.png\",\r\n" + 
//    			"    \"profile_background_image_url_https\": \"https://abs.twimg.com/images/themes/theme1/bg.png\",\r\n" + 
//    			"    \"profile_background_tile\": false,\r\n" + 
//    			"    \"profile_link_color\": \"F58EA8\",\r\n" + 
//    			"    \"profile_sidebar_border_color\": \"000000\",\r\n" + 
//    			"    \"profile_sidebar_fill_color\": \"000000\",\r\n" + 
//    			"    \"profile_text_color\": \"000000\",\r\n" + 
//    			"    \"profile_use_background_image\": false,\r\n" + 
//    			"    \"profile_image_url\": \"http://pbs.twimg.com/profile_images/967130320259526656/0xZ-wxXJ_normal.jpg\",\r\n" + 
//    			"    \"profile_image_url_https\": \"https://pbs.twimg.com/profile_images/967130320259526656/0xZ-wxXJ_normal.jpg\",\r\n" + 
//    			"    \"profile_banner_url\": \"https://pbs.twimg.com/profile_banners/4448809940/1519340670\",\r\n" + 
//    			"    \"default_profile\": false,\r\n" + 
//    			"    \"default_profile_image\": false,\r\n" + 
//    			"    \"following\": null,\r\n" + 
//    			"    \"follow_request_sent\": null,\r\n" + 
//    			"    \"notifications\": null\r\n" + 
//    			"  }";
//    	
//    	JacksonConverter jacksonConverter = new JacksonConverter(json, "Person", "src/main/java/output");
//    	jacksonConverter.write();
    	launch(args);
    }
    
    @Override
	public void start(Stage stage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("views/index.fxml"));
			Scene scene = new Scene(root);
		    
	        stage.setTitle("FXML Welcome");
	        stage.setScene(scene);
	        stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
