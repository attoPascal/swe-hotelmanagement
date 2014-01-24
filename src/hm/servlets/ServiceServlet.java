package hm.servlets;

import hm.dao.DAO;
import hm.managers.ServiceManagement;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServiceServlet")
public class ServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			String serviceName;
			String hotelName;
			String beschreibung;
			String newName;
			String dateString;
			int preis;
			int buchungsID;

			if (action != null) {
				switch (action) {
				case "remove":
					serviceName = request.getParameter("serviceName");
					hotelName = request.getParameter("hotelName");
					ServiceManagement.removeService(serviceName, hotelName);
					response.sendRedirect("servicesverwalten.jsp?hotel=" + hotelName);
					break;
				
				case "create":
					serviceName = request.getParameter("serviceName");
					hotelName = request.getParameter("hotelName");
					preis = Integer.parseInt(request.getParameter("preis"));
					beschreibung = request.getParameter("beschreibung");
					ServiceManagement.createService(serviceName, beschreibung, preis, hotelName);
					response.sendRedirect("servicesverwalten.jsp?hotel=" + hotelName);
					break;

				case "edit":
					serviceName = request.getParameter("serviceName");
					hotelName = request.getParameter("hotelName");
					newName = request.getParameter("newName");
					preis = Integer.parseInt(request.getParameter("preis"));
					beschreibung = request.getParameter("beschreibung");
					ServiceManagement.editService(serviceName, newName, beschreibung, preis, hotelName);
					response.sendRedirect("servicesverwalten.jsp?hotel=" + hotelName);
					break;
				
				case "order":
					hotelName = request.getParameter("hotelName");
					serviceName = request.getParameter("serviceName");
					dateString = request.getParameter("dateString");
					buchungsID = Integer.parseInt(request.getParameter("buchungsID"));
					ServiceManagement.serviceBuchen(serviceName, dateString, buchungsID, hotelName);
					response.sendRedirect("servicebuchen.jsp?hotelName=" + hotelName + "&buchungsID=" + buchungsID);
					break;
				}
			}
		} catch (Exception e) {
			response.setContentType("text/html");
			response.getWriter().write(e.getMessage());
		}
	}
	
	public static DAO getDAO() throws IOException {
		return ServiceManagement.getDAO();
	}
}
