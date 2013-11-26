package hm;

public class KategorieManagement {

	public void editCategority(Hotel hotel, String name, String newName, int preis){
		
		Kategorie kategorie = hotel.getKategorie(name);
		
		kategorie.setName(newName);
		kategorie.setPreis(preis);
	}

	public void createCategority(Hotel hotel, String name, int preis){
		hotel.addKategorie(new Kategorie (name, preis));
	}
	
	public void removeKategorie(Hotel hotel, String name){
		Kategorie kategorie = hotel.getKategorie(name);
		hotel.removeKategorie(kategorie);
		
	}

}
