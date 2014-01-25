package hm.servlets;

import hm.Buchung;
import hm.exceptions.UserException;
import hm.managers.*;
import hm.users.HotelGast;

import java.io.FileNotFoundException;
import java.io.IOException;

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
@WebServlet("/BuchungsServlet")
public class BuchungsServlet extends HttpServlet {

	private static final long serialVersionUID = 4L;

	private BuchungsManagement management = new BuchungsManagement();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("/inc/head.jsp").include(request, response);

		request.getRequestDispatcher("/inc/nav.jsp").include(request, response);
		try {
			// reading the user input
			String hotelName = request.getParameter("hotel");
			String katName = request.getParameter("kategorie");
			
			String dateString = request.getParameter("year") + "-" + request.getParameter("months") + "-" + request.getParameter("day");
			int duration = Integer.parseInt(request.getParameter("duration"));
			
			Object o = request.getSession().getAttribute("user");
			HotelGast gast;
			
			if (o instanceof HotelGast) {
				gast = (HotelGast) o;
			} else {
				throw new UserException("User ist kein HotelGast");
			}
			
			Buchung buchung = management.createBuchung(hotelName, katName, dateString, duration, gast);
			out.write(management.getBuchungsbestaetigung(buchung));

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
		}
	}
}