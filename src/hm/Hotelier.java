package hm;

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
	
	
	public void addHotel(Hotel hotel){
		
		hotels.add(hotel);
		
	}
	
	public void removeHotel(Hotel hotel){
		
		hotels.remove(hotel);
		
	}
	
	
	public ArrayList<Hotel> getHotels() {
		return hotels;
	}



	public void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = hotels;
	}



	public boolean isCanCreateCategory() {
		return canCreateCategory;
	}

	public void setCanCreateCategory(boolean canCreateCategory) {
		this.canCreateCategory = canCreateCategory;
	}

	public boolean isCanEditCategory() {
		return canEditCategory;
	}

	public void setCanEditCategory(boolean canEditCategory) {
		this.canEditCategory = canEditCategory;
	}

	public boolean isCanRemoveCategory() {
		return canRemoveCategory;
	}

	public void setCanRemoveCategory(boolean canRemoveCategory) {
		this.canRemoveCategory = canRemoveCategory;
	}

	public boolean isCanCreateRoom() {
		return canCreateRoom;
	}

	public void setCanCreateRoom(boolean canCreateRoom) {
		this.canCreateRoom = canCreateRoom;
	}

	public boolean isCanDeleteRoom() {
		return canDeleteRoom;
	}

	public void setCanDeleteRoom(boolean canDeleteRoom) {
		this.canDeleteRoom = canDeleteRoom;
	}

	
}
