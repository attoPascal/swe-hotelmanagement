package hm;

import java.io.Serializable;
import java.util.ArrayList;

public class Zimmer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Zimmernummer
	 */
	private int nummer;

	/**
	 * Liste mit allen Buchungen fuer dieses Zimmer
	 */
	ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
	
	
	public Zimmer(int nummer){
		
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
	
	public void addBuchung(Kategorie kategorie, Aufenthalt aufenthalt){
		
		buchungen.add(new Buchung(kategorie, aufenthalt));

	}

}
