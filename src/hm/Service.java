package hm;

import java.io.Serializable;

/**
 * Beschreibt Services die von Hotelgästen während ihres Aufenthalts gebucht werden können
 */
public class Service implements Serializable {
	private String name;
	private String beschreibung;
	private int preis;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruiert ein neues Service mit den gegebenen Parametern
	 * @param name Name des Service
	 * @param beschreibung Beschreibungstext mit zusätzlichen Details
	 * @param preis Preis in Cents
	 */
	public Service(String name, String beschreibung, int preis) {
		super();
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}
}
