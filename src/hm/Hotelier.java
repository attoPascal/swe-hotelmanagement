package hm;

public class Hotelier extends AbstractUser{

	//jeder hotelier ein hotel??? 
	//Hotel hotel = new hotel; TODO
	
	private Hotel xyz = new Hotel();
	
	public void addKategorie(String name, int preis) {
		xyz.addKategorie(name, preis);
	}
	
	public void addZimmer(int nummer, String name) {
		
	}
	
	public void deleteKategorie(String name) {
		xyz.deleteKategorie(name);
	}
	
	public void deleteZimmer(int nummer, String name) {
		
	}
	
	
	
	public void setZimmer(int nummer, String name) {
		
	}
	
	
	
}
