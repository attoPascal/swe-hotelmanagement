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
		req.getRequestDispatcher("/inc/head.jsp").include(req, res);

		req.getRequestDispatcher("/inc/nav.jsp").include(req, res);
		
		out.println("<h1>"+ hotel + "</h1>");
		
		int tage = Integer.parseInt(req.getParameter("tage"));
		
		out.println("<h1>"+ date + "</h1>");
		out.println("<h1>"+ tage + "</h1>");
		
		out.println(viewRevenue(hotel, date, tage));
		
		if (req.getParameter("hotel2") != null) {
			String hotel2 = req.getParameter("hotel2");
			out.println(viewRooms(hotel2, date, tage));
		}
		
		
	}

	public String viewRooms(String hotel, String date, int tage){
		String output;
		try{
			output ="<h1>Durschnittlicher Zimmerpreis </h1><p>"+ management.getAverageRoomPrice(hotel)/100. + "</p>";
			}catch(Exception e){
				output = "<h1> Ein Fehler ist aufgetreten </h1>";
			}
		
		return output;
	}
	
	public String viewBookings(String hotel, String date, int tage){
		String output;
		try{
			output ="<h1>Durschnittlicher Zimmerpreis </h1><p>"+ management.getAverageRoomPrice(hotel)/100. + "</p>";
			}catch(Exception e){
				output = "<h1> Ein Fehler ist aufgetreten </h1>";
			}
		
		return output;
	}
	
	public String viewRevenue(String name, String date, int tage){
		String output;
		try{
		Aufenthalt aufenthalt = new Aufenthalt (new SimpleDateFormat("yyyy-MM-dd").parse(date),tage);
		
			output = "<h1>Durschnittlicher Zimmerpreis </h1><p>"+ management.getAverageRoomPrice(name)/100. + "</p>";
			output += "<h1>Meistgebuchter Monat </h1><p>"+ management.getMostBookedMonth(aufenthalt, name) + "</p>";
			output += "<h1>Einnahmen durch Zimmer </h1><p>"+ management.getTotalRevenue(aufenthalt, name) + "</p>";
			output += "<h1>Durschnittlicher Servicepreis </h1><p>"+ management.getAverageServicePrice(name) + "</p>";
			output += "<h1>Einnahmen durch Services </h1><p>"+ management.getServiceRevenue(aufenthalt, name) + "</p>";

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
