/**
 * 
 */
package hm.tests;

import static org.junit.Assert.*;
//import hm.Aufenthalt;
import hm.Buchung;
import hm.Hotel;
import hm.Kategorie;
import hm.Zimmer;
import hm.dao.DAO;
//import hm.dao.SerializedDAO;
import hm.managers.*;
import hm.users.HotelGast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * 
 *
 */
public class BuchungStornierenTest {
	/**
	 * Test, ob eine bestehende Buchung erfolgreich storniert wird
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws NullPointerException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */

	@Test
	public void buchungsTest() throws FileNotFoundException, ClassNotFoundException, NullPointerException, IOException, ParseException {
		
		//loescht alte daten
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
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo, Butler");
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
				
		HotelGast user = new HotelGast("Hugo", "pwd", "123");	//123=Zahlungsdaten
		try{
		bm.instantiateDAO();
		DAO dao = bm.getDAO();
		dao.saveHotel(h1);
		dao.saveUser(user);
		}catch(Exception e){
			e.printStackTrace();
		}

		//erstellt neue buchung fuer kategorie einzel am 25.5.2014
		Buchung b1 = bm.createBuchung(h1.getName(),h1.getKategorie("Einzel").getName(), "2014-05-23", 1, user);
		
		//entfernt die Buchung
		try {
			bm.removeBuchung(b1.getId(),b1.getZimmernummer(), h1.getName());
			assertEquals(102,b1.getZimmernummer());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}
