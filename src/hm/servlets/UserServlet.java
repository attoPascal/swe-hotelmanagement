package hm.servlets;

import hm.exceptions.DAOException;
import hm.exceptions.UserException;
import hm.managers.UserManagement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManagement um;
	
	public UserServlet() {
		um = new UserManagement();
	}
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "login":
				login(request, response);
				break;
			
			default:
				out.write("Ungültige Aktion");
			}
		} else {
			out.write("Ungültige Aktion");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "logout":
				logout(request, response);
				break;
			
			default:
				out.write("Ungültige Aktion");
			}
		} else {
			out.write("Ungültige Aktion");
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username != null && password != null) {
			try {
				um.instantiateDAO();
				
				if (um.checkLogin(username, password)) {
					session.setAttribute("user", um.getUser(username));
					
					String redirect = (String) session.getAttribute("redirect");
					response.sendRedirect((redirect != null) ? redirect : "allekategorien.jsp");
				} else {
					session.setAttribute("alert", "Falsches Passwort");
					response.sendRedirect("login.jsp");
				}
				
			} catch (UserException | DAOException e) {
				session.setAttribute("alert", e.getMessage());
				response.sendRedirect("login.jsp");
			}
		} else {
			out.write("Ungültige Login-Daten");
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		request.getSession().setAttribute("alert", "Logout erfolgreich");
		response.sendRedirect("login.jsp");
	}
}
