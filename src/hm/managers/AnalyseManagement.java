/**
 * 
 */
package hm.managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import hm.Aufenthalt;
import hm.Buchung;
import hm.Hotel;
import hm.Kategorie;
import hm.Service;
import hm.Zimmer;
import hm.dao.DAO;
import hm.dao.SerializedDAO;



public class AnalyseManagement {

	private DAO dao;
	
	
	/**
	 * 
	 */
	public AnalyseManagement() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Instanziiert das DAO
	 * @param filename Datei-Name in dem die Daten gespeichert werden
	 * @throws IOException
	 */
	public void instantiateDAO(String filename) throws IOException {
		dao = SerializedDAO.getInstance();
	}
	
	
	/**
	 * Gibt das DAO das verwendet wird um die Daten zu speichern
	 * @return DAO das verwendet wird um die Daten zu speichern
	 */
	public DAO getDAO(){
		return dao;
	}
	
	
	/**
	 * Gibt die Anzahl der Zimmer in einem Hotel zurück
	 * @param hotel
	 * @return
	 */
	public int getNumberOfRooms(String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		return dao.getHotelByName(name).getZimmerList().size();
	}
	
	/**
	 * Gibt einem eine Liste mit gebuchten Zimmern in einer gewissen Zeitspanne zur�ck
	 * 
	 * @param hotel
	 * @param aufenthalt
	 * @return
	 */
	public ArrayList<Zimmer> getBookedRooms(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		
		ArrayList<Zimmer> zlist = dao.getHotelByName(name).getZimmerList();
		
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
	public ArrayList<Buchung> getBookings(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{

		ArrayList<Zimmer> zlist = getBookedRooms(aufenthalt, name);
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
	public int getNumberOfBookings(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		int buchungen = 0;
	
		buchungen = getBookings(aufenthalt, name).size();
		
		return buchungen;
	}
	
	/**
	 * Gibt die Eink�nfte eines Hotels �ber einen gewissen Zeitraum zur�ck
	 * @param hotel
	 * @param aufenthalt
	 * @return
	 */
	public int getTotalRevenue(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		int revenue = 0;
		
		ArrayList<Buchung> buchungen = getBookings(aufenthalt, name);
		
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
	public int getPossibleBookings(Aufenthalt aufenthalt, int dauer, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		int gebucht = 0;
		ArrayList<Buchung> blist = getBookings(aufenthalt, name);
		
		for(Buchung buchung : blist){
			
			gebucht += buchung.getAufenthalt().getDays();
			
		}
		
		int days = getNumberOfRooms(name) * aufenthalt.getDays();
		
		return (days-gebucht)/dauer;
	}
	
	/**
	 * Berechnet den Durchschnittspreis für Zimmer in einem Hotel
	 * @param hotel Das Hotel für die der Durschnittspreis berechnet werden soll
	 * @return Durchschnittspreis der Zimmer
	 */
	public int getAverageRoomPrice(String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		//TODO Preis zu einem gewissen Zeitraum
		int preis = 0;
		ArrayList<Kategorie> kategorien = dao.getHotelByName(name).getKategorien();
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
	public int getAverageServicePrice(String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		//TODO Preis zu einem gewissen Zeitraum
		int preis = 0;
		ArrayList<Service> services = dao.getHotelByName(name).getServiceList();
		for (Service service : services){
			
			preis += service.getPreis();
			}
		return preis/services.size();
		}
	
	/**
	 * @param aufenthalt
	 * @param name
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public String getMostBookedMonth(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		ArrayList<Buchung> buchungen = getBookings(aufenthalt, name);
		
		
		return null;
		}
	
	/**
	 * @param hotel
	 * @param aufenthalt
	 * @return
	 */
	public String getBestCategory(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		return null;
		}
	
	/**
	 * @param hotel
	 * @param aufenthalt
	 * @return
	 */
	public int getServiceRevenue(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		return 0;
		}
	
	/**
	 * @param hotel
	 * @param aufenthalt
	 * @return
	 */
	public String getBestService(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		return null;
		}

}
