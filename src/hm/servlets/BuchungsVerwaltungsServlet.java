package hm.servlets;

import hm.Aufenthalt;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Servlet-Klasse zur Verwaltung von Buchungen
 */
@WebServlet("/BuchungsVerwaltungsServlet")
public class BuchungsVerwaltungsServlet extends HttpServlet {

	private static final long serialVersionUID = 6L;

	private BuchungsManagement management = new BuchungsManagement();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		try {
			/**
			 * hier wird instantiateDAO innerhalb des try-catch Blocks
			 * aufgerufen
			 */
			management.instantiateDAO("data.ser");

			// // reading the user input
			String hotelName = request.getParameter("hotel");
			String katName = request.getParameter("kategorie");

			int day = Integer.parseInt(request.getParameter("day"));
			int month = Integer.parseInt(request.getParameter("months"));
			int year = Integer.parseInt(request.getParameter("year"));
			int duration = Integer.parseInt(request.getParameter("duration"));

			Hotel hotel = management.getDAO().getHotelByName(hotelName);
			Calendar c = new GregorianCalendar();
			c.set(year, month - 1, day, 0, 0, 0);

			int zimmernummer = management.createBuchung(
					hotel.getKategorie(katName),
					new Aufenthalt(new Date(c.getTimeInMillis()), duration),hotel.getName());

			out.write("Ihre Buchung war erfolgreich, ihre Zimmernummer ist "
					+ zimmernummer);
			out.write("<br>Datum ihrer Buchung: "
					+ new Date(c.getTimeInMillis()).toString());
			out.write("<br>Gesamtkosten: "
					+ hotel.getKategorie(katName).getPreis() * duration);
			out.write(hotel.getKategorie(katName).toString());

			management.getDAO().saveHotel(hotel);

		} catch (NullPointerException e) {
			out.write("Es ist zu diesem Zeitpunkt leider kein Zimmer mehr frei!");

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
