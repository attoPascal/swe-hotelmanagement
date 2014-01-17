package hm.managers;

import java.io.FileNotFoundException;
import java.io.IOException;

import hm.Aufenthalt;
import hm.Kategorie;
import hm.Zimmer;
import hm.Buchung;
import hm.Hotel;
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
	 * @param name
	 *            Name das Hotels für das eine neue Buchung erstellt werden soll
	 * @return gibt die Nummer des Zimmers zurueck, das gebucht wurde.
	 */
	public int createBuchung(Kategorie kategorie, Aufenthalt aufenthalt,
			String name) {
		Zimmer zimmer = kategorie.getZimmer(aufenthalt);
		zimmer.addBuchung(kategorie, aufenthalt);

		return zimmer.getNummer();
	}

	/**
	 * @param buchung
	 * @param aufenthalt
	 * @param name
	 *            Name des Hotes für das die Zeit einer Buchung verändert werden
	 *            soll
	 * @return
	 */
	public String editAufenthalt(Buchung buchung, Aufenthalt aufenthalt,
			String name) throws ClassNotFoundException, FileNotFoundException,
			IOException {

		Hotel hotel = dao.getHotelByName(name);
		Zimmer zimmer = hotel.getZimmer(buchung.getZimmernummer());
		
		/**
		 * Sollte das Zimmer im Hotel nicht vorhanden sein
		 */
		if (zimmer == null) 
			return "Das Zimmer existiert nicht";

		if (Zimmer.isBooked(zimmer, aufenthalt)) {
			return "Das Zimmer ist du diesem Zeitpunkt leider schon belegt";

		} else {
			buchung.setAufenthalt(aufenthalt);
			return "Der Aufenthalt wurde erfolgreich editiert";
		}
	}

	/**
	 * @param buchung
	 * @param zimmer
	 * @param name
	 *            Name des Hotels für das die Zimmernummer einer Buchung
	 *            geändert werden soll
	 */
	/* TODO Es wäre vermutlich sinnvoller, anstatt eines Zimmerobjekts nur die Nummer zu übergeben*/
	public String editZimmerNummer(Buchung buchung, Zimmer zimmer, String name)
			throws ClassNotFoundException, FileNotFoundException, IOException {
		
		Hotel hotel = dao.getHotelByName(name);
		/**
		 * Das übergebene Zimmerobjekt könnte zB nicht zu dem ausgewählten Hotel
		 * gehören, deshalb ist es notwendig, dies zu überprüfen
		 */
		Zimmer room = hotel.getZimmer(zimmer.getNummer());
		
		/**
		 * Sollte das Zimmer im Hotel nicht vorhanden sein
		 */
		if (room == null) 
			return "Das Zimmer existiert nicht";
		
		if (Zimmer.isBooked(room, buchung.getAufenthalt())) {
			return "Das Zimmer ist zu diesem Zeitpunkt schon belegt";
		
		} else {
			/**
			 * Das alte Zimmer aus der Buchung entfernen
			 */
			buchung.getZimmer().removeBuchung(buchung.getId());
			/**
			 * Dem neuen Zimmer eine Buchung hinzufügen
			 */
			room.addBuchung(buchung.getKategorie(), buchung.getAufenthalt());
			/**
			 * Das Zimmer der Buchung zuteilen
			 */
			buchung.setZimmer(room);

			return "Das Zimmer für die Buchung wurde erfolgreich geändert";
		}
	}

	/**
	 * Storniert eine bestehenede Buchung/ Buchung wird entfernt
	 * 
	 * @param id
	 *            ID der Buchung
	 * @param nummer
	 *            Nummer des Zimmers von der die Buchung entfernt werden soll
	 * @param name
	 *            Name des Hotels von dem die Buchung entfernt werden soll
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void removeBuchung(int id, int nummer, String name)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		Zimmer zimmer = dao.getHotelByName(name).getZimmer(nummer);
		zimmer.removeBuchung(id);
	}

	public DAO getDAO() {
		return dao;
	}
}
