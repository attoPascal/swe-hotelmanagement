package hm;

import java.util.Date;


public class Buchung {

	/**
	 * zu Buchendes Zimmer
	 */
	private Zimmer zimmer;
	
	
	private Kategorie kategorie;
	
	/**
	 * Dauer des Aufenthalts
	 */
	private Aufenthalt aufenthalt;
	
	/**
	 * kosten der Buchung
	 */
	private int kosten;
	
	
	
	public Kategorie getKategorie() {
		return kategorie;
	}

	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}

	public Zimmer getZimmer() {
		return zimmer;
	}

	public void setZimmer(Zimmer zimmer) {
		this.zimmer = zimmer;
	}

	public Aufenthalt getAufenthalt() {
		return aufenthalt;
	}

	public void setAufenthalt(Aufenthalt aufenthalt) {
		this.aufenthalt = aufenthalt;
	}

	public int getKosten() {
		return kosten;
	}

	public void setKosten(int kosten) {
		this.kosten = kosten;
	}
/*
	public Buchung(Zimmer zimmer, Date d, int tage){
		
		this.zimmer = zimmer;
		this.aufenthalt = new Aufenthalt(d,tage);
		this.kosten = tage * zimmer.getKategorie().getPreis();
		
	}
	*/
	public Buchung(Kategorie kategorie, Date d, int tage){
		
		this.kategorie = kategorie;
		this.aufenthalt = new Aufenthalt(d,tage);
		this.kosten = tage * kategorie.getPreis();
		this.zimmer = kategorie.getZimmer(aufenthalt);
	}
	
	public Buchung(Kategorie kategorie, Aufenthalt aufenthalt){
		
		this.kategorie = kategorie;
		this.aufenthalt = aufenthalt;
		this.kosten = aufenthalt.getDays() * kategorie.getPreis();
		this.zimmer = kategorie.getZimmer(aufenthalt);
	}

}
