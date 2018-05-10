package com.gluonapplication;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GluonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	
    	Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
//        Scene scene = new Scene(root, visualBounds.getWidth(), visualBounds.getHeight());
    	
    	FXMLLoader loader=new FXMLLoader();
    	loader.setLocation(this.getClass().getResource("/fxml/MainScreen.fxml"));
        StackPane stackPane = loader.load();

        Scene scene = new Scene(stackPane, 380, 600);
        primaryStage.setScene(scene);

       
        primaryStage.show();
    }
}