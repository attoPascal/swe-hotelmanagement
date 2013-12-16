/**
 * 
 */
package hm.managers;

import java.util.ArrayList;

import hm.Aufenthalt;
import hm.Buchung;
import hm.Hotel;
import hm.Zimmer;

/**
 * @author 
 *
 */
/**
 * @author Elisabeth Reiner
 *
 */
public class AnalyseManagement {

	/**
	 * 
	 */
	public AnalyseManagement() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Gibt einem eine Liste mit gebuchten Zimmern in einer gewissen Zeitspanne zurück
	 * 
	 * @param hotel
	 * @param aufenthalt
	 * @return
	 */
	public ArrayList<Zimmer> getBookedRooms(Hotel hotel, Aufenthalt aufenthalt){
		
		ArrayList<Zimmer> zlist = hotel.getZimmerList();
		
		ArrayList<Zimmer> bzlist = new ArrayList<Zimmer>();
		
		for (Zimmer zimmer : zlist){
			
			if (Zimmer.isBooked(zimmer, aufenthalt)) bzlist.add(zimmer);
			
		}
		
		return bzlist;
	}
	
	/**
	 * Gibt einem eine Liste mit freien Zimmern zu einer gewissen Zeitdauer zurück
	 * 
	 * @param hotel
	 * @param aufenthalt
	 * @return
	 */
	public ArrayList<Zimmer> getFreeRooms(Hotel hotel, Aufenthalt aufenthalt){
		
		ArrayList<Zimmer> zlist = hotel.getZimmerList();
		
		ArrayList<Zimmer> fzlist = new ArrayList<Zimmer>();
		
		for (Zimmer zimmer : zlist){
			
			if (!Zimmer.isBooked(zimmer, aufenthalt)) fzlist.add(zimmer);
			
		}
		
		return fzlist;
	}
	
	public ArrayList<Buchung> getBookings(Hotel hotel, Aufenthalt aufenthalt){

		ArrayList<Zimmer> zlist = getBookedRooms(hotel, aufenthalt);
		ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
		for (Zimmer zimmer : zlist){
			
			buchungen.addAll(zimmer.getBuchungen());
			
		}
		
		return buchungen;
	}
	
	public int getNumberOfBookings(Hotel hotel, Aufenthalt aufenthalt){
		int buchungen = 0;
	
		buchungen = getBookings(hotel, aufenthalt).size();
		
		return buchungen;
	}
	
}
