package hm.managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import hm.Buchung;
import hm.Hotel;
import hm.Service;
import hm.dao.DAO;
import hm.dao.SerializedDAO;
import hm.exceptions.BuchungsException;
import hm.exceptions.ServiceException;

/**
 * Zur Verwaltung der Services
 */
public class ServiceManagement {
		
	/**
	 * Erstellt ein neues Service
	 */
	public static void createService(String name, String beschreibung, int preis, String hotelName) throws FileNotFoundException, ClassNotFoundException, IOException, ServiceException {
		DAO dao = getDAO();
		
		Service service = new Service(name, beschreibung, preis);
		Hotel hotel = dao.getHotelByName(hotelName);
		hotel.addService(service);
		dao.saveHotel(hotel);
	}
	
	/**
	 * Ändert die angegebenen Parameter des angegebenen Services
	 */
	public static void editService(String oldName, String newName, String newBeschreibung, int newPreis, String hotelName) throws FileNotFoundException, ClassNotFoundException, IOException, ServiceException {
		DAO dao = getDAO();
		
		Hotel hotel = dao.getHotelByName(hotelName);
		Service oldService = hotel.getService(oldName);
		
		String name;
		String beschreibung;
		int preis;
		
		if (newName == null || newName.equals("")) {
			name = oldName;
		} else {
			name = newName;
		}
		
		if (newBeschreibung == null || newBeschreibung.equals("")) {
			beschreibung = oldService.getBeschreibung();
		} else {
			beschreibung = newBeschreibung;
		}
		
		if (newPreis <= 0) {
			preis = oldService.getPreis();
		} else {
			preis = newPreis;
		}
		
		Service newService = new Service(name, beschreibung, preis);
		hotel.editService(oldName, newService);
		dao.saveHotel(hotel);
	}
	
	/**
	 * Entfernt das angegebene Service
	 * @return das entfernte Service
	 */
	public static Service removeService(String name, String hotelName) throws FileNotFoundException, ClassNotFoundException, IOException, ServiceException {
		DAO dao = getDAO();
		
		Hotel hotel = dao.getHotelByName(hotelName);
		Service service = hotel.removeService(name);
		dao.saveHotel(hotel);
		return service;
	}
	
	/**
	 * Liefert alle angebotenen Services des spezifizierten Hotels zurück
	 */
	public static List<Service> getServices(String hotelName) throws FileNotFoundException, IOException, ClassNotFoundException{
		Hotel hotel = getDAO().getHotelByName(hotelName);
		return hotel.getServiceList();
	}
	
	/**
	 * Liefert alle gebuchten Services der jeweiligen Buchung zurück
	 */
	public static Map<Date,Service> getServices(int buchungsID, String hotelName) throws BuchungsException, IOException, ClassNotFoundException {
		DAO dao = getDAO();
		Hotel hotel = dao.getHotelByName(hotelName);
		
		return hotel.getBuchungByID(buchungsID).getServices();
	}
	
	/**
	 * Weist das übergebene Service zum übergebenen Zeitpunkt der übergebenen Buchung zu
	 */
	public static void serviceBuchen(String serviceName, String dateString, int buchungsID, String hotelName) throws FileNotFoundException, ClassNotFoundException, IOException, ServiceException, ParseException, BuchungsException {
		DAO dao = getDAO();
		
		Hotel hotel = dao.getHotelByName(hotelName);
		Service service = hotel.getService(serviceName);
		Buchung buchung = hotel.getBuchungByID(buchungsID);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(dateString);
		
		buchung.addService(service, date);
		dao.saveHotel(hotel);
	}
	
	/**
	 * Liefert das verwendete DAO zurück
	 * @throws IOException 
	 */
	public static DAO getDAO() throws IOException {
		return SerializedDAO.getInstance();
	}
}
