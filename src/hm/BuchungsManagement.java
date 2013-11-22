package hm;

public class BuchungsManagement {


		
		public void neueBuchung(Kategorie kategorie, Aufenthalt aufenthalt){
									
			kategorie.getZimmer(aufenthalt).addBuchung(kategorie, aufenthalt);
			
		}
		
		public static boolean isBooked(Zimmer zimmer, Aufenthalt aufenthalt){
			
			for(Buchung buchung : zimmer.getBuchungen()){
				
				if (buchung.getAufenthalt().overlaps(aufenthalt)) return true;
				
			}
			
			return false;
			
		}

}
