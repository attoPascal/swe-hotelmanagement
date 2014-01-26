package hm;

import hm.exceptions.BuchungsException;
import hm.exceptions.ServiceException;
import hm.users.HotelGast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Zeichnet ein Hotel-Objekt aus. Beinhaltet Zimmer und Kategorien
 */
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	/**
	 * Die Kategorien, die das Hotel anbietet
	 */
	private ArrayList<Kategorie> kategorien = new ArrayList<Kategorie>();

	/**
	 * Liste der Zimmer, die das Hotel zur Verfuegung hat
	 */
	private ArrayList<Zimmer> zimmerList = new ArrayList<Zimmer>();
	
	private HashMap<String,Service> services = new HashMap<String,Service>();

	public Hotel(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setZimmerList(ArrayList<Zimmer> zimmerList) {
		this.zimmerList = zimmerList;
	}

	/**
	 * @return gibt eine ArrayList(kategorien) mit allen im Hotel gespeicherten Kategorien zurueck
	 */
	public ArrayList<Kategorie> getKategorien() {
		return kategorien;
	}

	public void setKategorien(ArrayList<Kategorie> kategorien) {
		this.kategorien = kategorien;
	}

	/**
	 * @return gibt eine ArrayList(zimmerList) mit allen im Hotel gespeicherten Zimmern zurueck
	 */
	public ArrayList<Zimmer> getZimmerList() {
		return zimmerList;
	}
	
	public void setZimmer(ArrayList<Zimmer> zimmerList) {
		this.zimmerList = zimmerList;
	}

	/**
	 * @param zimmer Wird dem Hotel-Objekt hinzugefuegt
	 */
	public void addZimmer(Zimmer zimmer) {
		zimmerList.add(zimmer);
	}

	/**
	 * @param zimmer Wird aus dem Hotel-Objekt entfernt
	 */
	public void removeZimmer(Zimmer zimmer) {
		zimmerList.remove(zimmer);
	}

	/**
	 * Fuegt eine Kategorie dem Hotel-Objekt hinzu
	 * @param kategorie die hinzugefuegt wird
	 */
	public void addKategorie(Kategorie kategorie) {
		kategorien.add(kategorie);
	}

	/**
	 * Loescht eine Kategorie des Hotel-Objekts
	 * @param kategorie gesuchte Kategorie
	 */
	public void removeKategorie(Kategorie kategorie) {
		kategorien.remove(kategorie);
	}

	/**
	 * Sucht eine Kategorie des Hotels
	 * @param name gesuchte Kategorie
	 * @return gibt gesuchte kategorie des Hotels zurueck; Null wenn es nicht gefunden wird.
	 */
	public Kategorie getKategorie(String name) throws NullPointerException {

		for (Kategorie kategorie : kategorien) {
			if (kategorie.getName().equals(name))
				return kategorie;
		}

		return null;
	}
	
	/**
	 * Sucht ein Zimmer des Hotels
	 * @param nummer des gesuchten Zimmers
	 * @return gibt gesuchtes Zimmer zurueck, wenn es gefunden wird; sonst null
	 */
	public Zimmer getZimmer(int nummer) {

		for (Zimmer zimmer : zimmerList) {
			if (zimmer.getNummer() == nummer)
				return zimmer;
		}
		return null;
	}
	
	/**
	 * Fügt ein Service zum Hotel hinzu
	 * @param service Das neue Service
	 * @throws ServiceException wenn der angegebene Name bereits verwendet wird
	 */
	public void addService(Service service) throws ServiceException {
		String name = service.getName();
		
		if (services.containsKey(name)) {
			throw new ServiceException("Ein Service mit dem angegebenen Namen existiert bereits");
		} else {
			services.put(name, service);
		}
	}
	
	/**
	 * Entfernt ein Service aus dem Hotel
	 * @param name Name des Services, das entfernt werden soll
	 * @return das Service, das entfernt wurde
	 * @throws ServiceException wenn kein Service mit dem angegebenen Namen existiert
	 */
	public Service removeService(String name) throws ServiceException {
		if (!services.containsKey(name)) {
			throw new ServiceException("Es existiert kein Service mit dem angegebenen Namen");
		} else {
			return services.remove(name);
		}
	}
	
	/**
	 * Gibt das Service mit dem angegebenen Namen zurück
	 * @param name Name des Service
	 * @return das Service
	 * @throws ServiceException wenn kein Service mit dem angegebenen Namen existiert
	 */
	public Service getService(String name) throws ServiceException {
		if (!services.containsKey(name)) {
			throw new ServiceException("Es existiert kein Service mit dem angegebenen Namen");
		} else {
			return services.get(name);
		}
	}
	
	/**
	 * Ersetzt das alte Service mit dem angegebenen Namen durch ein neues
	 * @param oldName Name des alten Services
	 * @param newService das neue Service
	 * @throws ServiceException wenn kein Service mit dem angegebenen Namen existiert
	 */
	public void editService(String oldName, Service newService) throws ServiceException {
		if (!services.containsKey(oldName)) {
			throw new ServiceException("Es existiert kein Service mit dem angegebenen Namen");
		} else {
			services.remove(oldName);
			services.put(newService.getName(), newService);
		}
	}
	
	public Map<String,Service> getServices() {
		return services;
	}
	
	public List<Service> getServiceList() {
		return new ArrayList<Service>(services.values());
	}
	
	public Buchung getBuchungByID(int id) throws BuchungsException {
		for (Zimmer z : zimmerList) {
			for (Buchung b : z.getBuchungen()) {
				if (b.getId() == id) {
					return b;
				}
			}
		}
		
		throw new BuchungsException("Es existiert keine Buchung mit der angegebenen ID");
	}
	
	public List<Buchung> getBuchungsList(HotelGast gast) {
		ArrayList<Buchung> list = new ArrayList<Buchung>();
		System.out.println("JAJAJAJAJAJAJAJAJAJAJ" + gast.getBuchungsIDs().size());
		for (int i : gast.getBuchungsIDs()) {
			try {
				list.add(getBuchungByID(i));
			} catch (BuchungsException e) {
				// do nothing
			}
		}
		
		return list;
	}
	
	//TODO toHtml?
	public String toString(){
		
		StringBuffer s = new StringBuffer();
		
		s.append("<h1>Hotel '" + getName() + "'</h1>");
		
		for (Kategorie kategorie : this.getKategorien()){
			s.append("<h2>Kategorie '" + kategorie.getName() + "'</h2>");
			s.append("<div>Preis: " + kategorie.getPreis() + "</div>");
			s.append("<div>Ausstattung: " + kategorie.getAusstattung() + "</div>");
			
			s.append("<div>Zimmer: ");
			for (int zimmerNummer : kategorie.getZimmerMap().keySet()) {
				s.append(zimmerNummer + " ");
			}
			s.append("</div>");
		}

		return s.toString();	

	}
}
