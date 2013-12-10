package hm.dao;

import java.util.ArrayList;

import hm.Hotel;

public interface DAO {
	
	/**
	 * @return Arraylist, in der die Hotels gespeichert werden
	 */
	public ArrayList<Hotel> getHotelList();
	
	/**
	 * Sucht ein Hotel anhand des Namens
	 * @param name des Hotels
	 * @return true, wenn Hotel gefunden wird
	 */
	public Hotel getHotelByName(String name);
	
	/**
	 * @param hotel Hotel-Objekt, das gespeichert wird
	 */
	public void saveHotel(Hotel hotel);
	
	/*
	public ArrayList<Kategorie> getKatList();

	public Kategorie getKatByName();
	
	public void saveKategorie(Kategorie kat);
	*/
}
