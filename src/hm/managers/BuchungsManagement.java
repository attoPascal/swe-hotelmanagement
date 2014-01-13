package hm.managers;

import java.io.FileNotFoundException;
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
	 *            
	 * @param name Name das Hotels für das eine neue Buchung erstellt werden soll
	 * @return gibt die Nummer des Zimmers zurueck, das gebucht wurde.
	 */
	public int createBuchung(Kategorie kategorie, Aufenthalt aufenthalt, String name) {
		Zimmer zimmer = kategorie.getZimmer(aufenthalt);
		zimmer.addBuchung(kategorie, aufenthalt);

		return zimmer.getNummer();
	}
	
	
	/**
	 * @param buchung
	 * @param aufenthalt
	 * @param name Name des Hotes für das die Zeit einer Buchung verändert werden soll
	 * @return
	 */
	public String editAufenthalt(Buchung buchung, Aufenthalt aufenthalt, String name) {
		return null;
	}
	
	/**
	 * @param buchung
	 * @param zimmer
	 * @param name Name des Hotels für das die Zimmernummer einer Buchung geändert werden soll
	 */
	public void editZimmerNummer(Buchung buchung, Zimmer zimmer, String name) {
		
	}

	
	/**
	 * Storniert eine bestehenede Buchung/ Buchung wird entfernt
	 * @param aufenthalt Zeitspanne der Buchung
	 * @param nummer Nummer des Zimmers von der die Buchung entfernt werden soll
	 * @param name Name des Hotels von dem die Buchung entfernt werden soll
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void removeBuchung(Aufenthalt aufenthalt, int nummer, String name) throws FileNotFoundException, ClassNotFoundException, IOException {
		Zimmer zimmer = dao.getHotelByName(name).getZimmer(nummer);
		zimmer.removeBuchung(aufenthalt);
	}
	
	public DAO getDAO() {
		return dao;
	}
}
