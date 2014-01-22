/**
 * 
 */
package hm.servlets;

import hm.managers.AnalyseManagement;

import java.io.*;
import java.sql.Date;

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

		
		int tage = Integer.parseInt(req.getParameter("tage"));
		
		out.println("<h1>"+ date + "</h1>");
		out.println("<h1>"+ tage + "</h1>");
		
		out.println(viewRooms(hotel, date, tage));
		
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
	
	public String viewRevenue(String hotel, String date, int tage){
		String output;
		try{
			output ="<h1>Durschnittlicher Zimmerpreis </h1><p>"+ management.getAverageRoomPrice(hotel)/100. + "</p>";
			}catch(Exception e){
				output = "<h1> Ein Fehler ist aufgetreten </h1>";
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
