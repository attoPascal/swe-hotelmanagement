package hm.servlets;

import hm.Hotel;
import hm.Kategorie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class KategorieManagement
 */
@WebServlet("/KategorieManagement")
public class KategorieManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public KategorieManagement() {
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
	        case "edit":
	        	String newName = request.getParameter("newname");
	        	int betten = Integer.parseInt(request.getParameter("betten"));
	        	int preis = Integer.parseInt(request.getParameter("preis"));
	        	String ausstattung = request.getParameter("ausstattung");
	        	/*
	        	 * Irgendwo muss ein Hotel-Liste gespeichert sein, um für den
	        	 * String "hotel" ein Hotelobjekt zu bekommen
	        	 */
	        	String hotel = request.getParameter("hotel");
	        	
	        	//editCategory(hotel, name, newName, preis);
	        	
	        	out.write("Kategorie bearbeitet\n");
	        	out.write("Für Hotel: " + hotel + "\n");
	        	out.write("Neuer Name: " + newName + "\n");
	        	out.write("Betten: " + betten + "\n");
	        	out.write("Preis " + preis + "\n");
	        	out.write("Ausstattung: " + ausstattung);
	        }
        } else {
        	out.write("No action. Nothing to do.");
        }
	}
	
	public void editCategority(Hotel hotel, String name, String newName, int preis){
		Kategorie kategorie = hotel.getKategorie(name);
		kategorie.setName(newName);
		kategorie.setPreis(preis);
	}

	public void createCategority(Hotel hotel, String name, int preis){
		hotel.addKategorie(new Kategorie (name, preis));
	}
	
	public void removeKategorie(Hotel hotel, String name){
		Kategorie kategorie = hotel.getKategorie(name);
		hotel.removeKategorie(kategorie);
	}
}
