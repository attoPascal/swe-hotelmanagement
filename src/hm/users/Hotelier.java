package hm.users;

import hm.Hotel;

import java.util.ArrayList;

/**
 * Benutzer Hotelier, kann seine zugeordneten Hotels verwalten
 */
public class Hotelier extends AbstractUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean canManageCategories;
	private boolean canManageRooms;
	/**
	 * Jeder Hotelier kann grundsätzlich Buchungen managen
	 */
	private boolean canManageBookings = true;

	private ArrayList<String> hotels = new ArrayList<String>();

	public Hotelier(String username, String password, boolean canManageCategories, boolean canManageRooms) {
		super(username, password);
		this.canManageCategories = canManageCategories;
		this.canManageRooms = canManageRooms;
	}
	
	/**
	 * @param hotel Hotel, das dem Hotelier-Objekt hinzugefuegt wird
	 */
	public void addHotel(Hotel hotel){	
		hotels.add(hotel.getName());
	}
	
	/**
	 * @param hotel Hotel, das entfernt wird
	 */
	public void removeHotel(Hotel hotel){	
		hotels.remove(hotel.getName());	
	}
	
	/**
	 * @return Gibt ArrayList an Hotels zurueck, die dem Hotelier zugewiesen sind
	 */
	public ArrayList<String> getHotels() {
		return hotels;
	}

	/**
	 * @param hotels
	 */
	public void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = new ArrayList<String>();
		
		for (Hotel h : hotels) {
			this.hotels.add(h.getName());
		}
	}

	/**
	 * @return Berechtigung, Kategorien zu verwalten
	 */
	public boolean isCanManageCategories() {
		return canManageCategories;
	}

	/**
	 * @param canCreateCategory this
	 */
	public void setCanManageCategories(boolean canManageCategories) {
		this.canManageCategories = canManageCategories;
	}
	
	/**
	 * @return Berechtigung, Räume zu verwalten
	 */
	public boolean isCanManageRooms() {
		return canManageRooms;
	}

	/**
	 * @param canCreateCategory this
	 */
	public void setCanManageRooms(boolean canManageRooms) {
		this.canManageRooms = canManageRooms;
	}
	
	public boolean isCanManageBookings() {
		return canManageBookings;
	}
	
	public void setCanManageBookings(boolean canManageBookings) {
		this.canManageBookings = canManageBookings;
	}
}