/**
 * 
 */
package hm.managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import hm.Aufenthalt;
import hm.Buchung;
import hm.Hotel;
import hm.Kategorie;
import hm.Service;
import hm.Zimmer;
import hm.dao.DAO;
import hm.dao.SerializedDAO;
import hm.exceptions.ServiceException;
import hm.users.HotelGast;



/**
 * @author vincent
 *Diese Klasse implementiert die notwendige funktionalität für die 
 *Use Cases des Analysten.
 *Es gibt Methoden zur berechnung von Durschnittspreisen,
 * Einkommen, besten Kategorien, Services, Monaten usw.
 */
public class AnalyseManagement { 
	
	/**
	 * Data Access Object mit dem auf die Hoteldaten zugegriffen wird
	 */
	private DAO dao;
	
	
	/**
	 * Instanziiert das DAO
	 * @throws IOException
	 */
	public void instantiateDAO() throws IOException {
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
	 * @param name Name des Hotels für das die Zimmeranzahl berechnet werden soll
	 * @return Anzahl der Zimmer in einem Hotel
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int getNumberOfRooms(String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		return dao.getHotelByName(name).getZimmerList().size();
	}
	
	/**
	 * Gibt einem eine Liste mit gebuchten Zimmern in einer gewissen Zeitspanne zur�ck
	 * @param aufenthalt Zeitspanne für die die gebuchten Zimmer berechnet werden sollen
	 * @param name Name des Hotels für das die gebuchten Zimmer berechnet werden sollen
	 * @return Liste mit gebuchten Zimmern zu einem gewissen Zeitraum
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Zimmer> getBookedRooms(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		
		ArrayList<Zimmer> zlist = dao.getHotelByName(name).getZimmerList();
		
		ArrayList<Zimmer> bzlist = new ArrayList<Zimmer>();
		
		for (Zimmer zimmer : zlist){
			
			if (zimmer.isBooked(aufenthalt)) bzlist.add(zimmer);
			
		}
		
		return bzlist;
	}
	
	/**
	 * Gibt einem eine Liste mit freien Zimmern zu einer gewissen Zeitdauer zur�ck
	 * @param name Name des Hotels für das die freien Zimmer berechnet werden sollen
	 * @param aufenthalt Zeitraum für den die freien Zimmer berechnet werden sollen
	 * @return Liste mit freien Zimmern in einem Hotel
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	public ArrayList<Zimmer> getFreeRooms(String name, Aufenthalt aufenthalt) throws FileNotFoundException, ClassNotFoundException, IOException{
		
		Hotel hotel = dao.getHotelByName(name);
		
		ArrayList<Zimmer> zlist = hotel.getZimmerList();
		
		ArrayList<Zimmer> fzlist = new ArrayList<Zimmer>();
		
		for (Zimmer zimmer : zlist){
			
			if (!zimmer.isBooked(aufenthalt)) fzlist.add(zimmer);
			
		}
		
		return fzlist;
	}
	
	/**
	 * Gibt eine ArrayListe mit allen Buchungen zu einem gewissen Zeitraum in einem Hotel zur�ck
	 * @param aufenthalt Zeitraum für den die Buchungen berechnet werden sollen
	 * @param name Name des Hotels für das die Buchungen berechnet werden sollen
	 * @return Liste mit allen Buchungen in einem Hotel zu einem gewissen Zeitraum
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Buchung> getBookings(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{

		ArrayList<Zimmer> zlist = getBookedRooms(aufenthalt, name);
		ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
		for (Zimmer zimmer : zlist){
			
			ArrayList<Buchung> bs = zimmer.getBuchungen();
			
			for (Buchung b : bs){
				if (b.getAufenthalt().overlaps(aufenthalt)) buchungen.add(b);
			}
			
		}
		return buchungen;
	}
	
	/**
	 * Gibt die Anzahl der Buchungen zu einem gewissen Zeitraum in einem Hotel zur�ck
	 * @param name Name der Hotels für das die Anzahl der Buchungen berechnet werden soll
	 * @param aufenthalt Zeitraum für den die Anzahl der Buchungen berechnet werden soll
	 * @return Anzahl der Buchungen in einem Hotel
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int getNumberOfBookings(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		int buchungen = 0;
	
		buchungen = getBookings(aufenthalt, name).size();
		
		return buchungen;
	}
	
	/**
	 * Gibt die Einkünfte eines Hotels über einen gewissen Zeitraum zurück
	 * @param name Hotel für das die Einkünfte berechnet werden sollen
	 * @param aufenthalt Zeitraum für den die Einkünfte berechnet werden sollen
	 * @return Einkünfte des Hotels in Cent
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
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
	 * Gibt die Anzahl an möglichen Buchungen einer gewissen Dauer zu einem gewissen Zeitpunkt für ein Hotel zurück
	 * @param name Hotel für das die möglichen Buchungen berechnet werden sollen.
	 * @param aufenthalt Zeitraum für den die möglichen Buchungen berechnet werden sollen. 
	 * @param dauer Dauer einer Buchung
	 * @return Anzahl an möglichen Buchungen
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int getPossibleBookings(Aufenthalt aufenthalt, int dauer, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		int gebucht = 0;
		ArrayList<Buchung> blist = getBookings(aufenthalt, name);
		
		for(Buchung buchung : blist){
			
			gebucht += buchung.getAufenthalt().getDays();
			
		}
		System.out.println("Gebucht: " + gebucht);
		int days = getNumberOfRooms(name) * aufenthalt.getDays();
		System.out.println("Zimmer: " + getNumberOfRooms(name));
		System.out.println("Tage: " + aufenthalt.getDays());
		System.out.println("Termine: " +  days);
		return (days-gebucht)/dauer;
	}
	
	/**
	 * Berechnet den Durchschnittspreis für Zimmer in einem Hotel
	 * @param name Das Hotel für die der Durschnittspreis berechnet werden soll
	 * @return Durchschnittspreis der Zimmer
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
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
	 * Berechnet die Einnamen pro Buchungen pro Tag in einem Hotel zu einem gewissen Zeitraum
	 * @param name Das Hotel für die der Durschnittspreis berechnet werden soll
	 * @param aufenthalt Zeitraum für das der Durschnittspreis berechnet werden soll
	 * @return Durchschnittspreis der Buchungen pro Tag
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int getAverageBookingPricePerDay(String name, Aufenthalt aufenthalt)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		int preis = 0;
		
		ArrayList<Buchung> buchungen = getBookings(aufenthalt, name);
		if (buchungen.isEmpty()) return 0;
		for (Buchung buchung : buchungen){
			
			preis += buchung.getKosten()/buchung.getAufenthalt().getDays();
			}
		return preis/buchungen.size();
		}
	
	/**
	 * Berechnet die Einnamen pro Buchungen in einem Hotel zu einem gewissen Zeitraum
	 * @param name Das Hotel für die der Durschnittspreis berechnet werden soll
	 * @param aufenthalt Zeitraum für das der Durschnittspreis berechnet werden soll
	 * @return Durchschnittspreis der Buchungen
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	public int getAverageBookingPrice(String name, Aufenthalt aufenthalt)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		int preis = 0;
		
		ArrayList<Buchung> buchungen = getBookings(aufenthalt, name);
		if (buchungen.isEmpty()) return 0;
		for (Buchung buchung : buchungen){
			
			preis += buchung.getKosten();
			}
		
		return preis/buchungen.size();
		}
	
	/**
	 * Berechnet den Durchschnittspreis für Services in einem bestimmten Hotel
	 * @param name Hotel für das die Durchschnittspreise ermittelt werden sollen
	 * @return Durchschnittspreis der Services
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public int getAverageServicePrice(String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		int preis = 0;
		List<Service> services = dao.getHotelByName(name).getServiceList();
		for (Service service : services){
			
			preis += service.getPreis();
			}
		return preis/services.size();
		}
	
	/**
	 * Berechnet den meist gebuchten Monat zu einem bestimmten Zeitraum
	 * @param aufenthalt Zeitraum für den der Monat berechnet werden soll
	 * @param name Name des Hotels für das der Monat berechnet werden soll
	 * @return Name des meist gebuchten Monats
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws ServiceException
	 */
	public String getMostBookedMonth(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
				
		ArrayList<Buchung> buchungen = getBookings(aufenthalt, name);
		if(buchungen.isEmpty()) return "-";
		int month = 0;
		int months[] = new int [12];
		
		if(buchungen.isEmpty()) return null;
		
		for(Buchung b : buchungen){
			months[b.getAufenthalt().getMonth()]++;
		}
		for(int i = 0; i <= 11; i++){
			if (months[i] > months[month]) month = i;
		}		
		return new DateFormatSymbols().getMonths()[month];
		}
	

	/**
	 * Berechnet die meist gebuchte Kategorie zu einem bestimmten Zeitraum
	 * @param aufenthalt Zeitraum für den die Kategorie berechnet werden soll
	 * @param name Name des Hotels für das die Kategorie berechnet werden soll
	 * @return Name der meist gebuchten Kategorie
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws ServiceException
	 */
	public String getBestCategory(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		
		Hotel hotel = dao.getHotelByName(name);

		ArrayList<Buchung> buchungen= getBookings(aufenthalt, name);
		if(buchungen.isEmpty()) return "-";
		ArrayList<Kategorie> kategorien = hotel.getKategorien();
		ArrayList<Integer> score = new ArrayList<Integer>();
		
		int index = 0;
		int max = 0;
		
		for(int i = 0; i < kategorien.size(); i++){score.add(i, 0);}
		for(Buchung buchung : buchungen){
			int i = kategorien.indexOf(hotel.getKategorie(buchung.getKategorie().getName()));
			score.set(i, new Integer(score.get(i).intValue()+1));
		}
		for (Integer i : score){
			if(i.intValue() > max) {max = i.intValue(); index = score.indexOf(i);}
		}
		
			return kategorien.get(index).getName();
		}
	

	/**
	 * Berechnet die Einnamhen eines Hotels durch Services zu einem gewissen Zeitraum
	 * @param aufenthalt Zeitraum für den die Einnahmen berechnet werden soll
	 * @param name Name des Hotels für das die Einnahmen berechnet werden soll
	 * @return Einnahmen durch Services in Cent
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public int getServiceRevenue(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException{
		ArrayList<Buchung> buchungen = getBookings(aufenthalt, name);
		ArrayList<Service> services = new ArrayList<Service>();
		int revenue= 0;

		for (Buchung buchung : buchungen){
			
			services.addAll(buchung.getServices().values());
			
		}
		
		for (Service service : services){
			
			revenue += service.getPreis();
			
		}
		return revenue;
		}
	

	/**
	 * Berechnet den meist gebuchten Service zu einem bestimmten Zeitraum
	 * @param aufenthalt Zeitraum für den der Service berechnet werden soll
	 * @param name Name des Hotels für das der Service berechnet werden soll
	 * @return Name des meist gebuchten Services
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws ServiceException
	 */
	public String getBestService(Aufenthalt aufenthalt, String name)throws FileNotFoundException, IOException,
	ClassNotFoundException, ServiceException{
		Hotel hotel = dao.getHotelByName(name);

		ArrayList<Buchung> buchungen= getBookings(aufenthalt, name);
		ArrayList<Integer> score = new ArrayList<Integer>();
		ArrayList<Service> services = new ArrayList<Service>();
		ArrayList<Service> slist = (ArrayList<Service>)hotel.getServiceList();
		
		for (Buchung buchung : buchungen){
			
			services.addAll(buchung.getServices().values());
			
		}
		
		if(services.isEmpty()) return "-";
		
		int index = 0;
		int max = 0;
		
		for(int i = 0; i < slist.size(); i++){score.add(i, 0);}
		for(Service service : services){
			int i = slist.indexOf(hotel.getService(service.getName()));
			score.set(i, new Integer(score.get(i).intValue()+1));
		}
		for (Integer i : score){
			if(i.intValue() > max) {max = i.intValue(); index = score.indexOf(i);}
		}
		
			return slist.get(index).getName();
		}

}
