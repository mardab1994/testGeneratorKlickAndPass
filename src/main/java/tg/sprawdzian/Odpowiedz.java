package tg.sprawdzian;
/*
 * klasa przechowuje odpowiedz na dane  pytanie tj. przechowuje odpowiedz- wartosc kazdego podpunktu czyli jesli zaznaczymy A to A=1, 
 * jesli zaznaczymy tez B to B=1 itd... a jesli odznaczymy to 0 itd...
 * */
public class Odpowiedz {
		
		private int liczbaOdpowiedzi=0;
	
		private int a=0;
		private int b=0;
		private int c=0;
		private int d=0;
		private int e=0;
		
// KONSTRUKTORY
		public Odpowiedz() {}
		public Odpowiedz(int LiczbaOdpowiedzi) {
			this.liczbaOdpowiedzi=LiczbaOdpowiedzi;
		}
// SETERY
		public void setLiczbaOdpowiedzi(int liczbaOdp) {
			this.liczbaOdpowiedzi=liczbaOdp;
		}
		public void setA(int A) {
			this.a=A;
		}
		public void setB(int B) {
			this.b=B;
		}
		public void setC(int C) {
			this.c=C;
		}
		public void setD(int D) {
			this.d=D;
		}
		public void setE(int E) {
			this.e=E;
		}
// GETERY
		public int getA() {
			return this.a;
		}
		public int getB() {
			return this.b;
		}
		public int getC() {
			return this.c;
		}
		public int getD() {
			return this.d;
		}
		public int getE() {
			return this.e;
		}
// CALA ODPOWIEDZ NA PYTANIE - zwraca odpowiedz na clae pytanie ktore wysy³amy do bazy danych 
		public String getAnswear() {
			if(this.liczbaOdpowiedzi==2) {
				return Integer.toString(this.a)+Integer.toString(this.b);
			}else if(this.liczbaOdpowiedzi==3) {
				return Integer.toString(this.a)+Integer.toString(this.b)+Integer.toString(this.c);
			}else if(this.liczbaOdpowiedzi==4) {
				return Integer.toString(this.a)+Integer.toString(this.b)+Integer.toString(this.c)+Integer.toString(this.d);
			}else if(this.liczbaOdpowiedzi==5) {
				return Integer.toString(this.a)+Integer.toString(this.b)+Integer.toString(this.c)+Integer.toString(this.d)+Integer.toString(this.e);
			}else {
				return "nic";
			}
		}
}
