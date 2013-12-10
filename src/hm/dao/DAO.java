package hm.dao;

import java.util.ArrayList;

import hm.Hotel;

public interface DAO {
	public ArrayList<Hotel> getHotelList();
	
	public Hotel getHotelByName(String name);
	
	public void saveHotel(Hotel hotel);
	
	/*
	public ArrayList<Kategorie> getKatList();

	public Kategorie getKatByName();
	
	public void saveKategorie(Kategorie kat);
	*/
}
