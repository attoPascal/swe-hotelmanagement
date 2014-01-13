/**
 * 
 */
package hm.managers;

import java.io.FileNotFoundException;
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
	 * Erstellt einen neuen Service
	 * @param serName
	 * @param beschreibung
	 * @param preis
	 * @param name Name des Hotels für das der Service erstellt werden soll
	 * @return
	 */
	public Service createService(String serName, String beschreibung, int preis, String name) {
		//TODO
		return null;
	}
	
	/**
	 * Ändert die angegebenen Parameter des passenden Services
	 * 	 
	 * @param name Name des Hotels für das der Service verändert werden soll
	 */
	public Service editService(String oldName, String newName, String newBeschreibung, int newPreis, String Name) {
		//TODO
		return null;
	}
	
	/**
	 * Löscht das passende Service
	 * @param name Name des Hotels für das der Service erstellt werden soll
	 */
	public Service removeService(String serviceName, String Name) {
		//TODO
		return null;
	}
	
	/**
	 * Liefert alle angebotenen Services des spezifizierten Hotels zurück
	 * 
	 * @param name Name des Hotels für das alle Services zurückgegeben werden sollen
	 */
	public HashMap<String,Service> getServices(String name) throws FileNotFoundException, IOException,
	ClassNotFoundException{
		Hotel hotel = dao.getHotelByName(name);
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
	 * Weist das übergebene Service zum übergebenen Zeitpunkt dem übergebenen Gast zu
	 */
	public void serviceBuchen(HotelGast gast, Service service, Date date) {
		//TODO
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
