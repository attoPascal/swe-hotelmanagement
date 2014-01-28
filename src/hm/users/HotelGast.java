package hm.users;

import java.util.ArrayList;
import java.util.List;

import hm.Buchung;

/**
 * Benutzer Hotelgast, kann Buchungen vornehmen
 */
public class HotelGast extends AbstractUser{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Integer> buchungen;
	private String zahlungsdaten;
	
	public HotelGast(String username, String password, String zahlungsdaten) {
		super(username, password);
		
		this.zahlungsdaten = zahlungsdaten;
		this.buchungen = new ArrayList<Integer>();
	}
	
	/**
	 * @return bestehende Buchungen des HotelGasts
	 */
	public List<Integer> getBuchungsIDs() {
		return buchungen;
	}
	
	/**
	 * @param buchung
	 */
	public void addBuchung(Buchung buchung) {
		buchungen.add(buchung.getId());
	}
	
	/**
	 * @param buchung
	 */
	public void removeBuchung(Buchung buchung) {
		buchungen.remove(buchung.getId());
	}
	
	/**
	 * @return Zahlungsdaten des Hotelgasts
	 */
	public String getZahlungsdaten() {
		return zahlungsdaten;
	}
	
	/**
	 * @param zahlungsdaten
	 */
	public void setZahlungsdaten(String zahlungsdaten) {
		this.zahlungsdaten = zahlungsdaten;
	}
}
