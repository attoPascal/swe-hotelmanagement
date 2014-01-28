package hm.tests;

import static org.junit.Assert.*;
import hm.Aufenthalt;
import hm.Buchung;
import hm.Kategorie;
import hm.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BuchungTest {
	private static DateFormat df;
	private static Date d1;
	private static Date d2;
	private static Aufenthalt a;
	private static Kategorie k;
	
	private Buchung b1;
	private Buchung b2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		df = new SimpleDateFormat("yyyy-MM-dd");
		d1 = df.parse("2014-02-01");
		d2 = df.parse("2014-02-28");
		a = new Aufenthalt(d1, 10);
		k = new Kategorie("TestKat", 10, "Test");
	}

	@Before
	public void setUp() throws Exception {
		b1 = new Buchung(k, a, 1);
		b2 = new Buchung(k, d1, 20, 2);
	}

	@Test
	public void testFields() throws ParseException {
		assertEquals(k, b1.getKategorie());
		assertEquals(a, b1.getAufenthalt());
		assertEquals(1, b1.getId());
		assertEquals(100, b1.getKosten());
		
		assertEquals(k, b2.getKategorie());
		assertNotEquals(a, b2.getAufenthalt());
		assertEquals(2, b2.getId());
		assertEquals(200, b2.getKosten());
		
		b2.setAufenthalt(a);
		assertEquals(a, b2.getAufenthalt());
		assertEquals(100, b1.getKosten());
	}
	
	@Test
	public void testServices() {
		Service s1 = new Service("S1", "1", 1);
		Service s2 = new Service("S2", "2", 2);
		
		b1.addService(s1, d1);
		b1.addService(s2, d2);
		
		b2.addService(s1, d2);
		
		assertNotEquals(b1.getServices(), b2.getServices());
		
		assertTrue(b1.getServices().containsKey(d1));
		assertTrue(b1.getServices().containsKey(d2));
		assertFalse(b2.getServices().containsKey(d1));
		assertTrue(b2.getServices().containsKey(d2));
		
		assertTrue(b1.getServices().containsValue(s1));
		assertTrue(b1.getServices().containsValue(s2));
		assertTrue(b2.getServices().containsValue(s1));
		assertFalse(b2.getServices().containsValue(s2));
	}

}
