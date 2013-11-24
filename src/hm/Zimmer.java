package hm;

import java.util.ArrayList;

public class Zimmer {

	/**
	 * Zimmernummer
	 */
	private int nummer;
	
	private Kategorie kategorie;

	/**
	 * Liste mit allen Buchungen für dieses Zimmer
	 */
	ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
	
	
	public Zimmer (int nummer, Kategorie kat){
	
	}
	

	public ArrayList<Buchung> getBuchungen() {
		return buchungen;
	}


	public void setBuchungen(ArrayList<Buchung> buchungen) {
		this.buchungen = buchungen;
	}


	public int getNummer() {
		return nummer;
	}
	
	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}


	public Kategorie getKategorie() {
		return this.kategorie;
	}


	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	public void addBuchung(Aufenthalt aufenthalt){
		
		//TODO 
		
	}

}
