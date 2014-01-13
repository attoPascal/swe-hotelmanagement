package hm.servlets;

import hm.Hotel;
import hm.managers.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/KategorieServlet")
public class KategorieServlet extends HttpServlet {

	private static final long serialVersionUID = 2L;

	private KategorieManagement management = new KategorieManagement();

	/**
	 * Default constructor.
	 */
	public KategorieServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			/**
			 * hier wird instantiateDAO innerhalb des try-catch Blocks
			 * aufgerufen
			 */
			management.instantiateDAO();

			String action = request.getParameter("action");
			String katName = request.getParameter("name");
			String hotelName = request.getParameter("hotel");

			/**
			 * Hotel-Objekt aus dem Speicher holen
			 */
			Hotel hotel = management.getDAO().getHotelByName(hotelName);

			if (action != null) {

				if (action.equals("delete")) {
					management.removeKategorie(katName, hotel.getName());

					//out.write("Kategorie erfolgreich geloescht\n");
					response.sendRedirect("kategorienverwalten.jsp?hotel=" + hotelName);
				
				} else {
					int preis = Integer.parseInt(request.getParameter("preis"));
					String ausstattung = request.getParameter("ausstattung");
					
					if (action.equals("create")) {
						management
								.createCategority(katName, ausstattung, preis, hotel.getName());

						//out.write("Neue Kategorie erstellt\n");
						response.sendRedirect("kategorienverwalten.jsp?hotel=" + hotelName);

					} else if (action.equals("edit")) {
						String newName = request.getParameter("newname");

						management.editCategory(katName, newName, preis,
								ausstattung, hotel.getName());

						//out.write("Kategorie bearbeitet\n");
						//out.write("Fuer Hotel: " + hotel + "\n");
						//out.write("Neuer Name: " + newName + "\n");
						//out.write("Preis " + preis + "\n");
						//out.write("Ausstattung: " + ausstattung);
						response.sendRedirect("kategorienverwalten.jsp?hotel=" + hotelName);
					}
				}

			} else {
				out.write("No action. Nothing to do.");
			}
			management.getDAO().saveHotel(hotel);

		} catch (ClassNotFoundException e) {
			out.write(e.getMessage());

		} catch (FileNotFoundException e) {
			out.write(e.getMessage());

		} catch (IOException e) {
			out.write(e.getMessage());

		} catch (NumberFormatException e) {
			out.write("Bitte geben Sie eine Zahl im Preisfeld ein");

		} /*catch (NullPointerException e) {
			out.write("Hotel nicht vorhanden");
		}*/
	}
	
	public KategorieManagement getManagement() {
		return management;
	}
}
