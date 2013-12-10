package hm.users;

import hm.Hotel;

import java.util.ArrayList;

public class Hotelier extends AbstractUser{

	private boolean  canCreateCategory, canEditCategory,canRemoveCategory,canCreateRoom,canDeleteRoom;

	private ArrayList<Hotel> hotels = new ArrayList<Hotel>();

	public Hotelier(boolean canCreateCategory, boolean canEditCategory,
			boolean canRemoveCategory, boolean canCreateRoom,
			boolean canDeleteRoom) {
		super();
		this.canCreateCategory = canCreateCategory;
		this.canEditCategory = canEditCategory;
		this.canRemoveCategory = canRemoveCategory;
		this.canCreateRoom = canCreateRoom;
		this.canDeleteRoom = canDeleteRoom;
		hotels.add(new Hotel());
	}
	
	/**
	 * @param hotel Hotel, das dem Hotelier-Objekt hinzugefuegt wird
	 */
	public void addHotel(Hotel hotel){	
		hotels.add(hotel);
	}
	
	/**
	 * @param hotel Hotel, das entfernt wird
	 */
	public void removeHotel(Hotel hotel){	
		hotels.remove(hotel);	
	}
	
	/**
	 * @return Gibt ArrayList an Hotels zurueck, die dem Hotelier zugewiesen sind
	 */
	public ArrayList<Hotel> getHotels() {
		return hotels;
	}

	/**
	 * @param hotels
	 */
	public void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = hotels;
	}

	/**
	 * @return Berechtigung, Kategorien zu erstellen
	 */
	public boolean isCanCreateCategory() {
		return canCreateCategory;
	}

	/**
	 * @param canCreateCategory this
	 */
	public void setCanCreateCategory(boolean canCreateCategory) {
		this.canCreateCategory = canCreateCategory;
	}

	/**
	 * @return Berechtigung, Kategorien zu veraendern
	 */
	public boolean isCanEditCategory() {
		return canEditCategory;
	}

	/**
	 * @param canEditCategory this
	 */
	public void setCanEditCategory(boolean canEditCategory) {
		this.canEditCategory = canEditCategory;
	}

	/**
	 * @return Berechtigung, Kategorien zu loeschen
	 */
	public boolean isCanRemoveCategory() {
		return canRemoveCategory;
	}

	/**
	 * @param canRemoveCategory this
	 */
	public void setCanRemoveCategory(boolean canRemoveCategory) {
		this.canRemoveCategory = canRemoveCategory;
	}

	/**
	 * @return Berechtigung, Zimmer zu erstellen
	 */
	public boolean isCanCreateRoom() {
		return canCreateRoom;
	}

	/**
	 * @param canCreateRoom this
	 */
	public void setCanCreateRoom(boolean canCreateRoom) {
		this.canCreateRoom = canCreateRoom;
	}

	/**
	 * @return Berechtigung, Zimmer zu loeschen
	 */
	public boolean isCanDeleteRoom() {
		return canDeleteRoom;
	}

	/**
	 * @param canDeleteRoom this
	 */
	public void setCanDeleteRoom(boolean canDeleteRoom) {
		this.canDeleteRoom = canDeleteRoom;
	}

	
}
