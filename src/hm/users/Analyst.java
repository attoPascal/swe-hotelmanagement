/**
 * 
 */
package hm.users;

/**
 * @author 
 *
 */
public class Analyst extends AbstractUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3126007390053181157L;

	private boolean canViewRevenue;
	private boolean canViewBookings;
	private boolean canViewRooms;
	/**
	 * 
	 */
	public Analyst(String username, String password, boolean canViewRevenue,
			boolean canViewBookings, boolean canViewRooms) {
		super(username, password);
		this.canViewRevenue = canViewRevenue;
		this.canViewBookings = canViewBookings;
		this.canViewRooms = canViewRooms;
	}

	/**
	 * @return the canViewRevenue
	 */
	public boolean isCanViewRevenue() {
		return canViewRevenue;
	}
	/**
	 * @param canViewRevenue the canViewRevenue to set
	 */
	public void setCanViewRevenue(boolean canViewRevenue) {
		this.canViewRevenue = canViewRevenue;
	}
	/**
	 * @return the canViewBookings
	 */
	public boolean isCanViewBookings() {
		return canViewBookings;
	}
	/**
	 * @param canViewBookings the canViewBookings to set
	 */
	public void setCanViewBookings(boolean canViewBookings) {
		this.canViewBookings = canViewBookings;
	}
	/**
	 * @return the canViewRooms
	 */
	public boolean isCanViewRooms() {
		return canViewRooms;
	}
	/**
	 * @param canViewRooms the canViewRooms to set
	 */
	public void setCanViewRooms(boolean canViewRooms) {
		this.canViewRooms = canViewRooms;
	}

	public boolean hasNoRights(){
		if (canViewBookings || canViewRooms || canViewRevenue) return false;
		else return true;
	}
}
