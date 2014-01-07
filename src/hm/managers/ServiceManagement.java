/**
 * 
 */
package hm.managers;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import hm.Hotel;
import hm.Service;
import hm.dao.DAO;
import hm.dao.SerializedDAO;
import hm.users.HotelGast;

/**
 * Zur Verwaltung der Services
 */
public class ServiceManagement {
	private DAO dao;

	public ServiceManagement() {
		
	}
	
	/**
	 * Erstellt ein neues Service
	 */
	public Service createService(String hotelName, String name, String beschreibung, int preis) {
		//TODO
		return null;
	}
	
	/**
	 * Ändert die angegebenen Parameter des passenden Services
	 */
	public Service editService(String hotelName, String oldName, String newName, String newBeschreibung, int newPreis) {
		//TODO
		return null;
	}
	
	/**
	 * Löscht das passende Service
	 */
	public Service deleteService(String hotelName, String serviceName) {
		//TODO
		return null;
	}
	
	/**
	 * Liefert alle angebotenen Services des spezifizierten Hotels zurück
	 */
	public HashMap<String,Service> getServices(Hotel hotel) {
		//TODO
		return null;
	}
	
	/**
	 * Liefert alle gebuchten Services des spezifizierten Gasts zurück
	 */
	public HashMap<Date,Service> getServices(HotelGast gast) {
		//TODO
		return null;
	}
	
	/**
	 * Instanziert das DAO
	 */
	public void instantiateDAO() throws IOException {
		dao = SerializedDAO.getInstance();
	}
	
	/**
	 * Liefert das verwendete DAO zurück
	 */
	public DAO getDAO() {
		return dao;
	}
}
