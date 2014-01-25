package hm.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import hm.Hotel;
import hm.users.AbstractUser;

/**
 * Generisches Interface zur konsistenten Datenspeicherung
 */
public interface DAO {
	
	/**
	 * Sucht ein Hotel anhand des Namens
	 * @param Name des Hotels
	 * @return das Hotel, wenn es gefunden wurde
	 */
	public Hotel getHotelByName(String name) throws FileNotFoundException, IOException, ClassNotFoundException;
	
	/**
	 * Gibt alle Hotels aus der konstistenten Datenspeicherung in Form einer ArrayList zurück
	 * @return ArrayList mit allen Hotels
	 */
	public ArrayList<Hotel> getHotelList() throws FileNotFoundException, IOException, ClassNotFoundException;
	
	/**
	 * Gibt alle User aus der konstistenten Datenspeicherung in Form einer ArrayList zurück
	 * @return ArrayList mit allen Usern
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	ArrayList<AbstractUser> getUserList() throws FileNotFoundException, IOException, ClassNotFoundException;
	
	/**
	 * Speichert ein Hotel
	 * @param hotel Hotel-Objekt, das gespeichert wird
	 */
	public void saveHotel(Hotel hotel) throws FileNotFoundException, IOException, ClassNotFoundException;
	
	/**
	 * Speichert einen User 
	 * @param user AbstractUser-Object, das gespeichert wird
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void saveUser(AbstractUser user) throws FileNotFoundException, IOException, ClassNotFoundException;
	
	/**
	 * Liefert fortlaufende BuchungsID
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	public int getNextBuchungsID() throws FileNotFoundException, ClassNotFoundException, IOException;
}
