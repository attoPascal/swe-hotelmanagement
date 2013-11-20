package hm;

import java.util.ArrayList;

public class BuchungsManagement {


		
		public void neueBuchung(Kategorie kategorie, Aufenthalt aufenthalt){
			
			Zimmer gebuchtesZimmer;
			
			for (Zimmer zimmer : kategorie.getZimmerList()){
				
				if(!(isBooked(zimmer, aufenthalt))){ gebuchtesZimmer = zimmer; break;}
				
			}
			
			gebuchtesZimmer.addBuchung(aufenthalt);
			
		}
		
		public boolean isBooked(Zimmer zimmer, Aufenthalt aufenthalt){
			
			for(Buchung buchung : zimmer.getBuchungen()){
				
				if (buchung.getAufenthalt().overlaps(aufenthalt)) return true;
				
			}
			
			return false;
			
		}

}
