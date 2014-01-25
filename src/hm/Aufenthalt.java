package hm;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Zeichnet einen zeitlichen Aufenthalt mit Beginn- und Enddatum aus
 */
public class Aufenthalt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date anfang;
	private Date ende;
	
	/**
	 * Konstruiert einen Aufenthalt
	 * @param anfang
	 * @param tage
	 */
	public Aufenthalt(Date anfang, int tage) {
		this.anfang = anfang;
		this.ende = new Date(anfang.getTime() + tage * (1000 * 60 * 60 * 24));
	}

	/**
	 * @return Anfang des Aufenthalts
	 */
	public Date getAnfang() {
		return anfang;
	}

	public void setAnfang(Date anfang) {
		this.anfang = anfang;
	}
	
	/**
	 * @return Ende des Aufenthalts
	 */
	public Date getEnde() {
		return ende;
	}

	public void setEnde(Date ende) {
		this.ende = ende;
	}

	/**
	 * @return Anzahl der Tage eines Aufenthalts
	 */
	public int getDays() {
		return (int) (ende.getTime() - anfang.getTime())
				/ (1000 * 60 * 60 * 24);
	}

	/**
	 * Ueberprueft, ob es zu einem bestimmten zeitraum eine Ueberlappung gibt (fuer die Buchung eines Zimmers)
	 * 
	 * @param aufenthalt Zeitraum, der auf Ueberlappung geprueft wird
	 * @return true, wenn es eine ueberlappung gibt, d.h. es schon verbucht ist; false wenn das Zimmer noch frei ist
	 */
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

	public int getMonth(){
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(anfang);
		cal2.setTime(ende);

		int month1 = cal1.get(Calendar.MONTH);
		int month2 = cal2.get(Calendar.MONTH);
		int month = (month1+month2)/2;
		
		return month;
		
	}
	@Override
	public String toString() {
		return "Aufenthalt [anfang=" + anfang + ", ende=" + ende + "]";
	}
}
