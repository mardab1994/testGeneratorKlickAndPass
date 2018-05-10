package tg.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tg.jdbc.TestDriver;
import tg.sprawdzian.Sprawdzian;
public class AnswearController {
	
	private MainController mainController;
	@FXML public Rectangle kafA = new Rectangle();
	@FXML public Rectangle kafB = new Rectangle();
	@FXML public Label actualNumber = new Label();
	
	private int actual=1;
	private int allNum=10;
	private int liczbaOdpowiedzi;
	int ButtA=-1;
	int ButtB=-1;
	
	private Sprawdzian sprawdzian = new Sprawdzian();
	private TestDriver testDriver;
	
	public void kafelekA(){
		System.out.println("klinales w kafelek A");
		ButtA*=-1;
		if(ButtA>0) {
			System.out.println("buttaA = "+ButtA);
			kafA.setStyle("-fx-fill: #62ff2d;");
			System.out.println("Zaznaczono A");
			sprawdzian.setOdpowiedz(actual, "A", 1);
		}
		else
			{
			kafA.setStyle("-fx-fill: #64b4ff;");
			System.out.println("Odznaczono A");
			sprawdzian.setOdpowiedz(actual, "A", 0);
			}
	}
	
	public void kafelekB(){
		System.out.println("klinales w kafelek B");
		ButtB*=-1;
		if(ButtB>0) {
			System.out.println("buttaB = "+ButtB);
			kafB.setStyle("-fx-fill: #62ff2d;");
			System.out.println("Zaznaczono B");
			sprawdzian.setOdpowiedz(actual, "B", 1);
		}
		else
			{
			kafB.setStyle("-fx-fill: #64b4ff;");
			System.out.println("Odznaczono B");
			sprawdzian.setOdpowiedz(actual, "B", 0);
			}
	}

	public void setTestAndDataBase(Sprawdzian spr, TestDriver TD){
		this.sprawdzian=spr;
		this.testDriver=TD;
		liczbaOdpowiedzi=sprawdzian.getLiczbaOdpowiedzi();
		odswiezOkno();
	}
	
	public void odswiezOkno() {
		actualNumber.setText(actual +"/"+sprawdzian.getLiczbaPytan());
		System.out.println("odpowiedz na pytanie nr "+actual+"  ");
		System.out.println("to A="+sprawdzian.getOdpowiedz(actual, "A"));
		System.out.println("to B="+sprawdzian.getOdpowiedz(actual, "B"));
		if(sprawdzian.getOdpowiedz(actual, "A")==0) {
			ButtA=-1;
			kafA.setStyle("-fx-fill: #64b4ff;");
		}else {
			ButtA=1;
			kafA.setStyle("-fx-fill: #62ff2d;");
		}
		if(sprawdzian.getOdpowiedz(actual, "B")==0) {
			ButtB=-1;
			kafB.setStyle("-fx-fill: #64b4ff;");
		}else {
			ButtB=1;
			kafB.setStyle("-fx-fill: #62ff2d;");
		}		
	}
	
	@FXML
	public void nextButton(){
		System.out.println("nastepne pytanie");
		actual++;
		if(actual>sprawdzian.getLiczbaPytan())actual=sprawdzian.getLiczbaPytan();
		odswiezOkno();
	}
	@FXML
	public void prewButton() {
		System.out.println("poprzednie pytanie");
		actual--;
		if(actual<1)actual=1;
		odswiezOkno();
	}
	
	//zamyka aplikacje i wysyla dane do bazy 
	@FXML
	public void EndAndSend() {
		String zapytanie="";
		String odp="";
		String pyt="pyt";
		//testDriver.dodajDaneStudenta();
		
		zapytanie="INSERT ALL INTO SPRAWDZIAN(idTestu, grupaTestu,indeksStudenta) VALUES ("+sprawdzian.getIdTestu()+",'" +sprawdzian.getGrupaTestu()+"','" +sprawdzian.getIndeksStudenta()+"')" +"SELECT * FROM dual";
		testDriver.sendQuery(zapytanie);
		
		for(int i=1; i <= sprawdzian.getLiczbaPytan(); i++){
			 zapytanie="";
			 odp="";
			 pyt="pyt"+Integer.toString(i);
			 odp=sprawdzian.getFinalAnswear(i-1);
			 System.out.println("Odpowiedz na pytanie nr="+(i-1)+"  to "+odp);
			//WPISUJE DANE TAM GDZIE JEST INDEKS TEGO STUDENTA I TAM GDZIE JEST AKTUALNIE PISANA GRUPA TESTU
			 zapytanie="UPDATE SPRAWDZIAN SET "+pyt+"='"+odp+"' WHERE indeksStudenta = '"+sprawdzian.getIndeksStudenta()+"'"+" AND grupaTestu='"+sprawdzian.getGrupaTestu()+"' AND idTestu='"+sprawdzian.getIdTestu()+"'";
			testDriver.sendQuery(zapytanie);
		}//*/
		testDriver.sendQuery("COMMIT");//*/
		testDriver.closeConnection();
		Platform.exit();//zamyka aplikacjê 
	}
	
public void setMainController(LoginController loginController) {
		this.mainController=mainController;	
	}
}


