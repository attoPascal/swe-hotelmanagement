package hm.managers;

import java.io.IOException;

import hm.Aufenthalt;
import hm.Kategorie;
import hm.Zimmer;
import hm.Buchung;
import hm.dao.*;

public class BuchungsManagement {

	private DAO dao;

	public BuchungsManagement() {

	}

	/**
	 * Damit die Exceptions im Servlet zentral in der doGet-Methode behandelt
	 * werden können, gibt es eine eigene Methode, die die Membervariable dao
	 * instanziert
	 */
	public void instantiateDAO(String filename) throws IOException {
		dao = SerializedDAO.getInstance();
	}

	/**
	 * Erstellt eine neue Buchung
	 * 
	 * @param kategorie
	 *            Kategorie des Zimmers
	 * @param aufenthalt
	 *            Zeitraum, der gebucht wurde
	 * @return gibt die Nummer des Zimmers zurueck, das gebucht wurde.
	 */
	public int neueBuchung(Kategorie kategorie, Aufenthalt aufenthalt) {
		Zimmer zimmer = kategorie.getZimmer(aufenthalt);
		zimmer.addBuchung(kategorie, aufenthalt);

		return zimmer.getNummer();
	}
	
	public String stornoBuchung(Buchung buchung) {
		return null;
	}
	
	public String changeAufenthalt(Buchung buchung, Aufenthalt newAufenthalt) {
		return null;
	}
	
	public void changeZimmernummer(Buchung buchung, Zimmer newroom) {
		
	}

	/**
	 * Storniert eine bestehende Buchung; Buchung-Objekt wird entfernt
	 * 
	 * @param kategorie
	 * 				des zu loeschenden Zimmers
	 * @param aufenthalt
	 * 				Zeitraum der Buchung, die storniert wird
	 * @param nummer
	 * 				Nummer zur Identifikation des Zimmers
	 * @param id ID der zu loeschenden Buchung
	 */
	public void BuchungStornieren(Buchung Buchung, int id) {
		Zimmer zimmer = Buchung.getZimmer();
		//int nummer=zimmer.getNummer();
		zimmer.removeBuchung(Buchung, id);
	}
	
	public DAO getDAO() {
		return dao;
	}
}
