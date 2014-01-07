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

	public DAO getDAO() {
		return dao;
	}
}
