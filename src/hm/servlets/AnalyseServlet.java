/**
 * 
 */
package hm.servlets;

import hm.Aufenthalt;
import hm.exceptions.UserException;
import hm.managers.AnalyseManagement;
import hm.users.AbstractUser;
import hm.users.Analyst;

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
		
		HttpSession session = req.getSession();
		
		Object o = session.getAttribute("user");
		Analyst user;		
		

		String date = req.getParameter("anfang");
		
		String hotel = req.getParameter("hotel");
		
		out.println("<!DOCTYPE html>"
				+ "<html>");
		
		req.getRequestDispatcher("/inc/head.jsp").include(req, res);
		
		out.println("<body>"
				+ "<main class=\"container\">");

		req.getRequestDispatcher("/inc/nav.jsp").include(req, res);
		if (o instanceof Analyst){
			user = (Analyst)o;
		}else {
				try {
					throw new UserException("User ist kein HotelGast");
				} catch (UserException e) {
					
					out.println("<h1> Benutzer hat nicht die notwendigen Rechte <h1>");
					
					out.println("</main>"
							+ "</body>"
							+ "</html>");
					return;
				}
		}
		
		out.println("<div class=\"analyst1\"><table class=\"table table-striped analyst1\">");

		int tage = Integer.parseInt(req.getParameter("tage"));
		
		out.println("<h1> Statistik für "+ hotel + "</h1>"
				+ "<h2> Von "+ date + " über eine Zeitspanne von "
				+ tage + " Tagen</h2>");
		
		if(user.isCanViewRevenue()) out.println(viewRevenue(hotel, date, tage));
		if(user.isCanViewBookings())out.println(viewBookings(hotel, date, tage));
		if(user.isCanViewRooms())out.println(viewRooms(hotel, date, tage));
		
		out.println("</table></div>");

		
		
		if (req.getParameter("hotel2") != null) {
			out.println("<div class=\"analyst2\"><table class=\"table table-striped analyst2\">");
			String hotel2 = req.getParameter("hotel2");
			out.println("<h1> Statistik für "+ hotel2 + "</h1>"
					+ "<h2> Von "+ date + " über eine Zeitspanne von "
					+ tage + " Tagen</h2>");
			if(user.isCanViewRevenue()) out.println(viewRevenue(hotel2, date, tage));
			if(user.isCanViewBookings())out.println(viewBookings(hotel2, date, tage));
			if(user.isCanViewRooms())out.println(viewRooms(hotel2, date, tage));
			out.println("</table></div>");

		}
		
		out.println("</main>"
				+ "</body>"
				+ "</html>");
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req,res);
	}

	public String viewRooms(String name, String date, int tage){
		String output;
		try{
			Aufenthalt aufenthalt = new Aufenthalt (new SimpleDateFormat("yyyy-MM-dd").parse(date),tage);
			output = "<thead><tr><th>Zimmer</th></tr></thead>";
			output += "<tr><td>Anzahl der Zimmer </th>"
					+ "<td>"+ management.getNumberOfRooms(name) + "</td></tr>";
			output += "<tr><td>Anzahl der freien Zimmer </th>"
					+ "<td>"+ management.getFreeRooms(name, aufenthalt).size() + "</td></tr>";
			output += "<tr><td>Anzahl der gebuchten Zimmer </th>"
					+ "<td>"+ management.getBookedRooms(aufenthalt, name).size() + "</td></tr>";
			output += "<tr><td>Durschnittlicher Zimmerpreis </th>"
					+ "<td>"+ String.format("%.2f", management.getAverageRoomPrice(name)/100.) + " &#8364</td></tr>";
			}catch(Exception e){
				output = "<h1> Ein Fehler ist aufgetreten </h1>";
				e.printStackTrace();
			}
		
		return output;
	}
	
	public String viewBookings(String name, String date, int tage){
		String output;
		try{
			Aufenthalt aufenthalt = new Aufenthalt (new SimpleDateFormat("yyyy-MM-dd").parse(date),tage);
			output = "<thead><tr><th>Buchungen</th></tr></thead>";
			output += "<tr><td>Anzahl der gebuchten Zimmer</th>"
					+ "<td>"+ management.getBookedRooms(aufenthalt, name).size() + "</td></tr>";
			output += "<tr><td>Meistgebuchte Kategorie</th>"
					+ "<td>"+ management.getBestCategory(aufenthalt, name) + "</td></tr>";
			output += "<tr><td>Meistgebuchter Service </th>"
					+ "<td>"+ management.getBestService(aufenthalt, name) + "</td></tr>";
			output += "<tr><td>Meistgebuchter Monat </th>"
					+ "<td>"+ management.getMostBookedMonth(aufenthalt, name) + "</td></tr>";
			output += "<tr><td>Anzahl der Buchungen </th>"
					+ "<td>"+ management.getNumberOfBookings(aufenthalt, name) + "</td></tr>";
			output += "<tr><td>Anzahl an möglichen Buchungen </th>"
					+ "<td>"+ management.getPossibleBookings(aufenthalt, 1, name) + "</td></tr>";
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
			output = "<thead><tr><th>Einnahmen</th></tr></thead>";
			output += "<tr><td>Durschnittlicher Servicepreis </th>"
					+ "<td>"+ String.format("%.2f", management.getAverageServicePrice(name)/100.) + " &#8364</td></tr>";
			output += "<tr><td>Durschnittliche Einnahme pro Buchung </th>"
					+ "<td>"+ String.format("%.2f", management.getAverageBookingPrice(name, aufenthalt)/100.) + " &#8364</td></tr>";
			output += "<tr><td>Einnahmen durch Zimmer </th>"
					+ "<td>"+ String.format("%.2f", management.getTotalRevenue(aufenthalt, name)/100.) + " &#8364</td></tr>";
			output += "<tr><td>Einnahmen durch Services </th>"
					+ "<td>"+ String.format("%.2f", management.getServiceRevenue(aufenthalt, name)/100.) + " &#8364</td></tr>";
			output += "<tr><td>Durschnittliche Einnahme pro Buchung pro Tag </th>"
					+ "<td>"+ String.format("%.2f", management.getAverageBookingPricePerDay(name, aufenthalt)/100.) + " &#8364</td></tr>";
			
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
