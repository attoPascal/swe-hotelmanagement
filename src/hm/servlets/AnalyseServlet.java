/**
 * 
 */
package hm.servlets;

import hm.Aufenthalt;
import hm.managers.AnalyseManagement;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * @author vincent
 *
 */
@WebServlet("/AnalyseServlet")
public class AnalyseServlet extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2200863049293507775L;

	private AnalyseManagement management = new AnalyseManagement();

	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		management.instantiateDAO();
		
		String date = req.getParameter("anfang");
		
		String hotel = req.getParameter("hotel");
		
		out.println("<!DOCTYPE html>"
				+ "<html>");
		
		req.getRequestDispatcher("/inc/head.jsp").include(req, res);
		
		out.println("<body>"
				+ "<main class=\"container\">");

		req.getRequestDispatcher("/inc/nav.jsp").include(req, res);
		
		
		int tage = Integer.parseInt(req.getParameter("tage"));
		
		out.println("<h1> Statistik für "+ hotel + "</h1>"
				+ "<h2> Von "+ date + " über eine Zeitspanne von "
				+ tage + " Tagen</h2>");
		
		out.println(viewRevenue(hotel, date, tage));
		out.println(viewBookings(hotel, date, tage));
		
		if (req.getParameter("hotel2") != null) {
			String hotel2 = req.getParameter("hotel2");
			out.println(viewRevenue(hotel2, date, tage));
			out.println(viewBookings(hotel2, date, tage));
		}
		
		
		out.println("</main>"
				+ "</body>"
				+ "</html>");
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req,res);
	}

	public String viewRooms(String hotel, String date, int tage){
		String output;
		try{
			Aufenthalt aufenthalt = new Aufenthalt (new SimpleDateFormat("yyyy-MM-dd").parse(date),tage);

			output ="<h1>Durschnittlicher Zimmerpreis </h1><p>"+ management.getAverageRoomPrice(hotel)/100. + "</p>";
			}catch(Exception e){
				output = "<h1> Ein Fehler ist aufgetreten </h1>";
			}
		
		return output;
	}
	
	public String viewBookings(String name, String date, int tage){
		String output;
		try{
			Aufenthalt aufenthalt = new Aufenthalt (new SimpleDateFormat("yyyy-MM-dd").parse(date),tage);
	
			output = "<table class=\"table table-striped hotel\">";
	
			output += "<tr><th>Anzahl der gebuchten Zimmer</th>"
					+ "<td>"+ management.getBookedRooms(aufenthalt, name).size() + "</td></tr>";
			output += "<tr><th>Meistgebuchte Kategorie</th>"
					+ "<td>"+ management.getBestCategory(aufenthalt, name) + "</td></tr>";
			output += "<tr><th>Meistgebuchter Service </th>"
					+ "<td>"+ management.getBestService(aufenthalt, name) + "</td></tr>";
			output += "<tr><th>Anzahl der Buchungen </th>"
					+ "<td>"+ management.getNumberOfBookings(aufenthalt, name) + "</td></tr>";
			output += "<tr><th>Anzahl an möglichen Buchungen </th>"
					+ "<td>"+ management.getPossibleBookings(aufenthalt, 1, name) + "</td></tr>";
			output += "</table>";
			}catch(Exception e){
				output = "<h1> Ein Fehler ist aufgetreten </h1>";
				e.printStackTrace();
			}
		
		return output;
	}
	
	public String viewRevenue(String name, String date, int tage){
		String output;
		try{
			Aufenthalt aufenthalt = new Aufenthalt (new SimpleDateFormat("yyyy-MM-dd").parse(date),tage);
	
			output = "<table class=\"table table-striped hotel\">";
	
			output += "<tr><th>Durschnittlicher Zimmerpreis </th>"
					+ "<td>"+ String.format("%.2f", management.getAverageRoomPrice(name)/100.) + " &#8364</td></tr>";
			output += "<tr><th>Durschnittliche Einnahme pro Buchung </th>"
					+ "<td>"+ String.format("%.2f", management.getAverageBookingPrice(name, aufenthalt)/100.) + " &#8364</td></tr>";
			output += "<tr><th>Durschnittliche Einnahme pro Buchung pro Tag </th>"
					+ "<td>"+ String.format("%.2f", management.getAverageBookingPricePerDay(name, aufenthalt)/100.) + " &#8364</td></tr>";
			output += "<tr><th>Meistgebuchter Monat </th>"
					+ "<td>"+ management.getMostBookedMonth(aufenthalt, name) + "</td></tr>";
			output += "<tr><th>Einnahmen durch Zimmer </th>"
					+ "<td>"+ String.format("%.2f", management.getTotalRevenue(aufenthalt, name)/100.) + " &#8364</td></tr>";
			output += "<tr><th>Durschnittlicher Servicepreis </th>"
					+ "<td>"+ String.format("%.2f", management.getAverageServicePrice(name)/100.) + " &#8364</td></tr>";
			output += "<tr><th>Einnahmen durch Services </th>"
					+ "<td>"+ String.format("%.2f", management.getServiceRevenue(aufenthalt, name)/100.) + " &#8364</td></tr>";

			output += "</table>";
			}catch(Exception e){
				output = "<h1> Ein Fehler ist aufgetreten </h1>";
				e.printStackTrace();
			}
		
		return output;
	}
	
	/**
	 * @return the management
	 */
	public AnalyseManagement getManagement() {
		return management;
	}

	/**
	 * @param management the management to set
	 */
	public void setManagement(AnalyseManagement management) {
		this.management = management;
	}
	
	
	
	
}
