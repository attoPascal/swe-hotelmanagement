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
import hm.managers.*;

import java.io.FileNotFoundException;
import java.io.IOException;
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
		
		//luescht alte daten
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
		//fuegt zimmer zu hotel hinzu
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		//erstellt kategorien
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		//fuegt zimmer zu kategorien hinzu
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		//fuegt kategorien zu hotel hinzu
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		BuchungsManagement bm = new BuchungsManagement();
		Calendar c = new GregorianCalendar ();
		c.set(2014, 5, 23, 0, 0, 0);
		
		//erstellt neue buchung fuer kategorie einzel am 25.5.2014
		int zimmernummer = bm.neueBuchung(h1.getKategorie("Einzel"), new Aufenthalt(new Date(c.getTimeInMillis()), 1));

		assertEquals(102,zimmernummer);
		
		//erstellt neue buchung fuer kategorie einzel am 25.5.2014
		int zimmernummer2 = bm.neueBuchung(h1.getKategorie("Einzel"), new Aufenthalt(new Date(c.getTimeInMillis()), 1));

		assertEquals(101,zimmernummer2);
		
	}
	

	/**
	 * Testet ob erfolgreich abgebrochen wird wenn ueberbucht
	 */
	@Test (expected = NullPointerException.class)
	public void BuchungsExceptionTest() {
		
		//luescht alte daten
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
		//fuegt zimmer zu hotel hinzu
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		//erstellt kategorien
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		//fuegt zimmer zu kategorien hinzu
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		//fuegt kategorien zu hotel hinzu
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		BuchungsManagement bm = new BuchungsManagement();
		Calendar c = new GregorianCalendar ();
		c.set(2014, 5, 23, 0, 0, 0);
		
		//erstellt neue buchung fuer kategorie einzel am 25.5.2014
		int zimmernummer = bm.neueBuchung(h1.getKategorie("Einzel"), new Aufenthalt(new Date(c.getTimeInMillis()), 1));

		assertEquals(102,zimmernummer);
		
		//erstellt neue buchung fuer kategorie einzel am 25.5.2014
		int zimmernummer2 = bm.neueBuchung(h1.getKategorie("Einzel"), new Aufenthalt(new Date(c.getTimeInMillis()), 1));

		assertEquals(101,zimmernummer2);
		
		//erstellt neue buchung fuer kategorie einzel am 25.5.2014
		bm.neueBuchung(h1.getKategorie("Einzel"), new Aufenthalt(new Date(c.getTimeInMillis()), 1));

		
	}
	
	/**
	 * Testet ob erfolgreich ein neues Zimmer erstellt wird
	 */
	@Test 
	public void ZimmerErstellungsTest() {
		
		//luescht alte daten
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
		//fuegt zimmer zu hotel hinzu
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		//erstellt kategorien
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		//fuegt zimmer zu kategorien hinzu
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		//fuegt kategorien zu hotel hinzu
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		try {
			dao.saveHotel(h1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean hasZimmer = false;
		
		try {
			zm.createZimmer("CrazySharkyFish", 909);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Hotel h2;
		try {
			h2 = dao.getHotelByName("CrazySharkyFish");
			if (h2.getZimmer(909) != null) hasZimmer = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		assertEquals(true,hasZimmer);
		

		
	}


	/**
	 * Testet ob die Zimmerkategorie eines Zimmers erfolgreich geuendert wird
	 */
	@Test 
	public void ZimmerKategorieTest() {
		
		//luescht alte daten
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
		//fuegt zimmer zu hotel hinzu
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		//erstellt kategorien
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		//fuegt zimmer zu kategorien hinzu
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		//fuegt kategorien zu hotel hinzu
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		try {
			dao.saveHotel(h1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean hasZimmer = false;
		
		try {
			zm.setZimmerKategorie("CrazySharkyFish", "Doppel", 101);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		HashMap<Integer, Zimmer> zimmerMap;
		try {
			zimmerMap = dao.getHotelByName("CrazySharkyFish").getKategorie("Doppel").getZimmerMap();
			Zimmer zimmer;
			Iterator<Entry<Integer, Zimmer>> it = zimmerMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Integer, Zimmer> zimmers = (Map.Entry<Integer, Zimmer>) it.next();// TODO variable name
				zimmer = (Zimmer) zimmers.getValue();
						
				if (zimmer.getNummer() == 101) {
					hasZimmer = true;
				}
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		assertEquals(true,hasZimmer);
		

		
	}
	

	/**
	 * Testet ob Kategorien erfolgreich erstellt werden
	 */
	@Test
	public void KategorieErstellenTest() {
		
		//luescht alte daten
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
		//fuegt zimmer zu hotel hinzu
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		//erstellt kategorien
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		//fuegt zimmer zu kategorien hinzu
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		//fuegt kategorien zu hotel hinzu
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		
		KategorieManagement km = new KategorieManagement();

		try {
			km.createCategority(h1, "Absteige", "Heu, Eimer zum Waschen ", 5);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DAO dao = km.getDAO();
		
		Hotel h2 = new Hotel();
		
		try {
			h2 = dao.getHotelByName("CrazySharkyFish");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(5, h2.getKategorie("Absteige").getPreis());
		assertEquals("Heu, Eimer zum Waschen ", h2.getKategorie("Absteige").getAusstattung());

	}
	

	/**
	 * Testet ob Kategorien erfolgreich geuendert werden
	 */
	@Test
	public void KategorieAendernTest() {
		
		//luescht alte daten
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
		//fuegt zimmer zu hotel hinzu
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		//erstellt kategorien
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		//fuegt zimmer zu kategorien hinzu
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		//fuegt kategorien zu hotel hinzu
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		
		KategorieManagement km = new KategorieManagement();

		try {
			km.editCategory(h1, "Einzel", "Loft", 5,"Heu, Eimer zum Waschen ");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DAO dao = km.getDAO();
		
		Hotel h2 = new Hotel();
		try {
			h2 = dao.getHotelByName("CrazySharkyFish");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(5, h2.getKategorie("Loft").getPreis());
		assertEquals("Heu, Eimer zum Waschen ", h2.getKategorie("Loft").getAusstattung());

	}
	

}
