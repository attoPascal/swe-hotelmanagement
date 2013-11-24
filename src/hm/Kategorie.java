package hm;

import java.util.HashMap;

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
	
	public Kategorie(String name, int preis){
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<Integer, Zimmer> getZimmerList() {
		return zimmerMap;
	}

	public void setZimmerMap(HashMap<Integer, Zimmer> zimmerMap) {
		this.zimmerMap = zimmerMap;
	}
	
}
