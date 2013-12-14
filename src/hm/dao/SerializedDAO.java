package hm.dao;

import hm.Hotel;

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
	private File file;

	public SerializedDAO(String filename) throws IOException {

		file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();

			this.saveMap(new HashMap<String, ArrayList<?>>());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Hotel> getHotelList() throws FileNotFoundException,
			IOException, ClassNotFoundException {
		HashMap<String, ArrayList<?>> map = getMap();
		ArrayList<Hotel> list = (ArrayList<Hotel>) map.get("hotels");

		return (list != null) ? list : new ArrayList<Hotel>();
	}

	@Override
	public Hotel getHotelByName(String name) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		ArrayList<Hotel> list = this.getHotelList();

		for (Hotel hotel : list) {
			if (hotel.getName().equals(name)) {
				return hotel;
			}
		}

		return null;
	}

	@Override
	public void saveHotel(Hotel hotel) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		HashMap<String, ArrayList<?>> map = getMap();
		ArrayList<Hotel> list = this.getHotelList();

		// altes Hotel aus Liste entfernen
		Iterator<Hotel> it = list.iterator();
		while (it.hasNext()) {
			Hotel h = it.next();
			if (h.getName().equals(hotel.getName())) {
				it.remove();
			}
		}

		// neues Hotel zu Liste hinzufuegen
		list.add(hotel);

		map.put("hotels", list);
		this.saveMap(map);
	}

	@SuppressWarnings("unchecked")
	HashMap<String, ArrayList<?>> getMap() throws FileNotFoundException,
			IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Object o = ois.readObject();

		if (o instanceof HashMap<?, ?>) {
			ois.close();
			return (HashMap<String, ArrayList<?>>) o;
		}
		ois.close();

		return null;
	}

	/**
	 * Speichern in File
	 * 
	 * @param map
	 *            HashMap, die in Datei gespeichert wird
	 */
	void saveMap(HashMap<String, ArrayList<?>> map) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(file));
			oos.writeObject(map);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
