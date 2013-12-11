/**
 * 
 */
package hm.tests;

import static org.junit.Assert.*;
import hm.Aufenthalt;
import hm.Hotel;
import hm.Kategorie;
import hm.Zimmer;
import hm.dao.DAO;
import hm.dao.SerializedDAO;
import hm.servlets.BuchungsManagement;
import hm.servlets.KategorieManagement;
import hm.servlets.ZimmerManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

/**
 * 
 *
 */
public class UnitTest {

	/**
	 * Testet ob erfolgreich gebucht wird
	 */
	@Test
	public void buchungsTest() {
		
		//l�scht alte daten
		try {
		    Files.delete(Paths.get("data.ser"));
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", Paths.get("data.ser"));
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", Paths.get("data.ser"));
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		
		//erstellt ein hotel
		Hotel h1 = new Hotel("CrazySharkyFish");
		//erstellt zimmer
		Zimmer z1 = new Zimmer(101);
		Zimmer z2 = new Zimmer(102);
		Zimmer z3 = new Zimmer(103);
		Zimmer z4 = new Zimmer(104);
		Zimmer z5 = new Zimmer(105);
		//f�gt zimmer zu hotel hinzu
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		//erstellt kategorien
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		//f�gt zimmer zu kategorien hinzu
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		//f�gt kategorien zu hotel hinzu
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		BuchungsManagement bm = new BuchungsManagement();
		Calendar c = new GregorianCalendar ();
		c.set(2014, 5, 23, 0, 0, 0);
		
		//erstellt neue buchung f�r kategorie einzel am 25.5.2014
		int zimmernummer = bm.neueBuchung(h1.getKategorie("Einzel"), new Aufenthalt(new Date(c.getTimeInMillis()), 1));

		assertEquals(102,zimmernummer);
		
		//erstellt neue buchung f�r kategorie einzel am 25.5.2014
		int zimmernummer2 = bm.neueBuchung(h1.getKategorie("Einzel"), new Aufenthalt(new Date(c.getTimeInMillis()), 1));

		assertEquals(101,zimmernummer2);
		
	}
	

	/**
	 * Testet ob erfolgreich abgebrochen wird wenn �berbucht
	 */
	@Test (expected = NullPointerException.class)
	public void BuchungsExceptionTest() {
		
		//l�scht alte daten
		try {
		    Files.delete(Paths.get("data.ser"));
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", Paths.get("data.ser"));
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", Paths.get("data.ser"));
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		
		//erstellt ein hotel
		Hotel h1 = new Hotel("CrazySharkyFish");
		//erstellt zimmer
		Zimmer z1 = new Zimmer(101);
		Zimmer z2 = new Zimmer(102);
		Zimmer z3 = new Zimmer(103);
		Zimmer z4 = new Zimmer(104);
		Zimmer z5 = new Zimmer(105);
		//f�gt zimmer zu hotel hinzu
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		//erstellt kategorien
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		//f�gt zimmer zu kategorien hinzu
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		//f�gt kategorien zu hotel hinzu
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		BuchungsManagement bm = new BuchungsManagement();
		Calendar c = new GregorianCalendar ();
		c.set(2014, 5, 23, 0, 0, 0);
		
		//erstellt neue buchung f�r kategorie einzel am 25.5.2014
		int zimmernummer = bm.neueBuchung(h1.getKategorie("Einzel"), new Aufenthalt(new Date(c.getTimeInMillis()), 1));

		assertEquals(102,zimmernummer);
		
		//erstellt neue buchung f�r kategorie einzel am 25.5.2014
		int zimmernummer2 = bm.neueBuchung(h1.getKategorie("Einzel"), new Aufenthalt(new Date(c.getTimeInMillis()), 1));

		assertEquals(101,zimmernummer2);
		
		//erstellt neue buchung f�r kategorie einzel am 25.5.2014
		int zimmernummer3 = bm.neueBuchung(h1.getKategorie("Einzel"), new Aufenthalt(new Date(c.getTimeInMillis()), 1));

		
	}
	
	/**
	 * Testet ob erfolgreich ein neues Zimmer erstellt wird
	 */
	@Test 
	public void ZimmerErstellungsTest() {
		
		//l�scht alte daten
		try {
		    Files.delete(Paths.get("data.ser"));
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", Paths.get("data.ser"));
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", Paths.get("data.ser"));
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		
		ZimmerManagement zm = new ZimmerManagement();

		DAO dao = zm.getDAO();

		//erstellt ein hotel
		Hotel h1 = new Hotel("CrazySharkyFish");
		//erstellt zimmer
		Zimmer z1 = new Zimmer(101);
		Zimmer z2 = new Zimmer(102);
		Zimmer z3 = new Zimmer(103);
		Zimmer z4 = new Zimmer(104);
		Zimmer z5 = new Zimmer(105);
		//f�gt zimmer zu hotel hinzu
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		//erstellt kategorien
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		//f�gt zimmer zu kategorien hinzu
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		//f�gt kategorien zu hotel hinzu
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		dao.saveHotel(h1);
		
		boolean hasZimmer = false;
		
		zm.createZimmer("CrazySharkyFish", 909);
		
		Hotel h2 = dao.getHotelByName("CrazySharkyFish");
		
		if (h2.getZimmer(909) != null) hasZimmer = true;
			
		assertEquals(true,hasZimmer);
		

		
	}


	/**
	 * Testet ob die Zimmerkategorie eines Zimmers erfolgreich ge�ndert wird
	 */
	@Test 
	public void ZimmerKategorieTest() {
		
		//l�scht alte daten
		try {
		    Files.delete(Paths.get("data.ser"));
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", Paths.get("data.ser"));
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", Paths.get("data.ser"));
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		
		ZimmerManagement zm = new ZimmerManagement();

		DAO dao = zm.getDAO();

		//erstellt ein hotel
		Hotel h1 = new Hotel("CrazySharkyFish");
		//erstellt zimmer
		Zimmer z1 = new Zimmer(101);
		Zimmer z2 = new Zimmer(102);
		Zimmer z3 = new Zimmer(103);
		Zimmer z4 = new Zimmer(104);
		Zimmer z5 = new Zimmer(105);
		//f�gt zimmer zu hotel hinzu
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		//erstellt kategorien
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		//f�gt zimmer zu kategorien hinzu
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		//f�gt kategorien zu hotel hinzu
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		dao.saveHotel(h1);
		
		boolean hasZimmer = false;
		
		zm.setZimmerKategorie("CrazySharkyFish", "Doppel", 101);
		
		
		HashMap<Integer, Zimmer> zimmerMap = dao.getHotelByName("CrazySharkyFish").getKategorie("Doppel").getZimmerMap();
		Zimmer zimmer;
		Iterator<Entry<Integer, Zimmer>> it = zimmerMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Zimmer> zimmers = (Map.Entry<Integer, Zimmer>) it.next();// TODO variable name
			zimmer = (Zimmer) zimmers.getValue();
					
			if (zimmer.getNummer() == 101) {
				hasZimmer = true;
			}
		}		
		assertEquals(true,hasZimmer);
		

		
	}
	

	/**
	 * Testet ob Kategorien erfolgreich erstellt werden
	 */
	@Test
	public void KategorieErstellenTest() {
		
		//l�scht alte daten
		try {
		    Files.delete(Paths.get("data.ser"));
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", Paths.get("data.ser"));
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", Paths.get("data.ser"));
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		
		//erstellt ein hotel
		Hotel h1 = new Hotel("CrazySharkyFish");
		//erstellt zimmer
		Zimmer z1 = new Zimmer(101);
		Zimmer z2 = new Zimmer(102);
		Zimmer z3 = new Zimmer(103);
		Zimmer z4 = new Zimmer(104);
		Zimmer z5 = new Zimmer(105);
		//f�gt zimmer zu hotel hinzu
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		//erstellt kategorien
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		//f�gt zimmer zu kategorien hinzu
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		//f�gt kategorien zu hotel hinzu
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		
		KategorieManagement km = new KategorieManagement();

		km.createCategority(h1, "Absteige", "Heu, Eimer zum Waschen ", 5);
		
		DAO dao = km.getDAO();
		
		Hotel h2 = dao.getHotelByName("CrazySharkyFish");
		
		assertEquals(5, h2.getKategorie("Absteige").getPreis());
		assertEquals("Heu, Eimer zum Waschen ", h2.getKategorie("Absteige").getAusstattung());

	}
	

	/**
	 * Testet ob Kategorien erfolgreich ge�ndert werden
	 */
	@Test
	public void KategorieAendernTest() {
		
		//l�scht alte daten
		try {
		    Files.delete(Paths.get("data.ser"));
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", Paths.get("data.ser"));
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", Paths.get("data.ser"));
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		
		//erstellt ein hotel
		Hotel h1 = new Hotel("CrazySharkyFish");
		//erstellt zimmer
		Zimmer z1 = new Zimmer(101);
		Zimmer z2 = new Zimmer(102);
		Zimmer z3 = new Zimmer(103);
		Zimmer z4 = new Zimmer(104);
		Zimmer z5 = new Zimmer(105);
		//f�gt zimmer zu hotel hinzu
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		//erstellt kategorien
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		//f�gt zimmer zu kategorien hinzu
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		//f�gt kategorien zu hotel hinzu
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		
		KategorieManagement km = new KategorieManagement();

		km.editCategory(h1, "Einzel", "Loft", 5,"Heu, Eimer zum Waschen ");
		
		DAO dao = km.getDAO();
		
		Hotel h2 = dao.getHotelByName("CrazySharkyFish");
		
		assertEquals(5, h2.getKategorie("Loft").getPreis());
		assertEquals("Heu, Eimer zum Waschen ", h2.getKategorie("Loft").getAusstattung());

	}
	

}
