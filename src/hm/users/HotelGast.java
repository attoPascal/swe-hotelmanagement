package hm.users;

import java.util.Date;
import java.util.HashMap;

import hm.Buchung;
import hm.Service;

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
	private HashMap<Date,Service> services;
	
	public HotelGast(String username, String password, Buchung buchung, String zahlungsdaten) {
		super(username, password);
		this.buchung = buchung;
		this.zahlungsdaten = zahlungsdaten;
		this.services = new HashMap<Date,Service>();
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
	
	/**
	 * Fügt ein neues Service zum angegebenen Zeitpunkt hinzu
	 */
	public void addService(Date date, Service service) {
		services.put(date, service);
	}
	
	/**
	 * Gibt die HashMap aller gebuchten Services mit ihrem jeweiligen Datum zurück
	 */
	public HashMap<Date,Service> getServices() {
		return services;
	}
}
