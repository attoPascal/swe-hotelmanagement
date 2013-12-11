package hm;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Eindeutiges Zimmer mit zugeordneten Buchungen
 */
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
	
	/**
	 * @param nummer initialisiert eine Zimmernummer
	 */
	public Zimmer(int nummer){
		
		this.nummer = nummer;
		
	}
	
	/**
	 * @return gibt eine ArrayList an bestehenden Buchungen zurueck
	 */
	public ArrayList<Buchung> getBuchungen() {
		return buchungen;
	}

	/**
	 * initialisiert die buchungen
	 * @param buchungen Liste mit Buchungen die dem Zimmer zugeteilt werden sollen
	 */
	public void setBuchungen(ArrayList<Buchung> buchungen) {
		this.buchungen = buchungen;
	}


	/**
	 * @return gibt Nummer des gefragen Zimmers zurueck
	 */
	public int getNummer() {
		return nummer;
	}

	/**
	 * @param nummer Legt Nummer eines Zimmers fest
	 */
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	/**
	 * Erstellt ein Buchung-Objekt
	 * 
	 * @param kategorie Zimmer-Kategorie der Buchung
	 * @param aufenthalt Zeitraum der Pruefung
	 */
	public void addBuchung(Kategorie kategorie, Aufenthalt aufenthalt){
		
		buchungen.add(new Buchung(kategorie, aufenthalt));

	}
	
	/**
	 * Ueberprueft, ob ein Zimmer zu einem Zeitraum(Aufenthalt) gebucht ist
	 * 
	 * @param zimmer Zimmer-Objekt, das ueberprueft wird
	 * @param aufenthalt Zeitraum der gewuenschten Buchung
	 * @return gibt true zurueck, wenn es eine ueberlappung gibt (d.h. das Zimmer bereits gebucht ist)
	 */
	public static boolean isBooked(Zimmer zimmer, Aufenthalt aufenthalt) {

		for (Buchung buchung : zimmer.getBuchungen()) {

			if (buchung.getAufenthalt().overlaps(aufenthalt))
				return true;

		}

		return false;

	}

}
