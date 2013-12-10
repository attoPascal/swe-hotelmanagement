package hm.servlets;

import hm.Hotel;
import hm.Kategorie;
import hm.Zimmer;
import hm.dao.DAO;
import hm.dao.SerializedDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ZimmerManagement
 */
@WebServlet("/ZimmerManagement")
public class ZimmerManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZimmerManagement() {
        super();
        dao = new SerializedDAO("data.ser");
        //dao = new SerializedDAO("/Users/pascal/Documents/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/LittleSharkyFish/data.ser");
        //System.out.println(this.getServletContext().getRealPath("/"));
        //System.out.println(System.getProperty("user.dir"));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
        
        if (action != null) {
        	String hotelName = request.getParameter("hotel");
            String katName   = request.getParameter("kategorie");
            int zimmerNummer = Integer.parseInt(request.getParameter("zimmer"));
        	
	        switch (action) {
	        case "create":
	        	createZimmer(hotelName, zimmerNummer);
	        	setZimmerKategorie(hotelName, katName, zimmerNummer);
	        	response.sendRedirect("zimmerverwalten.jsp?hotel=" + hotelName);
	        	break;
	        case "delete":
	        	deleteZimmer(hotelName, zimmerNummer);
	        	response.sendRedirect("zimmerverwalten.jsp?hotel=" + hotelName);
	        	break;
	        case "set":
	        	setZimmerKategorie(hotelName, katName, zimmerNummer);
	        	out.write("Kategorie geaendert\n");
	        	break;
	        default:
	        	out.write("Ungueltige Aktion");
	        }
	        
        } else {
        	out.write("<p>No action. Nothing to do. Will display test data instead.</p>");
        	
        	ArrayList<Hotel> list = dao.getHotelList();
        	out.write("Number of Hotels: " + list.size() + "<br>");
        	
        	for (Hotel h : list) {
        		out.write("<div>");
        		out.write(h.toString());
        		out.write("</div>");
        	}
        }
	}
	
	public void createZimmer(String hotelName, int nummer){
		Hotel hotel = dao.getHotelByName(hotelName);
		hotel.addZimmer(new Zimmer(nummer));
		dao.saveHotel(hotel);
	}
	
	public void deleteZimmer(String hotelName, int nummer){
		Hotel hotel = dao.getHotelByName(hotelName);
		Zimmer zimmer = hotel.getZimmer(nummer);
		hotel.removeZimmer(zimmer);
		dao.saveHotel(hotel);
	}
	
	public void setZimmerKategorie(String hotelName, String katName, int zimmerNummer){
		Hotel hotel = dao.getHotelByName(hotelName);
		Kategorie kategorie = hotel.getKategorie(katName);
		Zimmer zimmer = hotel.getZimmer(zimmerNummer);
		
		//aus alter Kategorie entfernen
		for (Kategorie k : hotel.getKategorien()) {
			if (k.hasZimmer(zimmerNummer)) {
				k.removeZimmer(zimmerNummer);
			}
		}
		
		//zu neuer Kategorie hinzufuegen
		kategorie.addZimmer(zimmer);
		
		dao.saveHotel(hotel);
	}
	
	public DAO getDAO() {
		return dao;
	}
}
