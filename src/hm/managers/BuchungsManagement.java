package hm.managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hm.Aufenthalt;
import hm.Kategorie;
import hm.Zimmer;
import hm.Buchung;
import hm.Hotel;
import hm.dao.*;
import hm.users.HotelGast;

public class BuchungsManagement {

	private DAO dao;

	public BuchungsManagement() {

	}

	/**
	 * Erstellt eine neue Buchung
	 * @return gibt die Nummer des Zimmers zurueck, das gebucht wurde.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * @throws ParseException 
	 */
	public Buchung createBuchung(String hotelName, String katName, String dateString, int duration, HotelGast gast) throws FileNotFoundException, ClassNotFoundException, IOException, ParseException {
		DAO dao = getDAO();
		
		Hotel hotel = dao.getHotelByName(hotelName);
		Kategorie kategorie = hotel.getKategorie(katName);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(dateString);
		Aufenthalt aufenthalt = new Aufenthalt(date, duration);
		
		Zimmer zimmer = kategorie.getZimmer(aufenthalt);
		Buchung buchung = zimmer.addBuchung(kategorie, aufenthalt, dao.getNextBuchungsID());
		
		gast.addBuchung(buchung);
		dao.saveUser(gast);
		dao.saveHotel(hotel);
		
		return buchung;
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

		if (zimmer.isBooked(aufenthalt)) {
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
		
		// Sollte das Zimmer im Hotel nicht vorhanden sein
		if (room == null) 
			return "Das Zimmer existiert nicht";
		
		if (room.isBooked(buchung.getAufenthalt())) {
			return "Das Zimmer ist zu diesem Zeitpunkt schon belegt";
		
		} else {
			// Das alte Zimmer aus der Buchung entfernen
			buchung.getZimmer().removeBuchung(buchung.getId());

			// Dem neuen Zimmer eine Buchung hinzufügen
			room.addBuchung(buchung.getKategorie(), buchung.getAufenthalt(), buchung.getId());
			
			// Das Zimmer der Buchung zuteilen
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
	 * @param gast 
	 * 			  Hotelgast, dessen Buchung entfernt wird            
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void removeBuchung(int id, int nummer, String name, HotelGast gast)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		
		Hotel hotel =dao.getHotelByName(name);
		Zimmer zimmer = hotel.getZimmer(nummer);
		zimmer.removeBuchung(id);
				
		zimmer.removeBuchungHG(id, gast);
		
		dao.saveUser(gast);
		dao.saveHotel(hotel);
	}
	
	public String getBuchungsbestaetigung(Buchung buchung) {
		String bestaetigung =
				"Ihre Buchung war erfolgreich, ihre Zimmernummer ist " + buchung.getZimmernummer() +
				"<br>Datum ihrer Buchung: "+ buchung.getAufenthalt().getAnfang().toString() +
				"<br>Gesamtkosten: " + buchung.getKosten() +
				"<br>Kategorie: " + buchung.getKategorie().getName() +
				"<br>Ausstattung: " + buchung.getKategorie().getAusstattung();

		
		return bestaetigung;
	}
	public void instantiateDAO() throws IOException{
		
		dao = SerializedDAO.getInstance();
		
	}
	
	public DAO getDAO() throws IOException {
		return SerializedDAO.getInstance();
	}
}
