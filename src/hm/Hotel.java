package hm;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Zeichnet ein Hotel-Objekt aus. Beinhaltet Zimmer und Kategorien
 */
public class Hotel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	/**
	 * Die Kategorien, die das Hotel anbietet
	 */
	private ArrayList<Kategorie> kategorien = new ArrayList<Kategorie>();

	/**
	 * Liste der Zimmer, die das Hotel zur Verfuegung hat
	 */
	private ArrayList<Zimmer> zimmerList = new ArrayList<Zimmer>();

	public Hotel() {
		super();
		this.name = "default";
	}

	public Hotel(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setZimmerList(ArrayList<Zimmer> zimmerList) {
		this.zimmerList = zimmerList;
	}

	/**
	 * @return gibt eine ArrayList(kategorien) mit allen im Hotel gespeicherten Kategorien zurueck
	 */
	public ArrayList<Kategorie> getKategorien() {
		return kategorien;
	}

	public void setKategorien(ArrayList<Kategorie> kategorien) {
		this.kategorien = kategorien;
	}

	/**
	 * @return gibt eine ArrayList(zimmerList) mit allen im Hotel gespeicherten Zimmern zurueck
	 */
	public ArrayList<Zimmer> getZimmerList() {
		return zimmerList;
	}
	
	public void setZimmer(ArrayList<Zimmer> zimmerList) {
		this.zimmerList = zimmerList;
	}

	/**
	 * @param zimmer Wird dem Hotel-Objekt hinzugefuegt
	 */
	public void addZimmer(Zimmer zimmer) {
		zimmerList.add(zimmer);
	}

	/**
	 * @param zimmer Wird aus dem Hotel-Objekt entfernt
	 */
	public void removeZimmer(Zimmer zimmer) {
		zimmerList.remove(zimmer);
	}

	/**
	 * Fuegt eine Kategorie dem Hotel-Objekt hinzu
	 * @param kategorie die hinzugefuegt wird
	 */
	public void addKategorie(Kategorie kategorie) {
		kategorien.add(kategorie);
	}

	/**
	 * Loescht eine Kategorie des Hotel-Objekts
	 * @param kategorie gesuchte Kategorie
	 */
	public void removeKategorie(Kategorie kategorie) {
		kategorien.remove(kategorie);
	}

	/**
	 * Sucht eine Kategorie des Hotels
	 * @param name gesuchte Kategorie
	 * @return gibt gesuchte kategorie des Hotels zurueck; Null wenn es nicht gefunden wird.
	 */
	public Kategorie getKategorie(String name) throws NullPointerException {

		for (Kategorie kategorie : kategorien) {
			if (kategorie.getName().equals(name))
				return kategorie;
		}

		return null;
	}
	
	/**
	 * Sucht ein Zimmer des Hotels
	 * @param nummer des gesuchten Zimmers
	 * @return gibt gesuchtes Zimmer zurueck, wenn es gefunden wird; sonst null
	 */
	// TODO
	public Zimmer getZimmer(int nummer) {

		for (Zimmer zimmer : zimmerList) {
			if (zimmer.getNummer() == nummer)
				return zimmer;
		}
		return null;
	}
	
	//TODO toHtml?
	public String toString(){
		
		StringBuffer s = new StringBuffer();
		
		s.append("<h1>Hotel '" + getName() + "'</h1>");
		
		for (Kategorie kategorie : this.getKategorien()){
			s.append("<h2>Kategorie '" + kategorie.getName() + "'</h2>");
			s.append("<div>Preis: " + kategorie.getPreis() + "</div>");
			s.append("<div>Ausstattung: " + kategorie.getAusstattung() + "</div>");
			
			s.append("<div>Zimmer: ");
			for (int zimmerNummer : kategorie.getZimmerMap().keySet()) {
				s.append(zimmerNummer + " ");
			}
			s.append("</div>");
		}

		return s.toString();	

	}
}
