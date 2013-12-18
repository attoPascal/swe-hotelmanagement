package hm.users;

import hm.Buchung;

/**
 * Benutzer Hotelgast, kann Buchungen vornehmen
 */
public class HotelGast extends AbstractUser{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Buchung buchung;
	private String zahlungsdaten;
	
	public HotelGast(String username, String password, Buchung buchung, String zahlungsdaten) {
		super(username, password);
		this.buchung = buchung;
		this.zahlungsdaten = zahlungsdaten;
	}
	
	/**
	 * @return bestehende Buchung des HotelGasts
	 */
	public Buchung getBuchung() {
		return buchung;
	}
	
	/**
	 * @param buchung
	 */
	public void setBuchung(Buchung buchung) {
		this.buchung = buchung;
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
