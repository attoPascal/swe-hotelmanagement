package hm;

public class ZimmerManagement {

		public void createZimmer(Hotel hotel, int nummer){
			
			hotel.addZimmer(new Zimmer(nummer));
			
		}
		
		public void deleteZimmer(Hotel hotel, int nummer){
			Zimmer zimmer = hotel.getZimmer(nummer);
			hotel.removeZimmer(zimmer);
			
		}
		
		public void setZimmerKategorie(Hotel hotel, Kategorie kategorie, int nummer){
			Zimmer zimmer = hotel.getZimmer(nummer);
			kategorie.addZimmer(zimmer);
			
		}


}
