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
	 * @return preis
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
	public void addZimmer(Zimmer zimmer) {
		zimmerMap.put(zimmer.getNummer(), zimmer);
	}

	public boolean hasZimmer(int nummer) {
		return zimmerMap.containsKey(nummer) ? true : false;
	}

	// TODO void?
	public void removeZimmer(int nummer) {
		zimmerMap.remove(nummer);
	}

	public Zimmer getZimmer(Aufenthalt aufenthalt) {
		Zimmer zimmer;
		Iterator<Entry<Integer, Zimmer>> it = zimmerMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Zimmer> zimmers = (Map.Entry<Integer, Zimmer>) it.next();// TODO variable name
			zimmer = (Zimmer) zimmers.getValue();
			it.remove();
			
			ArrayList<Buchung> buchungen = zimmer.getBuchungen();
			
			for(Buchung buchung : buchungen){
				
				buchung.getAufenthalt().toString();
				
			}
			
			/* wirklich?? */
			if (!Zimmer.isBooked(zimmer, aufenthalt)) {
				return zimmer;
			}
		}

		return null;
	}

	public String toString(){
		
		String s = "<br>" + "Name: " + this.getName() + "<br>"+ "Preis: " + this.getPreis() + "<br>" + "Ausstattung: "+this.getAusstattung() ;

		return s;
	}

}

