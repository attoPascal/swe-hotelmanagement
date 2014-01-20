package hm.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import hm.Buchung;
import hm.Service;

/**
 * Benutzer Hotelgast, kann Buchungen vornehmen
 */
public class HotelGast extends AbstractUser{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Buchung> buchungen;
	private String zahlungsdaten;
	private HashMap<Date,Service> services;
	
	public HotelGast(String username, String password, Buchung buchung, String zahlungsdaten) {
		super(username, password);
		
		this.zahlungsdaten = zahlungsdaten;
		this.services = new HashMap<Date,Service>();
		
		buchungen = new ArrayList<Buchung>();
		buchungen.add(buchung);
	}
	
	/**
	 * @return bestehende Buchung des HotelGasts
	 */
	public Buchung getBuchung() {
		return buchungen.get(buchungen.size());
	}
	
	/**
	 * @param buchung
	 */
	public void setBuchung(Buchung buchung) {
		buchungen.add(buchung);
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
	public void addService(Service service, Date date) {
		services.put(date, service);
	}
	
	/**
	 * Gibt die HashMap aller gebuchten Services mit ihrem jeweiligen Datum zurück
	 */
	public Map<Date,Service> getServices() {
		return services;
	}
}
