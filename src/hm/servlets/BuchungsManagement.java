package hm.servlets;

import hm.Aufenthalt;
import hm.Buchung;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;

public class BuchungsManagement extends HttpServlet {
	/**
	 * Servlet implementation class BuchungsManagement
	 */
	private static final long serialVersionUID = 4L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// reading the user input
				
		String name = request.getParameter("select");
		
		int day = Integer.parseInt(request.getParameter("day"));
		int month = Integer.parseInt(request.getParameter("months"));
		int year = Integer.parseInt(request.getParameter("year"));
		
		DAO dao = new SerializedDAO("data.ser");
    	
    	Hotel hotel = dao.getHotelByName("CrazySharkyFish");
    	Calendar c = new GregorianCalendar ();
    	c.set(day, year, month-1, 0, 0);
    	PrintWriter out = response.getWriter();

    	try{
    	
    		int zimmernummer = neueBuchung(hotel.getKategorie(name), new Aufenthalt(new Date(c.getTimeInMillis()), 1));
    		
    		out.println("Ihre Buchung war erfolgreich, ihre Zimmernummer ist " + zimmernummer);
    		
    		out.println(hotel.getKategorie(name).toString());
    			
    		dao.saveHotel(hotel);
    		
    		
    	}catch(NullPointerException e){
    		
    		out.println("Es ist zu diesem Zeitpunkt leider kein Zimmer mehr frei!");
    		
    	}

	}

	/**
	 * Erstellt eine neue Buchung
	 * 
	 * @param kategorie
	 * @param aufenthalt
	 * @return
	 */
	public int neueBuchung(Kategorie kategorie, Aufenthalt aufenthalt) {

		Zimmer zimmer = kategorie.getZimmer(aufenthalt);
		
		zimmer.addBuchung(kategorie, aufenthalt);
		
		return zimmer.getNummer();
	}



}