package hm;

import java.util.ArrayList;

public class Hotel {

	private String name;

	/**
	 * Die Kategorien, die das Hotel anbietet
	 */
	private ArrayList<Kategorie> kategorien = new ArrayList<Kategorie>();

	/**
	 * Liste der Zimmer, die das Hotel zur Verfügung hat
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
	
	public String toString(){
		
		String s;
		
		s = "Hotelname: " + getName();
		
		ArrayList<Kategorie> kategorien = getKategorien();
		
		s = "\nKategorien: ";
		
		for (Kategorie kategorie : kategorien){
			
			s += "\nName: " + kategorie.getName() + "\n" + "Preis: "+kategorie.getPreis() + "Ausstattung: "+kategorie.getAusstattung();
			
		}
		
		return s;
			
	}

}
