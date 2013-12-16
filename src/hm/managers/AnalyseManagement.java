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

	
	
	public int getNumberOfRooms(Hotel hotel){
		return hotel.getZimmerList().size();
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
	
	/**
	 * Gibt eine ArrayListe mit allen Buchungen zu einem gewissen Zeitraum in einem Hotel zurück
	 * 
	 * @param hotel
	 * @param aufenthalt
	 * @return
	 */
	public ArrayList<Buchung> getBookings(Hotel hotel, Aufenthalt aufenthalt){

		ArrayList<Zimmer> zlist = getBookedRooms(hotel, aufenthalt);
		ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
		for (Zimmer zimmer : zlist){
			
			buchungen.addAll(zimmer.getBuchungen());
			
		}
		
		return buchungen;
	}
	
	/**
	 * Gibt die Anzahl der Buchungen zu einem gewissen Zeitraum in einem Hotel zurück
	 * 
	 * @param hotel
	 * @param aufenthalt
	 * @return
	 */
	public int getNumberOfBookings(Hotel hotel, Aufenthalt aufenthalt){
		int buchungen = 0;
	
		buchungen = getBookings(hotel, aufenthalt).size();
		
		return buchungen;
	}
	
	/**
	 * Gibt die Einkünfte eines Hotels über einen gewissen Zeitraum zurück
	 * @param hotel
	 * @param aufenthalt
	 * @return
	 */
	public int getTotalRevenue(Hotel hotel, Aufenthalt aufenthalt){
		int revenue = 0;
		
		ArrayList<Buchung> buchungen = getBookings(hotel, aufenthalt);
		
		for (Buchung buchung : buchungen){
			
			revenue += buchung.getKosten();
			
		}
		
		return revenue;
	}
	
	/**
	 * Gibt die Anzahl an möglichen Buchungen einer gewissen Dauer zu einem gewissen Zeitpunkt für ein Hotel zurück
	 * 
	 * @param hotel
	 * @param aufenthalt
	 * @param dauer
	 * @return
	 */
	public int getPossibleBookings(Hotel hotel, Aufenthalt aufenthalt, int dauer){
		int gebucht = 0;
		ArrayList<Buchung> blist = getBookings(hotel, aufenthalt);
		
		for(Buchung buchung : blist){
			
			gebucht += buchung.getAufenthalt().getDays();
			
		}
		
		int days = getNumberOfRooms(hotel) * aufenthalt.getDays();
		
		return (days-gebucht)/dauer;
	}
}
