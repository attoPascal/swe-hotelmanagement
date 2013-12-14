package hm.managers;

import hm.Hotel;
import hm.Kategorie;
import hm.Zimmer;
import hm.dao.DAO;
import hm.dao.SerializedDAO;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ZimmerManagement {

	private DAO dao;

	public ZimmerManagement() {

	}

	/**
	 * Damit die Exceptions im Servlet zentral in der doGet-Methode behandelt
	 * werden können, gibt es eine eigene Methode, die die Membervariable dao
	 * instanziert
	 */
	public void instantiateDAO(String filename) throws IOException {
		dao = new SerializedDAO(filename);
	}

	/**
	 * Erstellt ein Zimmer-Objekt
	 * 
	 * @param hotelName
	 *            Hotel, in dem das Zimmer erstellt wird
	 * @param nummer
	 *            Nummer des Zimmers, das erstellt wird
	 */
	public void createZimmer(String hotelName, int nummer)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		Hotel hotel = dao.getHotelByName(hotelName);
		hotel.addZimmer(new Zimmer(nummer));
		dao.saveHotel(hotel);
	}

	/**
	 * Entfernt ein Zimmer-Objekt
	 * 
	 * @param hotelName
	 *            Hotel, aus dem das Zimmer entfernt wird
	 * @param nummer
	 *            Nummer des Zimmers, das entfernt wird
	 */
	public void deleteZimmer(String hotelName, int nummer)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		Hotel hotel = dao.getHotelByName(hotelName);
		Zimmer zimmer = hotel.getZimmer(nummer);
		hotel.removeZimmer(zimmer);
		dao.saveHotel(hotel);
	}

	/**
	 * Erlaubt das Zuordnen eines bestimmten Zimmers zu einer Kategorie
	 * 
	 * @param hotelName
	 *            Name des Hotels
	 * @param katName
	 *            Name der Kategorie, der das Zimmer zugeordnet werden soll
	 * @param zimmerNummer
	 *            Nummer zur Identifizierung des Zimmers
	 */
	public void setZimmerKategorie(String hotelName, String katName,
			int zimmerNummer) throws FileNotFoundException, IOException,
			ClassNotFoundException {

		Hotel hotel = dao.getHotelByName(hotelName);
		Kategorie kategorie = hotel.getKategorie(katName);
		Zimmer zimmer = hotel.getZimmer(zimmerNummer);

		// aus alter Kategorie entfernen
		for (Kategorie k : hotel.getKategorien()) {
			if (k.hasZimmer(zimmerNummer)) {
				k.removeZimmer(zimmerNummer);
			}
		}

		// zu neuer Kategorie hinzufuegen
		kategorie.addZimmer(zimmer);

		dao.saveHotel(hotel);
	}

	/**
	 * Gibt ein DAO-Objekt zur konsistenten Speicherung von Hoteldaten zurueck
	 * 
	 * @return DAO (Implementierung: Serialisiert)
	 */
	public DAO getDAO() {
		return dao;
	}
}
