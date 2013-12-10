package hm.dao;

import java.util.ArrayList;

import hm.Hotel;
import hm.Kategorie;

public interface DAO {
	public ArrayList<Hotel> getHotellist();
	
	public ArrayList<Kategorie> getKatList();

	public Kategorie getKatByName();
	
	public Hotel getHotelByName(String name);
	
	public void saveKategorie(Kategorie kat);
	
	public void saveHotel(Hotel hotel);
}
