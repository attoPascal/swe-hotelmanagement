package hm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Kategorie {

	/**
	 * Name der Kategorie
	 */
	private String name;

	/**
	 * Preis pro Nacht in cent
	 */
	private int preis;
	
	/**
	 * Liste von Zimmern dieser Kategorie
	 */
	HashMap<Integer, Zimmer> zimmerMap = new HashMap<Integer, Zimmer>();
	
	Kategorie(String name, int preis){
		
		this.name = name;
		this.preis = preis;
		
	}
	
	
	/**
	 * @return
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


	public HashMap<Integer, Zimmer> getZimmerMap() {
		return zimmerMap;
	}

	//TODO void???
	public void setZimmerMap(HashMap<Integer, Zimmer> zimmerMap) {
		this.zimmerMap = zimmerMap;
	}

	//TODO void???
	public void addZimmer(Zimmer zimmer){
		
		zimmerMap.put(zimmer.getNummer(), zimmer);
		
	}
	
	public boolean hasZimmer(int nummer){
		
		return zimmerMap.containsKey(nummer) ? true : false;
		
	}
	
	//TODO void?
	public void removeZimmer(int nummer){
		
		zimmerMap.remove(nummer);
		
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
