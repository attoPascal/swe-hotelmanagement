package hm;

import java.util.ArrayList;

public class BuchungsManagement {


		
		public void neueBuchung(Kategorie kategorie, Aufenthalt aufenthalt){
			
			Zimmer gebuchtesZimmer;
			
			Buchung buchung = new Buchung(kategorie, aufenthalt);
			
			buchung.getZimmer().addBuchung(aufenthalt);
			
		}
		
		public static boolean isBooked(Zimmer zimmer, Aufenthalt aufenthalt){
			
			for(Buchung buchung : zimmer.getBuchungen()){
				
				if (buchung.getAufenthalt().overlaps(aufenthalt)) return true;
				
			}
			
			return false;
			
		}

}
