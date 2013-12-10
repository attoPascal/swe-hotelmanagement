package hm.servlets;

import hm.Hotel;
import hm.Kategorie;
import hm.Zimmer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

public class BuchungsManagement extends HttpServlet { 
  /**
	 * 
	 */
	private static final long serialVersionUID = 4L;

protected void doGet(HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException 
  {
    // reading the user input
    String name = request.getParameter("name");    
    PrintWriter out = response.getWriter();
    out.println (createHotel(name));  
  }  

	String createHotel(String name){
		
		Hotel hotel = new Hotel(name);
		
		hotel.addZimmer(new Zimmer(5));
		
		hotel.addKategorie(new Kategorie("Suite",50,"Fernseher, Waschbecken"));
		
		hotel.getKategorie("Suite").addZimmer(hotel.getZimmer(5));
	
		return hotel.getName();
	
	}

}