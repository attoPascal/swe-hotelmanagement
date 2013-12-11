package hm.servlets;

import hm.Aufenthalt;
import hm.Hotel;
import hm.Kategorie;
import hm.Zimmer;
import hm.dao.DAO;
import hm.dao.SerializedDAO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BuchungsManagement extends HttpServlet {
	/**
	 * Servlet implementation class BuchungsManagement
	 */
	private static final long serialVersionUID = 4L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new SerializedDAO("data.ser");
		
		//// reading the user input
		String hotelName = request.getParameter("hotel");
		String katName   = request.getParameter("kategorie");
		
		int day = Integer.parseInt(request.getParameter("day"));
		int month = Integer.parseInt(request.getParameter("months"));
		int year = Integer.parseInt(request.getParameter("year"));
		int duration = Integer.parseInt(request.getParameter("duration"));
    	
    	Hotel hotel = dao.getHotelByName(hotelName);
    	Calendar c = new GregorianCalendar ();
    	c.set(day, year, month-1, 0, 0);
    	PrintWriter out = response.getWriter();

    	try{
    	
    		int zimmernummer = neueBuchung(hotel.getKategorie(katName), new Aufenthalt(new Date(c.getTimeInMillis()), duration));
    		
    		out.println("Ihre Buchung war erfolgreich, ihre Zimmernummer ist " + zimmernummer);
    		
    		out.println("<br>Datum ihrer Buchung: "+ new Date(c.getTimeInMillis()).toString());
    		
    		out.println("<br>Gesamtkosten: " + hotel.getKategorie(katName).getPreis()*duration);
    		
    		out.println(hotel.getKategorie(katName).toString());
    			
    		dao.saveHotel(hotel);
    		
    		
    	}catch(NullPointerException e){
    		
    		out.println("Es ist zu diesem Zeitpunkt leider kein Zimmer mehr frei!");
    		
    	}

	}

	/**
	 * Erstellt eine neue Buchung
	 * 
	 * @param kategorie Kategorie des Zimmers
	 * @param aufenthalt Zeitraum, der gebucht wurde
	 * @return gibt die Nummer des Zimmers zurueck, das gebucht wurde.
	 */
	public int neueBuchung(Kategorie kategorie, Aufenthalt aufenthalt) {

		Zimmer zimmer = kategorie.getZimmer(aufenthalt);
		
		zimmer.addBuchung(kategorie, aufenthalt);
		
		return zimmer.getNummer();
	}



}