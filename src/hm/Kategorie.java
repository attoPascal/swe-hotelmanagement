package hm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Kategorie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name der Kategorie
	 */
	private String name;

	/**
	 * Preis pro Nacht in cent
	 */
	private int preis;
	
	private String ausstattung;

	/**
	 * Liste von Zimmern dieser Kategorie
	 */
	HashMap<Integer, Zimmer> zimmerMap = new HashMap<Integer, Zimmer>();

	public Kategorie(String name, int preis, String ausstattung) {
		this.name = name;
		this.preis = preis;
		this.ausstattung = ausstattung;
	}

	/**
	 * @return preis gibt Preis der Kategorie zurueck (pro Nacht in Cent)
	 */
	public int getPreis() {
		return preis;
	}

	/**
	 * @param preis
	 */
	public void setPreis(int preis) {
		this.preis = preis;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAusstattung() {
		return this.ausstattung;
	}
	
	public void setAusstattung(String ausstattung) {
		this.ausstattung = ausstattung;
	}

	public HashMap<Integer, Zimmer> getZimmerMap() {
		return zimmerMap;
	}

	// TODO void???
	public void setZimmerMap(HashMap<Integer, Zimmer> zimmerMap) {
		this.zimmerMap = zimmerMap;
	}

	// TODO void???
	/**
	 * @param zimmer Zimmer-objekt, das der Kategorie hinzugefuegt wird
	 */
	public void addZimmer(Zimmer zimmer) {
		zimmerMap.put(zimmer.getNummer(), zimmer);
	}

	/**
	 * Ueberprueft, ob eine Kategorie ueber ein Zimmer verfuegt
	 * @param nummer Zur identifizierung des Zimmers
	 * @return true, wenn die zimmerMap das Zimmer enthaelt; sonst false
	 */
	public boolean hasZimmer(int nummer) {
		return zimmerMap.containsKey(nummer) ? true : false;
	}

	// TODO void?
	/**
	 * Entfernt Zimmer aus Kategorie
	 * @param nummer Zur identifizierung des Zimmers
	 */
	public void removeZimmer(int nummer) {
		zimmerMap.remove(nummer);
	}

	/**
	 * Sucht ein Zimmer, das zu einem gegebenen Zeitraum(Aufenthalt) nicht besetzt ist(mit getBuchungen())
	 * @param aufenthalt Zeitraum der Buchung fuer das Zimmer
	 * @return Ein freies Zimmer 
	 */
	public Zimmer getZimmer(Aufenthalt aufenthalt) {
		Zimmer zimmer;
		Iterator<Entry<Integer, Zimmer>> it = zimmerMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Zimmer> zimmers = (Map.Entry<Integer, Zimmer>) it.next();// TODO variable name
			zimmer = (Zimmer) zimmers.getValue();
			
			ArrayList<Buchung> buchungen = zimmer.getBuchungen();
			
			for(Buchung buchung : buchungen){
				
				buchung.getAufenthalt().toString();
				
			}
			
			if (!Zimmer.isBooked(zimmer, aufenthalt)) {
				return zimmer;
			}
		}
		//TODO nullPointerException
		return null;
	}

	/**
	 * gibt in einem String die Kategorie mit Name, Preis und Ausstattung zurueck
	 */
	public String toString(){
		
		String s = "<br>" + "Name: " + this.getName() + "<br>"+ "Preis pro Nacht: " + this.getPreis() + "<br>" + "Ausstattung: "+this.getAusstattung() ;

		return s;
	}

}

