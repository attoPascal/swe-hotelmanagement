package hm.servlets;

import hm.Aufenthalt;
import hm.Buchung;
//import hm.Hotel;
import hm.Kategorie;
import hm.Zimmer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BuchungsManagement")
public class BuchungsManagement2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuchungsManagement2() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        if (action != null) {
	        switch (action) {
	        case "create":
	        	break;
	        case "delete":
	        	break;
	        case "neu":	//
	        	//int zimmer = Integer.parseInt(request.getParameter("zimmer"));
	        	String kategorie = request.getParameter("kategorie");
	        	String aufenthalt = request.getParameter("aufenthalt");	//
	        	//String hotel = request.getParameter("hotel");
	        	
	        	//setZimmerKategorie(hotel, kategorie, zimmer);
	        	
	        	out.write("neue Buchung\n");	//
	        	//out.write("fuer Hotel: '" + hotel + "'\n");
	        	//out.write("Zimmer Nr " + zimmer + "\n");
	        	out.write("Kategorie: '" + kategorie + "'");
	        	out.write("Zeit: '" + aufenthalt + "'");	//
	        }
        } else {
        	out.write("No action. Nothing to do.");
        }
	}
	
	
	public void neueBuchung(Kategorie kategorie, Aufenthalt aufenthalt){
		
		kategorie.getZimmer(aufenthalt).addBuchung(kategorie, aufenthalt);
		
	}
	
	public static boolean isBooked(Zimmer zimmer, Aufenthalt aufenthalt){
		
		for(Buchung buchung : zimmer.getBuchungen()){
			
			if (buchung.getAufenthalt().overlaps(aufenthalt)) return true;
			
		}
		
		return false;
		
	}
}