package hm.tests;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.io.*;

import hm.Hotel;
import hm.Kategorie;
import hm.Zimmer;
import hm.dao.DAO;
import hm.dao.SerializedDAO;

public class DAOTest1 {

	public static void main(String[] args) {
		Hotel hotel = new Hotel("CrazySharkyFish");
		
		Zimmer z1 = new Zimmer(101);
		Zimmer z2 = new Zimmer(102);
		Zimmer z3 = new Zimmer(103);
		Zimmer z4 = new Zimmer(104);
		Zimmer z5 = new Zimmer(105);
		
		ArrayList<Zimmer> zList = new ArrayList<Zimmer>();
		zList.add(z1);
		zList.add(z2);
		zList.add(z3);
		zList.add(z4);
		zList.add(z5);
		hotel.setZimmerList(zList);
		
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		
		ArrayList<Kategorie> katList = new ArrayList<Kategorie>();
		katList.add(kat1);
		katList.add(kat2);
		katList.add(kat3);
		hotel.setKategorien(katList);
		
		try {
			DAO dao = new SerializedDAO("testdata.ser");
			dao.saveHotel(hotel);
		
			Hotel h2 = dao.getHotelByName("CrazySharkyFish");
			ArrayList<Kategorie> katList2 = h2.getKategorien();
		
			for (Kategorie k : katList2) {
				System.out.print(k.getName() + ": ");
			
				for (Entry<Integer, Zimmer> e : k.getZimmerMap().entrySet()) {
					System.out.print(e.getKey() + " ");
				}
			
				System.out.println();
			}
			
		} catch (ClassNotFoundException e) {
			
		} catch (FileNotFoundException f) {
			
		} catch (IOException e) {
			
		}
	}

}
