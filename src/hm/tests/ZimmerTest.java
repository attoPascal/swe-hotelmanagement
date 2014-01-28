package hm.tests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import hm.Aufenthalt;
import hm.Kategorie;
import hm.Zimmer;

import org.junit.Test;

public class ZimmerTest {
	@Test
	public void testNummer() {
		Zimmer z = new Zimmer(123);
		assertEquals(123, z.getNummer());
		
		z.setNummer(147);
		assertNotEquals(123, z.getNummer());
		assertEquals(147, z.getNummer());
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testBuchung() throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Zimmer z1 = new Zimmer(123);
		Zimmer z2 = new Zimmer(147);
		
		Aufenthalt a1 = new Aufenthalt(df.parse("2014-02-01"), 10);
		Aufenthalt a2 = new Aufenthalt(df.parse("2014-02-01"), 1);
		Aufenthalt a3 = new Aufenthalt(df.parse("2014-01-31"), 1);
		Aufenthalt a4 = new Aufenthalt(df.parse("2014-02-01"), 15);
		Aufenthalt a5 = new Aufenthalt(df.parse("2014-02-05"), 10);
		
		assertFalse(z1.isBooked(a1));
		
		// 01.02 bis 10.02
		z1.addBuchung(new Kategorie("", 0, ""), a1, 0);
		assertTrue(z1.isBooked(a1));
		
		//assertTrue(z1.isBooked(a2));
		assertFalse(z1.isBooked(a3));
		//assertTrue(z1.isBooked(a4));
		assertTrue(z1.isBooked(a5));
		
		// 01.02. bis 01.02
		z2.addBuchung(new Kategorie("", 0, ""), a2, 1);
		assertTrue(z2.isBooked(a2));
		
		//assertTrue(z2.isBooked(a1));
		assertFalse(z2.isBooked(a3));
		//assertTrue(z2.isBooked(a4));
		assertFalse(z2.isBooked(a5));
	}
}
