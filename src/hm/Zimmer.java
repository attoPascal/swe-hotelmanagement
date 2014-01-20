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
	public Buchung addBuchung(Kategorie kategorie, Aufenthalt aufenthalt){
		Buchung buchung = new Buchung(kategorie, aufenthalt);
		buchungen.add(buchung);
		return buchung;
	}
	
//	/**
//	 * Entfernt die Buchung mit dem selben Aufenthalt
//	 * @param aufenthalt Aufenthalt der Buchung welche gelöscht werden soll
//	 */
//	public void removeBuchung(Aufenthalt aufenthalt) {
//		
//		for (Buchung buchung : buchungen){
//			
//			if (buchung.getAufenthalt().equals(aufenthalt)) buchungen.remove(buchung);
//			
//		}
//	}
	

	/**
	 * Entfernt die Buchung mit der übergebenen ID
	 * @param id ID der Buchung die gelöscht werden soll
	 */
	public void removeBuchung(int id) {
		
		for (Buchung buchung : buchungen){
			
			if (buchung.getId() == id) buchungen.remove(buchung);
			
		}
	}
	
	
	/**
	 * Ueberprueft, ob ein Zimmer zu einem Zeitraum(Aufenthalt) gebucht ist
	 * 
	 * @param zimmer Zimmer-Objekt, das ueberprueft wird
	 * @param aufenthalt Zeitraum der gewuenschten Buchung
	 * @return gibt true zurueck, wenn es eine ueberlappung gibt (d.h. das Zimmer bereits gebucht ist)
	 */
	public boolean isBooked(Aufenthalt aufenthalt) {

		for (Buchung buchung : this.getBuchungen()) {

			if (buchung.getAufenthalt().overlaps(aufenthalt))
				return true;

		}

		return false;

	}

}
