package hm;

import java.util.ArrayList;

public class Hotel {


	/**
	 */
	ArrayList<Kategorie> kategorien = new ArrayList<Kategorie> ();
	
	/**
	 */
	ArrayList<Zimmer> zimmerList = new ArrayList<Zimmer>();
	
	

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
	
	public void addZimmer(Zimmer zimmer){
		
		zimmerList.add(zimmer);
		
	}
	
	public void removeZimmer(Zimmer zimmer){
		
		zimmerList.remove(zimmer);
		
	}

	public void addKategorie(Kategorie kategorie) {

		kategorien.add(kategorie);
		
	}
	
	public void removeKategorie(Kategorie kategorie){
		
		kategorien.remove(kategorie);
		
	}
	
	public Kategorie getKategorie(String name){
		
		for (Kategorie kategorie : kategorien){
			if (kategorie.getName().equals(name)) return kategorie;
		}
		
		return null;
		
	}

	public Zimmer getZimmer(int nummer) {
		
		for (Zimmer zimmer: zimmerList){
			if (zimmer.getNummer() == nummer) return zimmer;
		}
		return null;
	}
	
	
	
}
