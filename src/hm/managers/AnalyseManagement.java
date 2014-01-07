/**
 * 
 */
package hm.managers;

import java.util.ArrayList;

import hm.Aufenthalt;
import hm.Buchung;
import hm.Hotel;
import hm.Kategorie;
import hm.Service;
import hm.Zimmer;

/**
 * @author 
 *
 */
/**
 * @author 
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
	 * Gibt einem eine Liste mit gebuchten Zimmern in einer gewissen Zeitspanne zur�ck
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
	 * Gibt einem eine Liste mit freien Zimmern zu einer gewissen Zeitdauer zur�ck
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
	 * Gibt eine ArrayListe mit allen Buchungen zu einem gewissen Zeitraum in einem Hotel zur�ck
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
	 * Gibt die Anzahl der Buchungen zu einem gewissen Zeitraum in einem Hotel zur�ck
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
	 * Gibt die Eink�nfte eines Hotels �ber einen gewissen Zeitraum zur�ck
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
	 * Gibt die Anzahl an m�glichen Buchungen einer gewissen Dauer zu einem gewissen Zeitpunkt f�r ein Hotel zur�ck
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
	
	/**
	 * Berechnet den Durchschnittspreis für Zimmer in einem Hotel
	 * @param hotel Das Hotel für die der Durschnittspreis berechnet werden soll
	 * @return Durchschnittspreis der Zimmer
	 */
	public int getAverageRoomPrice(Hotel hotel){
		//TODO Preis zu einem gewissen Zeitraum
		int preis = 0;
		ArrayList<Kategorie> kategorien = hotel.getKategorien();
		for (Kategorie kategorie : kategorien){
			
			preis += kategorie.getPreis();
			}
		return preis/kategorien.size();
		}
	
	
	/**
	 * Berechnet den Durchschnittspreis für Services in einem bestimmten Hotel
	 * @param hotel Hotel für das die Durchschnittspreise ermittelt werden sollen
	 * @return Durchschnittspreis der Services
	 */
	public int getAverageServicePrice(Hotel hotel){
		//TODO Preis zu einem gewissen Zeitraum
		int preis = 0;
		ArrayList<Service> services = hotel.getServiceList();
		for (Service service : services){
			
			preis += service.getPreis();
			}
		return preis/services.size();
		}
	
	public String getMostBookedMonth(Hotel hotel, Aufenthalt aufenthalt){
		ArrayList<Buchung> buchungen = getBookings(hotel, aufenthalt);
		
		
		return null;
		}
	
	public String getBestCategory(Hotel hotel, Aufenthalt aufenthalt){
		return null;
		}
	
	public int getServiceRevenue(Hotel hotel, Aufenthalt aufenthalt){
		return 0;
		}
	
	public String getBestService(Hotel hotel, Aufenthalt aufenthalt){
		return null;
		}

}
