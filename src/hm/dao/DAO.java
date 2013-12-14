package hm.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import hm.Hotel;

/**
 * Generisches Interface zur konsistenten Datenspeicherung
 */
public interface DAO {
	
	/**
	 * @return Arraylist, in der die Hotels gespeichert werden
	 */
	public ArrayList<Hotel> getHotelList() throws FileNotFoundException, IOException, ClassNotFoundException;
	
	/**
	 * Sucht ein Hotel anhand des Namens
	 * @param name des Hotels
	 * @return true, wenn Hotel gefunden wird
	 */
	public Hotel getHotelByName(String name) throws FileNotFoundException, IOException, ClassNotFoundException;
	
	/**
	 * @param hotel Hotel-Objekt, das gespeichert wird
	 */
	public void saveHotel(Hotel hotel) throws FileNotFoundException, IOException, ClassNotFoundException;
	
	/*
	public ArrayList<Kategorie> getKatList();

	public Kategorie getKatByName();
	
	public void saveKategorie(Kategorie kat);
	*/
}
