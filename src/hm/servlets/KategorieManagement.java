package hm.servlets;

import hm.Hotel;
import hm.Kategorie;
import hm.ManagementDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
	private static final long serialVersionUID = 2L;

	/**
	 * "Model"-Klasse
	 */
	private ManagementDAO management = new ManagementDAO();

	/**
	 * Default constructor.
	 */
	public KategorieManagement() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		int preis = Integer.parseInt(request.getParameter("preis"));
		String ausstattung = request.getParameter("ausstattung");
		/**
		 * Hotel-Objekt aus dem Speicher holen
		 */
		Hotel hotel = management.getHotelByName(request.getParameter("hotel"));

		if (action != null) {

			if (action.equals("create")) {
				this.createCategority(hotel, name, ausstattung, preis);

				out.write("Neue Kategorie erstellt\n");

			} else if (action.equals("delete")) {
				this.removeKategorie(hotel, name);

				out.write("Kategorie erfolgreich gelöscht\n");

			} else if (action.equals("edit")) {
				String newName = request.getParameter("newname");

				this.editCategory(hotel, name, newName, preis, ausstattung);

				out.write("Kategorie bearbeitet\n");
				out.write("Für Hotel: " + hotel + "\n");
				out.write("Neuer Name: " + newName + "\n");
				out.write("Preis " + preis + "\n");
				out.write("Ausstattung: " + ausstattung);
			}

		} else {
			out.write("No action. Nothing to do.");
		}
	}

	public void editCategory(Hotel hotel, String name, String newName,
			int preis, String ausstattung) {

		Kategorie kat = hotel.getKategorie(name);
		kat.setName(newName);
		kat.setPreis(preis);
		kat.setAusstattung(ausstattung);

		hotel.setKategorie(kat);
	}

	public void createCategority(Hotel hotel, String name, String ausstattung,
			int preis) {
		Kategorie kat = new Kategorie(name, preis, ausstattung);

		hotel.addKategorie(kat);
		management.saveKategorie(kat);
		management.saveHotel(hotel);
	}

	public void removeKategorie(Hotel hotel, String name) {
		Kategorie kat = hotel.getKategorie(name);
		hotel.removeKategorie(kat);

		management.saveHotel(hotel);
	}
}
