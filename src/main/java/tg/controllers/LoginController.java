package tg.controllers;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
//import tg.dataBase.oracleJBDC;
import tg.jdbc.TestDriver;
import tg.sprawdzian.Sprawdzian;

public class LoginController {
	
	private MainController mainController;
//wstrzykniêcie textfieldow  
	@FXML public TextField indeksInput = new TextField();
	@FXML public TextField idTestInput = new TextField();
	@FXML public TextField grupaInput = new TextField();
	@FXML public Label ErrorLabel=new Label("Brak bledow");
	
	
	public TestDriver testDriver = new TestDriver();
	private Sprawdzian sprawdzian=new Sprawdzian();
	
	@FXML
	public void login2app() {
	
		System.out.println("Logowanie do aplikacji...");
		String indeks=indeksInput.getText();
		String idTestu=idTestInput.getText();
		String grupaTestu=grupaInput.getText();
		
		System.out.println("indeks: "+indeks+"  idTestu: "+idTestu +"  grupa Testu "+grupaTestu);
		
		//-------------------------------BAZA DANYCH--------------------------
		
		testDriver.pobierzDane_o_tescie(idTestu);
		ErrorLabel.setText(testDriver.getBlad());
		
		System.out.println(testDriver.getLiczbaPytan());
		sprawdzian.setParam(Integer.parseInt(idTestu), grupaTestu, indeks,testDriver.getLiczbaPytan(), testDriver.getliczbaKolumnNaPytania(), testDriver.getLiczbaOdpowiedzi());

		//sprawdzian.setParam(1, grupaTestu, indeks,10, 10, 4);

		sprawdzian.wypisz();
		//-----------------------------KONIEC BAZY DANYCH ---------------------------
		
		//inicjalizacja nowego okna
		if(sprawdzian.getLiczbaOdpowiedzi()==2) {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/answearScreen.fxml"));
			Pane pane=null;
			try{
				pane=loader.load();
			}catch(IOException e) {
				e.printStackTrace();
			}
			AnswearController answearController = loader.getController();
			answearController.setTestAndDataBase(sprawdzian, testDriver);
			answearController.setMainController(this);
			mainController.setScreen(pane);//przekazanie nowego okna do mainControllera
		}else if(sprawdzian.getLiczbaOdpowiedzi()==3) {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/answearScreen3.fxml"));
			Pane pane=null;
			try{
				pane=loader.load();
			}catch(IOException e) {
				e.printStackTrace();
			}
			AnswearController3 answearController3 = loader.getController();
			answearController3.setTestAndDataBase(sprawdzian, testDriver);
			answearController3.setMainController(this);
			mainController.setScreen(pane);//przekazanie nowego okna do mainControllera
		}else if(sprawdzian.getLiczbaOdpowiedzi()==4) {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/answearScreen4.fxml"));
			Pane pane=null;
			try{
				pane=loader.load();
			}catch(IOException e) {
				e.printStackTrace();
			}
			AnswearController4 answearController4 = loader.getController();
			answearController4.setTestAndDataBase(sprawdzian, testDriver);
			answearController4.setMainController(this);
			mainController.setScreen(pane);//przekazanie nowego okna do mainControllera
		}else if(sprawdzian.getLiczbaOdpowiedzi()==5) {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/answearScreen5.fxml"));
			Pane pane=null;
			try{
				pane=loader.load();
			}catch(IOException e) {
				e.printStackTrace();
			}
			AnswearController5 answearController5 = loader.getController();
			answearController5.setTestAndDataBase(sprawdzian, testDriver);
			answearController5.setMainController(this);
			mainController.setScreen(pane);//przekazanie nowego okna do mainControllera
		}
		
		
		
	}
	public void setMainController(MainController mainController) {
		indeksInput.setStyle("-fx-text-fill: #00bfff; -fx-border-color: #648FA7; -fx-background-color: #1D2030;");
		idTestInput.setStyle("-fx-text-fill: #00bfff; -fx-border-color: #13BEEB; -fx-background-color: #1D2030;");
		 grupaInput.setStyle("-fx-text-fill: #00bfff; -fx-border-color: #9A0CE8; -fx-background-color: #1D2030;");
		this.mainController=mainController;
	}

}
