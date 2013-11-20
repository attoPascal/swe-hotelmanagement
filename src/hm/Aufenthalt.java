package hm;

import java.util.Date;

public class Aufenthalt {


	private Date anfang;
	

	private Date ende;

	
	public Date getAnfang() {
		return anfang;
	}


	public void setAnfang(Date anfang) {
		this.anfang = anfang;
	}


	public Date getEnde() {
		return ende;
	}


	public void setEnde(Date ende) {
		this.ende = ende;
	}


	public Aufenthalt (Date anfang, int tage){
		
		this.anfang = anfang;
		
		ende  = new Date(anfang.getTime() + tage *(1000*60*60*24));
		
	}
	
	
	public int getDays(){
		
		return (int)(anfang.getTime() - ende.getTime()) / (1000*60*60*24);
	}
	
	public boolean overlaps(Aufenthalt aufenthalt){
		
		if ((this.anfang.getTime() > aufenthalt.getAnfang().getTime() && this.anfang.getTime()  < aufenthalt.getEnde().getTime() ) || (this.anfang.getTime()  < aufenthalt.getAnfang().getTime()  && this.ende.getTime()  > aufenthalt.getAnfang().getTime() ))
			{return true;}
		else return false;
		
	}
	
}
