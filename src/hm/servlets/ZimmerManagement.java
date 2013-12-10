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
        dao = new SerializedDAO("/Users/pascal/Documents/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/LittleSharkyFish/data.ser");
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
	        case "set":
	        	int zimmer = Integer.parseInt(request.getParameter("zimmer"));
	        	String kategorie = request.getParameter("kategorie");
	        	String hotel = request.getParameter("hotel");
	        	
	        	//setZimmerKategorie(hotel, kategorie, zimmer);
	        	
	        	out.write("Kategorie geaendert\n");
	        	out.write("fuer Hotel: '" + hotel + "'\n");
	        	out.write("Zimmer Nr " + zimmer + "\n");
	        	out.write("neue Kategorie: '" + kategorie + "'");
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
	
	public void createZimmer(Hotel hotel, int nummer){
		
		hotel.addZimmer(new Zimmer(nummer));
		
	}
	
	public void deleteZimmer(Hotel hotel, int nummer){
		Zimmer zimmer = hotel.getZimmer(nummer);
		hotel.removeZimmer(zimmer);
		
	}
	
	public void setZimmerKategorie(Hotel hotel, Kategorie kategorie, int nummer){
		Zimmer zimmer = hotel.getZimmer(nummer);
		kategorie.addZimmer(zimmer);
		
	}
	
	public DAO getDAO() {
		return dao;
	}
}
