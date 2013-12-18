package hm.dao;

import hm.Hotel;
import hm.users.AbstractUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * DAO-Implementierung, die Serialisierung zur persistenten Datenspeicherung
 * benutzt
 */
public class SerializedDAO implements DAO {
	private static SerializedDAO instance = null;
	/**
	 * GetInstance-Methode laut Singleton Pattern
	 * @return SerializedDAO über Datei "data.ser"
	 * @throws IOException Bei Fehlern im Dateizugriff
	 */
	public static SerializedDAO getInstance() throws IOException {
		if (instance == null) {
			try {
				instance = new SerializedDAO("data.ser");
			} catch (IOException e) {
				throw new IOException("Es gab ein Problem beim Zugriff auf die Datenbank (SerializedDAO.getInstance");
			}
		}
		
		return instance;
	}
	
	private File file;
	
	/**
	 * Privater Konstruktor laut Singleton Pattern
	 * @param filename Dateiname der Serialisierungsdatei
	 * @throws IOException Bei Fehlern im Dateizugriff
	 */
	private SerializedDAO(String filename) throws IOException {

		try {
			file = new File(filename);

			if (!file.exists()) {
				file.createNewFile();
				this.saveMap(new HashMap<String, ArrayList<?>>());
			}

		} catch (IOException e) {
			throw new IOException("Es gab ein Problem beim Zugriff auf die Datenbank (SerializedDAO.constructor");
		}
	}

	@Override
	public Hotel getHotelByName(String name) throws FileNotFoundException,
			IOException, ClassNotFoundException {

		try {
			ArrayList<Hotel> list = this.getHotelList();

			for (Hotel hotel : list)
				if (hotel.getName().equals(name))
					return hotel;

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					"Derzeit sind keine Hotels gespeichert (SerializedDAO.getHotelByName())");

		} catch (IOException e) {
			throw new IOException(
					"Es gab ein Problem beim Zugriff auf die Datenbank (SerializedDAO.getHotelByName())");

		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException(
					"Die angeforderte Klasse wurde nicht gefunden (SerializedDAO.getHotelByName())");
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Hotel> getHotelList() throws FileNotFoundException,
			IOException, ClassNotFoundException {
		ArrayList<Hotel> list = null;
		HashMap<String, ArrayList<?>> map = null;

		try {
			map = this.getMap();
			list = (ArrayList<Hotel>) map.get("hotels");

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					"Derzeit sind keine Hotels gespeichert (SerializedDAO.getHotelList())");

		} catch (IOException e) {
			throw new IOException(
					"Es gab ein Problem beim Zugriff auf die Datenbank (SerializedDAO.getHotelList())");

		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException(
					"Die angeforderte Klasse wurde nicht gefunden (SerializedDAO.getHotelList())");
		}

		return (list != null) ? list : new ArrayList<Hotel>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AbstractUser> getUserList() throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<AbstractUser> list = null;
		HashMap<String, ArrayList<?>> map = null;

		try {
			map = this.getMap();
			list = (ArrayList<AbstractUser>) map.get("users");

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					"Derzeit sind keine Hotels gespeichert (SerializedDAO.getHotelList())");

		} catch (IOException e) {
			throw new IOException(
					"Es gab ein Problem beim Zugriff auf die Datenbank (SerializedDAO.getHotelList())");

		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException(
					"Die angeforderte Klasse wurde nicht gefunden (SerializedDAO.getHotelList())");
		}

		return (list != null) ? list : new ArrayList<AbstractUser>();
	}

	@SuppressWarnings("unchecked")
	HashMap<String, ArrayList<?>> getMap() throws FileNotFoundException, IOException, ClassNotFoundException {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			Object o = ois.readObject();

			if (o instanceof HashMap<?, ?>) {
				return (HashMap<String, ArrayList<?>>) o;
			}

		} catch (FileNotFoundException e) {
			//keine Datei vorhanden: neue leere HashMap zurückliefern
			HashMap<String, ArrayList<?>> map = new HashMap<String, ArrayList<?>>();
			map.put("hotels", new ArrayList<Hotel>());
			map.put("user", new ArrayList<AbstractUser>());
			return map;

		} catch (IOException e) {
			throw new IOException(
					"Es gab ein Problem beim Zugriff auf die Datenbank (SerializedDAO.getMap())", e);

		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException(
					"Die angeforderte Klasse wurde nicht gefunden (SerializedDAO.getMap())");
		}

		return null;
	}

	@Override
	public void saveHotel(Hotel hotel) throws FileNotFoundException,
			IOException, ClassNotFoundException {

		try {
			HashMap<String, ArrayList<?>> map = getMap();
			
			if (map == null) System.out.println("map is null");
			
			ArrayList<Hotel> list = this.getHotelList();
			
			if (list == null) System.out.println("list is null");
			
			// altes Hotel aus Liste entfernen
			Iterator<Hotel> it = list.iterator();

			while (it.hasNext()) {
				Hotel h = it.next();
				if (h.getName().equals(hotel.getName()))
					it.remove();
			}
			// neues Hotel zu Liste hinzufuegen
			list.add(hotel);

			map.put("hotels", list);
			this.saveMap(map);

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					"Derzeit sind keine Hotels gespeichert (SerializedDAO.saveHotel(Hotel hotel))");

		} catch (IOException e) {
			throw new IOException(
					"Es gab ein Problem beim Zugriff auf die Datenbank (SerializedDAO.saveHotel(Hotel hotel))");

		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException(
					"Die angeforderte Klasse wurde nicht gefunden (SerializedDAO.saveHotel(Hotel hotel))");
		}
	}

	/**
	 * Speichern in File
	 * @param map HashMap, die in Datei gespeichert wird
	 */
	void saveMap(HashMap<String, ArrayList<?>> map)
			throws FileNotFoundException, IOException {

		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(file));
			oos.writeObject(map);
			oos.close();

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					"Derzeit sind keine Hotels gespeichert (SerializedDAO.saveMap()");

		} catch (IOException e) {
			throw new IOException(
					"Es gab ein Problem beim Zugriff auf die Datenbank (SerializedDAO.saveMap())");
		}
	}

	@Override
	public void saveUser(AbstractUser user) throws FileNotFoundException, IOException, ClassNotFoundException {
		try {
			HashMap<String, ArrayList<?>> map = getMap();
			ArrayList<AbstractUser> list = this.getUserList();
			
			// altes Hotel aus Liste entfernen
			Iterator<AbstractUser> it = list.iterator();
			while (it.hasNext()) {
				AbstractUser u = it.next();
				if (u.getUsername().equals(user.getUsername())) {
					it.remove();
				}
			}
			
			// neues Hotel zu Liste hinzufuegen
			list.add(user);

			map.put("users", list);
			this.saveMap(map);

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Derzeit sind keine User gespeichert (SerializedDAO.saveUser)");

		} catch (IOException e) {
			throw new IOException("Es gab ein Problem beim Zugriff auf die Datenbank (SerializedDAO.saveUser)");

		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Die angeforderte Klasse wurde nicht gefunden (SerializedDAO.saveUser)");
		}
	}
}
