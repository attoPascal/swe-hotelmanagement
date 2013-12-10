/**
 * "Model"-Klasse
 */
package hm.dao;

import hm.Hotel;
import hm.Kategorie;

import java.util.*;

/**
 * @author philipp
 *
 */
public class ManagementDAO implements DAO {
	
	public ManagementDAO() {
		
	}
	
	/**
	 * ArrayList, die die Hotels enthaelt
	 * @return null
	 */
	public ArrayList<Hotel> getHotelList() {
		return null;
	}
	
	/**
	 * ArrayList, die die Kategorien enthaelt
	 * @return null
	 */
	public ArrayList<Kategorie> getKatList() {
		return null;
	}
	
	/**
	 * Laden der kategorie aus einer Datei
	 */
	public Kategorie getKatByName() {
		return null;
	}
	
	/**
	 * Laden des Hotels aus einer Datei
	 */
	public Hotel getHotelByName(String name) {
		return null;
	}
	
	/**
	 * Speichern der kategorie in einer Datei
	 */
	public void saveKategorie(Kategorie kat) {
		
	}
	
	/**
	 * Speichern des Hotels in einer Datei
	 */
	public void saveHotel(Hotel hotel) {
		
	}
}
