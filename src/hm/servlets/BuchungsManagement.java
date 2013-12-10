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
	 * 
	 */
	private static final long serialVersionUID = 4L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// reading the user input
		
		Enumeration<String> e = request.getParameterNames();
		
		
		while (e.hasMoreElements()){
		System.out.println(e.nextElement());}
		
		String name = request.getParameter("select");
		
		int day = Integer.parseInt(request.getParameter("day"));
		int month = Integer.parseInt(request.getParameter("months"));
		int year = Integer.parseInt(request.getParameter("year"));
		
		DAO dao = new SerializedDAO("data.ser");
    	
    	Hotel hotel = dao.getHotelByName("CrazySharkyFish");
    	Calendar c = new GregorianCalendar ();
    	c.set(day, year, month-1);
    	int zimmernummer = neueBuchung(hotel.getKategorie(name), new Aufenthalt(new Date(c.getTimeInMillis()), 1));
		
    	PrintWriter out = response.getWriter();
		out.println("Ihre Buchung war erfolgreich, ihre Zimmernummer ist " + zimmernummer);
		out.println(createHotel("name"));
	}

	String createHotel(String name) {

		DAO dao = new SerializedDAO("data.ser");
    	
    	Hotel hotel = dao.getHotelByName("CrazySharkyFish");

		return hotel.toString();

	}

	public int neueBuchung(Kategorie kategorie, Aufenthalt aufenthalt) {

		Zimmer zimmer = kategorie.getZimmer(aufenthalt);
		zimmer.addBuchung(kategorie, aufenthalt);
		
		return zimmer.getNummer();
	}

	public static boolean isBooked(Zimmer zimmer, Aufenthalt aufenthalt) {

		for (Buchung buchung : zimmer.getBuchungen()) {

			if (buchung.getAufenthalt().overlaps(aufenthalt))
				return true;

		}

		return false;

	}

}