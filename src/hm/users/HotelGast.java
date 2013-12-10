package hm.users;

import hm.Buchung;

public class HotelGast extends AbstractUser{

	private Buchung buchung;
	private String zahlungsdaten;
	
	public HotelGast(Buchung buchung, String zahlungsdaten) {
		super();
		this.buchung = buchung;
		this.zahlungsdaten = zahlungsdaten;
	}
	public Buchung getBuchung() {
		return buchung;
	}
	public void setBuchung(Buchung buchung) {
		this.buchung = buchung;
	}
	public String getZahlungsdaten() {
		return zahlungsdaten;
	}
	public void setZahlungsdaten(String zahlungsdaten) {
		this.zahlungsdaten = zahlungsdaten;
	}
	
	
	
}
