package hm;

import java.io.Serializable;
import java.util.ArrayList;

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

	public ArrayList<Kategorie> getKategorien() {
		return kategorien;
	}

	public void setKategorien(ArrayList<Kategorie> kategorien) {
		this.kategorien = kategorien;
	}

	public ArrayList<Zimmer> getZimmerList() {
		return zimmerList;
	}
	
	public void setZimmer(ArrayList<Zimmer> zimmerList) {
		this.zimmerList = zimmerList;
	}

	public void addZimmer(Zimmer zimmer) {
		zimmerList.add(zimmer);
	}

	public void removeZimmer(Zimmer zimmer) {
		zimmerList.remove(zimmer);
	}

	public void addKategorie(Kategorie kategorie) {
		kategorien.add(kategorie);
	}

	public void removeKategorie(Kategorie kategorie) {
		kategorien.remove(kategorie);
	}

	public Kategorie getKategorie(String name) {

		for (Kategorie kategorie : kategorien) {
			if (kategorie.getName().equals(name))
				return kategorie;
		}

		return null;
	}
	

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
