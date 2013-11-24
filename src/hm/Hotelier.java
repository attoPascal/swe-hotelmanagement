package hm;

import java.util.ArrayList;

public class Hotelier extends AbstractUser{

	private ArrayList<Hotel> jaws = new ArrayList<Hotel>();
	
	public Hotelier() {
		
	}
	
	public void addHotel(String name, int id) {
		Hotel hotel = new Hotel(name, id);
		jaws.add(hotel);
	}
	
	public void deleteHotel(int id) {
		
		for(Hotel h : jaws) {
			if(h.getID() == id) {
				jaws.remove(h);
				break;
			}
		}
		
	}
	
	public void addKategorie(String name, int preis) {
		jaws.get(0).addKategorie(name, preis);
	}
	
	public void addZimmer(int nummer, String katName) {
		jaws.get(0).addZimmer(nummer, katName);
	}
	
	public void deleteKategorie(String name) {
		jaws.get(0).deleteKategorie(name);
	}
	
	public void deleteZimmer(int nummer) {
		jaws.get(0).deleteZimmer(nummer);
	}
	
	public void updateZimmer(int nummer, String katName) {
		jaws.get(0).updateZimmerKategorie(nummer, katName);
	}
	
	public void updateKategorie(String name, int preis) {
		jaws.get(0).updateKategorie(name, preis);
	}
	
}
