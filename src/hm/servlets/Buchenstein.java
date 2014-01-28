package hm.servlets;

import hm.Aufenthalt;
import hm.Buchung;
import hm.Hotel;
import hm.exceptions.BuchungsException;
import hm.exceptions.UserException;
import hm.managers.*;
import hm.users.HotelGast;
import hm.users.Hotelier;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet-Klasse zur Verwaltung von Buchungen
 */
@WebServlet("/Buchenstein")
public class Buchenstein extends HttpServlet {

	private static final long serialVersionUID = 4L;

	private BuchungsManagement management = new BuchungsManagement();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("/inc/head.jsp").include(request, response);
		request.getRequestDispatcher("/inc/nav.jsp").include(request, response);
		//BuchungsVerwaltungsServlet
		try {
			management.instantiateDAO();
			
			//hotel name
			String hotelName = request.getParameter("hotel");
			//BuchungsID
			String id = request.getParameter("id");
			//Zimmernummer
			String nummer = request.getParameter("nummer");
			//Duration
			String duration = request.getParameter("duration");
			//Hotel
			Hotel hotel = management.getDAO().getHotelByName(hotelName);
			//Kategorie
			String katName = request.getParameter("kategorie");
			//User
			Object o = request.getSession().getAttribute("user");
			//Gast
			HotelGast gast;

			//Buchungen verwalten
			if (o instanceof Hotelier) {
				System.out.println("Hotelier");
				System.out.println("buchungen verwalten");
				
				if(id != null && request.getParameter("year")!= null){
					//Date
					String dateString = request.getParameter("year") + "-" + request.getParameter("months") + "-" + request.getParameter("day");
					//Date Formatter
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					//Date
					Date date = df.parse(dateString);
					System.out.println("editAufenthalt");
					out.write(management.editAufenthalt(hotel.getBuchungByID(Integer.parseInt(id)), new Aufenthalt(date, Integer.parseInt(duration)), hotel));
					return;
				}
			//Zimmer Buchen
			} else if(o instanceof HotelGast){
				System.out.println("HotelGast");
				gast = (HotelGast)o;
				
				if(katName != null && request.getParameter("year")!= null){
					//Date
					String dateString = request.getParameter("year") + "-" + request.getParameter("months") + "-" + request.getParameter("day");
					out.write("<main class=\"container\">");
					Buchung buchung = management.createBuchung(hotelName, katName, dateString, Integer.parseInt(duration), gast);
					out.write(management.getBuchungsbestaetigung(buchung));
					out.write("</main>");
					
					return;
				//Buchung stornieren
				}else {
					//buchung entfernen
					management.removeBuchung(Integer.parseInt(id),Integer.parseInt(nummer),hotel.getName(),gast);

					out.write("Die Stornierung ihrer Buchung #" + id + 
							"des Zimmers" + nummer + 
							"im Hotel" + hotel.getName() + 
							"war erfolgreich.");
					
					management.getDAO().saveHotel(hotel);
					
					return;
				}
			//Falscher User
			}else{
				throw new UserException("User hat nicht die Rechte um auf diese Seite zuzugreifen!");
			}
	
			
		} catch (NullPointerException e) {
			e.printStackTrace();

			out.write("Es ist zu diesem Zeitpunkt leider kein Zimmer mehr frei!");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

			out.write(e.getMessage());

		} catch (FileNotFoundException e) {
			e.printStackTrace();

			out.write(e.getMessage());

		} catch (IOException e) {
			e.printStackTrace();

			out.write(e.getMessage());
			
		} catch (NumberFormatException e) {
			e.printStackTrace();

			out.write(e.getMessage());
			
		} catch (UserException e) {
			e.printStackTrace();

			out.write(e.getMessage());
			
		} catch (ParseException e) {
			e.printStackTrace();

			out.write(e.getMessage());
		} catch (BuchungsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}