package hm.managers;

import java.io.FileNotFoundException;
import java.io.IOException;

import hm.Hotel;
import hm.Kategorie;
import hm.dao.*;

public class KategorieManagement {

	private DAO dao;

	public KategorieManagement() {

	}

	/**
	 * Damit die Exceptions im Servlet zentral in der doGet-Methode behandelt
	 * werden können, gibt es eine eigene Methode, die die Membervariable dao
	 * instanziert
	 */
	public void instantiateDAO() throws IOException {
		dao = SerializedDAO.getInstance();
	}

	/**
	 * Erlaubt das Veraendern eines Kategorie-Objekts
	 * 
	 * @param hotel
	 *            Hotel, in dem die Kategorie vorkommt
	 * @param name
	 *            bestehender Name der Kategorie
	 * @param newName
	 *            neuer Name der kategorie
	 * @param preis
	 *            Preis der Kategorie in Cent pro Nacht
	 * @param ausstattung
	 *            Beschreibung der Ausstattung von Zimmern der Kategorie; als
	 *            String gespeichert
	 */
	public void editKategorie(String katName, String newName,
			int preis, String ausstattung, String name) throws FileNotFoundException,
			IOException, ClassNotFoundException, NullPointerException {

		Hotel hotel = dao.getHotelByName(name);
		
		Kategorie kat = hotel.getKategorie(katName);
		kat.setName(newName);
		kat.setPreis(preis);
		kat.setAusstattung(ausstattung);

		dao.saveHotel(hotel);
	}

	/**
	 * Erstellt ein Kategorie-Objekt
	 * 
	 * @param hotel
	 *            hotel, dem die Kategorie hinzugefuegt wird
	 * @param name
	 *            Name der neuen Kategorie
	 * @param ausstattung
	 *            Beschreibung der Ausstattung
	 * @param preis
	 *            Preis der Kategorie in Cent pro Nacht
	 */
	public void createKategorie(String katName, String ausstattung,
			int preis, String name) throws FileNotFoundException, IOException,
			ClassNotFoundException {
		
		Hotel hotel = dao.getHotelByName(name);
		
		Kategorie kat = new Kategorie(katName, preis, ausstattung);

		hotel.addKategorie(kat);
		dao.saveHotel(hotel);
	}

	/**
	 * Entfernt ein Kategorie-Objekt
	 * 
	 * @param hotel
	 *            Hotel, aus dem die Kategorie entfernt werden soll
	 * @param name
	 *            Name der Kategorie, die entfernt werden soll
	 */
	public void removeKategorie(String katName, String name)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		Hotel hotel = dao.getHotelByName(name);
		Kategorie kat = hotel.getKategorie(name);
		hotel.removeKategorie(kat);

		dao.saveHotel(hotel);
	}

	public DAO getDAO() {
		return dao;
	}
}
