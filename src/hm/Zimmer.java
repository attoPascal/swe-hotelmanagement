package hm;

import java.util.ArrayList;

public class Zimmer {

	/**
	 * Zimmernummer
	 */
	private int nummer;

	/**
	 * Liste mit allen Buchungen für dieses Zimmer
	 */
	ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
	
	
	public Zimmer (Kategorie kategorie, int nummer){
		
		this.nummer = nummer;
		
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


	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	public void addBuchung(Aufenthalt aufenthalt){
		
		//TODO 
		
	}

}
