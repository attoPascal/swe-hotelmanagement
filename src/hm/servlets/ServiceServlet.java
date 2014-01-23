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
			String serviceName = request.getParameter("serviceName");
			String hotelName = request.getParameter("hotelName");
			String beschreibung;
			String newName;
			int preis;

			if (action != null) {
				switch (action) {
				case "remove":
					ServiceManagement.removeService(serviceName, hotelName);
					break;
				
				case "create":
					preis = Integer.parseInt(request.getParameter("preis"));
					beschreibung = request.getParameter("beschreibung");
					ServiceManagement.createService(serviceName, beschreibung, preis, hotelName);
					break;

				case "edit":
					newName = request.getParameter("newname");
					preis = Integer.parseInt(request.getParameter("preis"));
					beschreibung = request.getParameter("beschreibung");
					ServiceManagement.editService(serviceName, newName, beschreibung, preis, hotelName);
					break;
				}
			}
			
			response.sendRedirect("servicesverwalten.jsp?hotel=" + hotelName);

		} catch (Exception e) {
			response.setContentType("text/html");
			response.getWriter().write(e.getMessage());
		}
	}
	
	public static DAO getDAO() throws IOException {
		return ServiceManagement.getDAO();
	}
}
