package tg.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainController {

	@FXML
	private StackPane mainStackPane;
	
	@FXML
	public void initialize() throws IOException {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/loginScreen.fxml"));
		Pane pane=null;
		try{
			 pane = loader.load();
		}catch(IOException e) {
			e.printStackTrace();
		}
		LoginController loginController = loader.getController();
		loginController.setMainController(this);
		//
		setScreen(pane);
	}

	public void setScreen(Pane pane) {
		mainStackPane.getChildren().clear();
		mainStackPane.getChildren().add(pane);
	}
	
}
