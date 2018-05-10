package tg.sprawdzian;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Sprawdzian {
	
	private int 	idTestu=0;
	private String 	grupaTestu="";
	private String 	indeksStudenta="";
	
	private int liczbaPytan=0;
	private int liczbaKolumnNaPytania=0;
	private int liczbaOdpowiedzi=0;
	
	private ArrayList<Odpowiedz>OdpowiedziStudenta=new ArrayList<Odpowiedz>();//lista odpowiedzi do kazdego poytania
	
	
	
	//private Odpowiedz[] OdpowiedziStudenta;// = new Odpowiedz[liczbaOdpowiedzi];
	
		
	//Konstruktor bezparametrowy 
	public Sprawdzian() {
		//inicjujListeOdpowiedzi();
		}

	//konstruktor z mo¿liwoœci podania pe³nych parametrów
	public Sprawdzian(int IdTestu, String GrupaTestu, String IndeksStudenta, int LiczbaPytan, int LiczbaKolumn, int LiczbaOdpowiedzi){
		this.idTestu=IdTestu;
		this.grupaTestu=GrupaTestu;
		this.indeksStudenta=IndeksStudenta;
		this.liczbaPytan=LiczbaPytan;
		this.liczbaKolumnNaPytania=LiczbaKolumn;
		this.liczbaOdpowiedzi=LiczbaOdpowiedzi;
	//	inicjujListeOdpowiedzi();
	}
	
	//funkcja pozwalajca na ustawienie parametrów testu
	public void setParam(int IdTestu, String GrupaTestu, String IndeksStudenta, int LiczbaPytan, int LiczbaKolumn, int LiczbaOdpowiedzi){
		this.idTestu=IdTestu;
		this.grupaTestu=GrupaTestu;
		this.indeksStudenta=IndeksStudenta;
		this.liczbaPytan=LiczbaPytan;
		this.liczbaKolumnNaPytania=LiczbaKolumn;
		this.liczbaOdpowiedzi=LiczbaOdpowiedzi;
		inicjujListeOdpowiedzi();
	}
	
	//funkcja wypisujca w konsoli wszystkie parametry 
	public void wypisz() {
		System.out.println("Id testu:        "+idTestu);
		System.out.println("Grupa testu:     "+grupaTestu);
		System.out.println("Indeks Studenta: "+indeksStudenta);
		System.out.println("Liczba Pytan: 	 "+liczbaPytan);
		System.out.println("Liczba kolumn na pytania: "+liczbaKolumnNaPytania);
		System.out.println("Liczba odpowiedzi do kazdego pytania: "+liczbaOdpowiedzi);		
	}
	
	//funkcja inicjalizujca listê odpowiedzi na zadan liczbê pytañ 
	private void inicjujListeOdpowiedzi() {
		for(int i=0;i<liczbaPytan;i++) {
			//Odpowiedz odb=new Odpowiedz();
			//this.OdpowiedziStudenta.add(new Odpowiedz());
			this.OdpowiedziStudenta.add(new Odpowiedz());
			this.OdpowiedziStudenta.get(i).setLiczbaOdpowiedzi(this.liczbaOdpowiedzi);
		}	
	}

// SETERY
	//funkcja pozwalajca na modyfikacjê odpowiedzi - danego podpunktu na aktualne pytanie
	public void setOdpowiedz(int nrPytania, String podpunkt, int wartosc) {//podpunkt a,b,c, lub d a wartosc 1 lub 0
		nrPytania-=1;
		if(podpunkt=="A") {
			
			this.OdpowiedziStudenta.get(nrPytania).setA(wartosc);
			System.out.println("modyfikacja podpunktu A, odpowiedz to "+this.OdpowiedziStudenta.get(nrPytania).getA());
//			this.OdpowiedziStudenta[nrPytania-1].setA(wartosc);
		}else if(podpunkt=="B") {
		
			this.OdpowiedziStudenta.get(nrPytania).setB(wartosc);
			System.out.println("modyfikacja podpunktu B, odpowiedz to "+this.OdpowiedziStudenta.get(nrPytania).getB());

		}else if(podpunkt=="C") {
			this.OdpowiedziStudenta.get(nrPytania).setC(wartosc);
			System.out.println("modyfikacja podpunktu C, odpowiedz to "+this.OdpowiedziStudenta.get(nrPytania).getC());

		}else if(podpunkt=="D") {
			System.out.println("modyfikacja podpunktu D");
			this.OdpowiedziStudenta.get(nrPytania).setD(wartosc);
		}else if(podpunkt=="E") {
			System.out.println("modyfikacja podpunktu E");
			this.OdpowiedziStudenta.get(nrPytania).setE(wartosc);
		}//*/
	}
	
	
//GETERY ----------------
	public int getOdpowiedz(int nrPytania, String podpunkt) {//podpunkt a,b,c, lub d zwraca aktualny stan podputnku
		nrPytania-=1;
		if(podpunkt=="A") {
			//System.out.println("pobieranie podpunktu A");
			return this.OdpowiedziStudenta.get(nrPytania).getA();
//			this.OdpowiedziStudenta[nrPytania-1].setA(wartosc);
		}else if(podpunkt=="B") {
			//System.out.println("pobieranie podpunktu B");
			return this.OdpowiedziStudenta.get(nrPytania).getB();
		}else if(podpunkt=="C") {
			//System.out.println("pobieranie podpunktu C");
			return this.OdpowiedziStudenta.get(nrPytania).getC();
		}else if(podpunkt=="D") {
			//System.out.println("pobieranie podpunktu D");
			return this.OdpowiedziStudenta.get(nrPytania).getD();
		}else if(podpunkt=="E") {
			//System.out.println("pobieranie podpunktu E");
			return this.OdpowiedziStudenta.get(nrPytania).getE();
		}//*/,
		return -1;
	}
	
	public int getLiczbaPytan() {
		return liczbaPytan;
	}
	
	public int getLiczbaOdpowiedzi() {
		return liczbaOdpowiedzi;
	}

	//zwraca cala odpowiedz na dane pytanie, odpowiedz ta ma postac 11 lub 10 lub 01 lub 00 itd. i jest ju¿ wstawiana do n=bazy danych 
	public String getFinalAnswear(int nrPytania) {
		return this.OdpowiedziStudenta.get(nrPytania).getAnswear();
	}

	public int getIdTestu() {
		return this.idTestu;
	}
	public String getGrupaTestu() {
		return this.grupaTestu;
	}
	public String getIndeksStudenta() {
		return this.indeksStudenta;
	}
}
