package hm.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hm.Buchung;
import hm.Service;

/**
 * Benutzer Hotelgast, kann Buchungen vornehmen
 */
public class HotelGast extends AbstractUser{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Integer> buchungen;
	private String zahlungsdaten;
	private HashMap<Date,Service> services;
	
	public HotelGast(String username, String password, String zahlungsdaten) {
		super(username, password);
		
		this.zahlungsdaten = zahlungsdaten;
		this.services = new HashMap<Date,Service>();
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
