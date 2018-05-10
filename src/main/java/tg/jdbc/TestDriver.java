package tg.jdbc;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tg.sprawdzian.Sprawdzian;

public class TestDriver {
	
	private Statement stmt = null;
	private ResultSet rs = null;

	private int liczbaPytan;
	private int liczbaKolumnNaPytania;
	private int liczbaOdpowiedzi;
	private Connection con=null;
	private String blad="Brak bledow";
	
	public void pobierzDane_o_tescie(String idTestu) {
		String zapytanie = "";
		try {
			// Method method = URLClassLoader.class.getDeclaredMethod("/ojdbc7.jar", new Class[]{URL.class});

			
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			//Connection 
			//con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "hr", "mikulec32");
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			con = DriverManager.getConnection("jdbc:oracle:thin:@Marcin:1522:xe", "hr", "mikulec32");
			stmt = con.createStatement();
			/*zamyka aplikacje
			 * 		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.3:1522:xe", "hr", "mikulec32");
			stmt = con.createStatement();
			 * */

//--------- --------------POBRANIE LICZBY PYTAN ------------------------------------
			zapytanie = "SELECT liczbaPytan FROM INFO_TESTU WHERE id=" + idTestu;
			rs = stmt.executeQuery(zapytanie);
			while (rs.next()) {
				liczbaPytan = Integer.parseInt(rs.getString("liczbaPytan"));
				System.out.println("liczba pytan w tescie to " + liczbaPytan);
			}
//---- ---------------POBRANIE LICZBY MOZLIWYCH ODPOWIEDZI ---------------
			zapytanie = "SELECT liczbaOdpowiedzi FROM INFO_TESTU WHERE id=" + idTestu;
			rs = stmt.executeQuery(zapytanie);
			while (rs.next()) {
				liczbaOdpowiedzi = Integer.parseInt(rs.getString("liczbaOdpowiedzi"));
				System.out.println("liczba odpowiedzi w pytaniu to " + liczbaOdpowiedzi);
			}
// ---------------POBRANIE LICZBY KOLUMN NA ODPOWIEDZI I JESLI TO KONIECZNE TO UTWORZENIE BRAKUJACYCH ---------------
			zapytanie = "SELECT Count(*) AS totalCol FROM all_tab_columns WHERE TABLE_NAME= 'SPRAWDZIAN'";
			rs = stmt.executeQuery(zapytanie);
			while (rs.next()) {
				liczbaKolumnNaPytania = Integer.parseInt(rs.getString("totalCol"))-4;
				System.out.println("liczba KOLUMN na pytania to " + liczbaKolumnNaPytania);
			}
				
				if(liczbaKolumnNaPytania < liczbaPytan) {//trzeba dodac nowe kolumny na test
					for(int i=liczbaKolumnNaPytania; i<=liczbaPytan; i++) {
						String nazwaKolumny="pyt";
						nazwaKolumny+=Integer.toString(i);
						System.out.println(nazwaKolumny);
						zapytanie="ALTER TABLE SPRAWDZIAN add(" + nazwaKolumny + " VARCHAR2(6) DEFAULT NULL)";
						
						stmt = null;
					    try {
					        stmt = con.createStatement();
					        stmt.executeUpdate(zapytanie);
					    } catch (SQLException e) {
					    	
					    } //finally {
					        //if (stmt != null) { stmt.close(); }
					    //}
						
				}
				}	
				stmt = null;
			    try {
			        stmt = con.createStatement();
			        stmt.executeUpdate("Commit");
			    } catch (SQLException e) {
			    	
			    }
				liczbaKolumnNaPytania=liczbaPytan;
			//}
		} catch (Exception ex) {
			ex.getStackTrace();
			blad=ex.toString();
			
			// obsluga bledow
		} finally { // zwalnianie zasobow
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
					// ignorujemy
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					// ignorujemy
				}
				stmt = null;
			}
			// analogicznie con.close();
		}
	}
public String getBlad() {
	return blad;
}
	public int getLiczbaPytan(){
		return liczbaPytan;
	}
	public int getLiczbaOdpowiedzi() {
		return liczbaOdpowiedzi;
	}
	public int getliczbaKolumnNaPytania() {
		return liczbaKolumnNaPytania;
	}
	public void closeConnection() {
		try {
			this.con.close();
			System.out.println("Zamykam po³czenie z baza danych		ANDROID");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dodajDaneStudenta( ) {
		String zapytanie="INSERT ALL INTO SPRAWDZIAN(idTestu, grupaTestu,indeksStudenta) VALUES (1,'F1A1','209221') SELECT * FROM dual";
		stmt = null;
	    try {
	        stmt = con.createStatement();
	        stmt.executeUpdate(zapytanie);
	    } catch (SQLException e) {
	    	
	    }
	}
	//wysyla do bazy komende/zapytanie np update table instert...
	public void sendQuery(String zapytanie) {
		stmt = null;
	    try {
	        stmt = con.createStatement();
	        stmt.executeUpdate(zapytanie);
	    } catch (SQLException e) {
	    	
	    }
	}
}
