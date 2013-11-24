package hm;

import java.util.ArrayList;

/**
 * Version 1.1
 * Die Methoden sind nicht fertig.
 * Im Prinzip sollte die Anzahl zum 
 * Zimmer- und Kategorien administrieren reichen
 */
public class Hotel {

	/**
	 * @uml.property  name="kategorien"
	 */
	ArrayList<Kategorie> kategorien = new ArrayList<Kategorie> ();
	/**
	 * @uml.property  name="zimmer"
	 */
	ArrayList<Zimmer> zimmer = new ArrayList<Zimmer>();
	
	int id;
	String name;
	
	public Hotel(String name, int id) {
		this.id = id;
		this.name = name;
	}
	
	public int getID() {
		return this.id;
	}
	
	/*
	 * ------------------
	 * Kategorie-Methoden
	 * ------------------
	 */
	
	/**
	 * @returns sämtliche vorhandenen Zimmerkategorien
	 */
	public ArrayList<Kategorie> getKategorien() {
		return kategorien;
	}

	/**
	 * Fügt eine neue Kategorie hinzu
	 */
	public void addKategorie(String name, int preis) {
		Kategorie kat = new Kategorie(name, preis);
		kategorien.add(kat);
	}
	
	/**
	 * ändert eine bestehende Kategorie, sowohl alter/neuer Name,
	 * als auch alter/neuer Preis müssen übergeben werden
	 */
	public void updateKategorie(String name, int preis) {
		
	}
	
	/**
	 * löscht eine bestehende Kategorie
	 */
	public void deleteKategorie(String name) {
		
	}
	
	/*
	 * --------------
	 * Zimmer-Metoden
	 * --------------
	 */

	/**
	 * @returns sämtliche vorhandene Zimmer
	 */
	public ArrayList<Zimmer> getZimmerList() {
		return zimmer;
	}
	
	/**
	 * Gibt die Bezeichnung der Zimmerkategorie zurück
	 */
	public String getZimmerKategorie(int nummer) {
		return " ";
	}

	/**
	 * Fügt ein neues Zimmer hinzu, sollte Exception
	 * werfen, falls Kategorie gewollte Kategorie nicht
	 * gefunden wird
	 */
	public void addZimmer(int nummer, String kategorie) {
		Kategorie kat = null;
		
		for (Kategorie k : kategorien) {
			if (k.getName().equals(kategorie))
				kat = k;
		}
		Zimmer room =  new Zimmer(nummer, kat);
		this.zimmer.add(room);
	}
	
	/**
	 * ändert die Kategorie eines bestehenden Zimmers
	 */
	public void updateZimmerKategorie(int nummer, String kategorie) {
		
	}
	
	/**
	 * löscht ein bestehendes Zimmer
	 */
	public void deleteZimmer(int nummer) {
		
	}
}
