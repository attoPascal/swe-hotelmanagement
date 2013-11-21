package hm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Kategorie {

	/**
	 * Name der Kategorie
	 * @uml.property  name="name"
	 */
	private String name;

	/**
	 * Preis pro Nacht in cent
	 * @uml.property  name="preis"
	 */
	private int preis;
	
	/**
	 * Liste von Zimmern dieser Kategorie
	 * @uml.property  name="zimmerList"
	 */
	HashMap<Integer, Zimmer> zimmerMap = new HashMap<Integer, Zimmer>();
	
	Kategorie(String name, int preis){
		
		this.name = name;
		this.preis = preis;
		
	}
	
	
	/**
	 * @return
	 * @uml.property  name="preis"
	 */
	public int getPreis() {
		return preis;
	}
	/**
	 * @param preis
	 * @uml.property  name="preis"
	 */
	public void setPreis(int preis) {
		this.preis = preis;
	}
	
	public String getKategoriename() {
		return name;
	}
	public void setKategoriename(String kategoriename) {
		this.name = kategoriename;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public HashMap<Integer, Zimmer> getZimmerMap() {
		return zimmerMap;
	}


	public void setZimmerMap(HashMap<Integer, Zimmer> zimmerMap) {
		this.zimmerMap = zimmerMap;
	}


	public Zimmer getZimmer(Aufenthalt aufenthalt) {
		Zimmer zimmer;
		 Iterator<Entry<Integer, Zimmer>> it = zimmerMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry zimmers = (Map.Entry)it.next();//TODO variable name
		        zimmer = (Zimmer)zimmers.getValue();
		        it.remove();
		        if (!BuchungsManagement.isBooked(zimmer, aufenthalt)){
		        	return zimmer;
		        }
		    }
		
		return null;
	}
	
}
