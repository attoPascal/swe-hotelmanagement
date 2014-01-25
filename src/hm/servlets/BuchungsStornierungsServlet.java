package hm.servlets;

import hm.Hotel;
import hm.managers.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

/**
 * Servlet-Klasse zur Stornierung von Buchungen
 */
@WebServlet("/BuchungsStornierungsServlet")
public class BuchungsStornierungsServlet extends HttpServlet {

	private static final long serialVersionUID = 4L;

	private BuchungsManagement management = new BuchungsManagement();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		try {
			// reading the user input
			int id = Integer.parseInt(request.getParameter("id"));
			int nummer = Integer.parseInt(request.getParameter("nummer"));
			String hotelName = request.getParameter("hotel");
			
			Hotel hotel = management.getDAO().getHotelByName(hotelName);

			management.removeBuchung(
					id,						//Buchung.getid(id),
					nummer,					//Zimmer.getNummer(Nummer),
					hotel.getName()
			);

			out.write("Die Stornierung ihrer Buchung #" + id + 
					"des Zimmers" + nummer + 
					"im Hotel" + hotel.getName() + 
					"war erfolgreich.");

			//TODO: Ins Management verlegen
			management.getDAO().saveHotel(hotel);

		} catch (NullPointerException e) {
			out.write("Ihre Stornierung konnte leider nicht abgeschlossen werden."
					+ "\n Versuchen Sie es in 5 Minuten erneut.");

		} catch (ClassNotFoundException e) {
			out.write(e.getMessage());

		} catch (FileNotFoundException e) {
			out.write(e.getMessage());

		} catch (IOException e) {
			out.write(e.getMessage());
			
		} catch (NumberFormatException e) {
			out.write(e.getMessage());
		}
	}
}