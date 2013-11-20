package hm;

import java.util.ArrayList;

public class Hotel {


	/**
	 * @uml.property  name="kategorien"
	 */
	ArrayList<Kategorie> kategorien = new ArrayList<Kategorie> ();
	
	/**
	 * @uml.property  name="zimmer"
	 */
	ArrayList<Zimmer> zimmer = new ArrayList<Zimmer>();

	public ArrayList<Kategorie> getKategorien() {
		return kategorien;
	}

	public void setKategorien(ArrayList<Kategorie> kategorien) {
		this.kategorien = kategorien;
	}

	public ArrayList<Zimmer> getZimmer() {
		return zimmer;
	}

	public void setZimmer(ArrayList<Zimmer> zimmer) {
		this.zimmer = zimmer;
	}
	
	
	
}
