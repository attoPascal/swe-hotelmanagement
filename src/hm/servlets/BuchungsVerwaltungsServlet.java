package hm.servlets;

import hm.Buchung;
import hm.exceptions.UserException;
import hm.managers.*;
import hm.users.Hotelier;
import hm.Aufenthalt;
import hm.Hotel;
import hm.exceptions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.text.ParseException;

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
			// reading the user input
			String hotelName = request.getParameter("hotel");
			String id = request.getParameter("id");

			String dateString = request.getParameter("year") + "-" + request.getParameter("months") + "-" + request.getParameter("day");
			int duration = Integer.parseInt(request.getParameter("duration"));

			Object o = request.getSession().getAttribute("user");
			Hotelier hotelier;
			
			if (o instanceof Hotelier) {
				hotelier = (Hotelier) o;
			} else {
				throw new UserException("User ist kein Hotelier");
			}
			
			Hotel hotel = management.getDAO().getHotelByName(hotelName);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = df.parse(dateString);
	
			out.write(management.editAufenthalt(hotel.getBuchungByID(Integer.parseInt(id)), new Aufenthalt(date, duration), hotel));
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
			
		} catch (UserException e) {
			out.write(e.getMessage());
			
		} catch (ParseException e) {
			out.write(e.getMessage());
			
		} catch (BuchungsException e) {
			out.write(e.getMessage());
		}
	}
}
