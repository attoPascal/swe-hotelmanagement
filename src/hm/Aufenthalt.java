package hm;

import java.io.Serializable;
import java.util.Date;

public class Aufenthalt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date anfang;
	private Date ende;

	public Aufenthalt(Date anfang, int tage) {
		this.anfang = anfang;
		this.ende = new Date(anfang.getTime() + tage * (1000 * 60 * 60 * 24));
	}

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

	public int getDays() {
		return (int) (anfang.getTime() - ende.getTime())
				/ (1000 * 60 * 60 * 24);
	}

	public boolean overlaps(Aufenthalt aufenthalt) {
		
		long a1 = this.anfang.getTime();
		long a2 = aufenthalt.getAnfang().getTime();
		long e1 = this.ende.getTime();
		long e2 = aufenthalt.getEnde().getTime();
		
	
		//a1 a2 e1 e2 || a1 a2 e2 e1
		if (a1 < a2 && a2 < e1){
			return true;
		}
		
		//a2 a1 e2 e1 || a1 a2 e2 e1 
		if (a1 > e2 && a2 < e2){
			return true;
		}
		
		// a1  e1
		// a2  e2
		if (a1 == a2 && e1 ==  e2){
			return true;
		}
		
		//a2 a1 e1 e2
		if (a2 < a1 && a1 < e2){
			return true;
					
		}else
			return false;
	}

	@Override
	public String toString() {
		return "Aufenthalt [anfang=" + anfang + ", ende=" + ende + "]";
	}
	
	
	
}
