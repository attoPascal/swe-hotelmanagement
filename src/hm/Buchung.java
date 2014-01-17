package hm;

import java.io.Serializable;
import java.util.Date;

/**
 * Buchung. Weist ein Zimmer einem Aufenthalt zu
 */
public class Buchung implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * zu buchendes Zimmer
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
	
	private static int buchungen = 0;
	
	private int id;
	
	
	/**
	 * @return Nummer der Buchung
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return gebuchte Kategorie
	 */
	public Kategorie getKategorie() {
		return kategorie;
	}

	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}
	
	/**
	 * 
	 * @return Nummer des gebuchten Zimmers
	 */
	public int getZimmernummer() {
		return this.zimmer.getNummer();
	}

	/**
	 * @return gebuchtes Zimmer
	 */
	public Zimmer getZimmer() {
		return zimmer;
	}

	public void setZimmer(Zimmer zimmer) {
		this.zimmer = zimmer;
	}

	/**
	 * @return Zeitraum einer Buchung
	 */
	public Aufenthalt getAufenthalt() {
		return aufenthalt;
	}

	public void setAufenthalt(Aufenthalt aufenthalt) {
		this.aufenthalt = aufenthalt;
	}

	/**
	 * @return Kosten einer Buchung
	 */
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
	/**
	 * Buchung anhand von Kategorie, Datum und Tage
	 * 
	 * @param kategorie der Buchung
	 * @param d Datum des Anfangs der Buchung
	 * @param tage Anzahl der Tage der Buchung
	 */
	public Buchung(Kategorie kategorie, Date d, int tage){
		
		this.kategorie = kategorie;
		this.aufenthalt = new Aufenthalt(d,tage);
		this.kosten = tage * kategorie.getPreis();
		this.zimmer = kategorie.getZimmer(aufenthalt);
		this.id = buchungen++;
		
	}
	
	/**
	 * Buchung anhand von Kategorie und Aufenthalt
	 * 
	 * @param kategorie der Buchung
	 * @param aufenthalt Zeitraum der Buchung
	 */
	public Buchung(Kategorie kategorie, Aufenthalt aufenthalt){
		
		this.kategorie = kategorie;
		this.aufenthalt = aufenthalt;
		this.kosten = aufenthalt.getDays() * kategorie.getPreis();
		this.zimmer = kategorie.getZimmer(aufenthalt);
		this.id = buchungen++;
	}

}
