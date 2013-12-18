package hm.servlets;

import hm.Hotel;
import hm.Kategorie;
import hm.Zimmer;
import hm.dao.DAO;
import hm.dao.SerializedDAO;
import hm.users.Hotelier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateTestData
 */
@WebServlet("/CreateTestData")
public class CreateTestData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		
		Hotel h1 = new Hotel("CrazySharkyFish");
		Hotel h2 = new Hotel("FunkySharkyFish");
		
		Zimmer z1 = new Zimmer(101);
		Zimmer z2 = new Zimmer(102);
		Zimmer z3 = new Zimmer(103);
		Zimmer z4 = new Zimmer(104);
		Zimmer z5 = new Zimmer(105);
		
		Zimmer z6 = new Zimmer(101);
		Zimmer z7 = new Zimmer(201);
		Zimmer z8 = new Zimmer(301);
		Zimmer z9 = new Zimmer(401);
		
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		
		h2.addZimmer(z6);
		h2.addZimmer(z7);
		h2.addZimmer(z8);
		h2.addZimmer(z9);

		
		Kategorie kat1 = new Kategorie("Einzel", 5000, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 10000, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 20000, "Bad & Klo, Affenbutler");
		
		Kategorie kat4 = new Kategorie("Einzel", 6000, "nix");
		Kategorie kat5 = new Kategorie("Doppel", 8000, "nix");
		Kategorie kat6 = new Kategorie("Tripel", 12000, "nix");
		Kategorie kat7 = new Kategorie("Quadrupel", 15000, "nix");
		
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		
		kat4.addZimmer(z6);
		kat5.addZimmer(z7);
		kat6.addZimmer(z8);
		kat7.addZimmer(z9);
		
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		h2.addKategorie(kat4);
		h2.addKategorie(kat5);
		h2.addKategorie(kat6);
		h2.addKategorie(kat7);
		
		Hotelier u1 = new Hotelier("Franz", "pwd1", true, true);
		u1.addHotel(h1);
		u1.addHotel(h2);
		
		try {
			//out.write("DAO in " + request.getSession().getServletContext().getRealPath("data.ser") + "<br>");
			DAO dao = SerializedDAO.getInstance();
			//DAO dao = new SerializedDAO(request.getSession().getServletContext().getRealPath("data.ser"));
			dao.saveHotel(h1);
			dao.saveHotel(h2);
			dao.saveUser(u1);
		
			Hotel h3 = dao.getHotelByName("CrazySharkyFish");
			ArrayList<Kategorie> katList = h3.getKategorien();
		
			for (Kategorie k : katList) {
				out.write(k.getName() + ": ");
			
				for (Entry<Integer, Zimmer> e : k.getZimmerMap().entrySet()) {
					out.write(e.getKey() + " ");
				}
			
				out.write("<br>");
			}
			
			Hotelier u2 = (Hotelier) dao.getUserList().get(0);
			out.write("Hotelier '" + u2.getUsername() + "' verwaltet folgende Hotels:<br>");
			
			for (String s : u2.getHotels()) {
				out.write(s + " ");
			}
		
		} catch (ClassNotFoundException e) {
			out.write(e.getMessage());

		} catch (FileNotFoundException e) {
			out.write(e.getMessage());

		} catch (IOException e) {
			out.write(e.getMessage());
		}
	}
}
