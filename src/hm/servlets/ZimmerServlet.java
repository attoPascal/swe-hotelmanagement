package hm.servlets;

import hm.managers.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ZimmerManagement")
public class ZimmerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ZimmerManagement management = new ZimmerManagement();

	/**
	 * Konstruiert das ZimmerManagement-Servlet und Initialisiert DAO
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public ZimmerServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		try {
			/**
			 * hier wird instantiateDAO innerhalb des try-catch Blocks
			 * aufgerufen
			 */
			management.instantiateDAO("data.ser");

			String hotelName = request.getParameter("hotel");
			String katName = request.getParameter("kategorie");
			int zimmerNummer = Integer.parseInt(request.getParameter("zimmer"));

			if (action != null) {

				switch (action) {
				case "create":
					management.createZimmer(hotelName, zimmerNummer);
					management.setZimmerKategorie(hotelName, katName,
							zimmerNummer);
					response.sendRedirect("zimmerverwalten.jsp?hotel="
							+ hotelName);
					break;
				case "delete":
					management.deleteZimmer(hotelName, zimmerNummer);
					response.sendRedirect("zimmerverwalten.jsp?hotel="
							+ hotelName);
					break;
				case "set":
					management.setZimmerKategorie(hotelName, katName,
							zimmerNummer);
					out.write("Kategorie geaendert\n");
					break;
				default:
					out.write("Ungueltige Aktion");
				}

			} else {
				out.write("Keine Aktion");
			}

		} catch (ClassNotFoundException e) {
			out.write(e.getMessage());

		} catch (FileNotFoundException e) {
			out.write(e.getMessage());

		} catch (IOException e) {
			out.write(e.getMessage());
		}
	}

	public ZimmerManagement getManagement() {
		return management;
	}
}
