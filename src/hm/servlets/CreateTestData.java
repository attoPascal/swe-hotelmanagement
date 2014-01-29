package hm.servlets;

import hm.Hotel;
import hm.Kategorie;
import hm.Service;
import hm.Zimmer;
import hm.dao.DAO;
import hm.dao.SerializedDAO;
import hm.exceptions.ServiceException;
import hm.users.AbstractUser;
import hm.users.Analyst;
import hm.users.HotelGast;
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
		Hotel h3 = new Hotel("NewSharkyFish");
		
		Zimmer z1 = new Zimmer(101);
		Zimmer z2 = new Zimmer(102);
		Zimmer z3 = new Zimmer(103);
		Zimmer z4 = new Zimmer(104);
		Zimmer z5 = new Zimmer(105);
		
		Zimmer z6 = new Zimmer(101);
		Zimmer z7 = new Zimmer(201);
		Zimmer z8 = new Zimmer(301);
		Zimmer z9 = new Zimmer(401);
		
		Zimmer z10 = new Zimmer(101);
		Zimmer z11 = new Zimmer(102);
		Zimmer z12 = new Zimmer(103);
		Zimmer z13 = new Zimmer(104);
		Zimmer z14 = new Zimmer(201);
		Zimmer z15 = new Zimmer(202);
		Zimmer z16 = new Zimmer(203);
		Zimmer z17 = new Zimmer(204);
		
		h1.addZimmer(z1);
		h1.addZimmer(z2);
		h1.addZimmer(z3);
		h1.addZimmer(z4);
		h1.addZimmer(z5);
		
		h2.addZimmer(z6);
		h2.addZimmer(z7);
		h2.addZimmer(z8);
		h2.addZimmer(z9);

		h3.addZimmer(z10);
		h3.addZimmer(z11);
		h3.addZimmer(z12);
		h3.addZimmer(z13);
		h3.addZimmer(z14);
		h3.addZimmer(z15);
		h3.addZimmer(z16);
		h3.addZimmer(z17);
		
		Kategorie kat1 = new Kategorie("Einzel", 5000, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 10000, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 20000, "Bad & Klo, Affenbutler");
		
		Kategorie kat4 = new Kategorie("Einzel", 6000, "nix");
		Kategorie kat5 = new Kategorie("Doppel", 8000, "nix");
		Kategorie kat6 = new Kategorie("Tripel", 12000, "nix");
		Kategorie kat7 = new Kategorie("Quadrupel", 15000, "nix");
		
		Kategorie kat8 = new Kategorie("Einzel", 4000, "Bad");
		Kategorie kat9 = new Kategorie("Doppel", 12000, "Bad, Balkon, Butler");
		Kategorie kat10 = new Kategorie("Luxus", 25000, "Bad, Balkon, Affenbutler");
		
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		
		kat4.addZimmer(z6);
		kat5.addZimmer(z7);
		kat6.addZimmer(z8);
		kat7.addZimmer(z9);
		
		kat8.addZimmer(z10);
		kat8.addZimmer(z11);
		kat8.addZimmer(z12);
		kat8.addZimmer(z13);
		kat9.addZimmer(z14);
		kat9.addZimmer(z15);
		kat10.addZimmer(z16);
		kat10.addZimmer(z17);
		
		h1.addKategorie(kat1);
		h1.addKategorie(kat2);
		h1.addKategorie(kat3);
		
		h2.addKategorie(kat4);
		h2.addKategorie(kat5);
		h2.addKategorie(kat6);
		h2.addKategorie(kat7);
		
		Service s1 = new Service("Sauna", "30min, 2 Aufguesse", 5000);
		Service s2 = new Service("Massage", "20min, ohne Happy End", 7500);
		Service s3 = new Service("Massage extra", "20min, mit Happy End", 5000);
		Service s4 = new Service("Fruehstueck", "Schinken, Kaese, Ei", 1500);
		Service s5 = new Service("Kalte Platte", "Schinken, Kaese, Ei, Spargel", 3000);
		Service s6 = new Service("Jetski", "Jetski fahren, 2 Stunden", 35000);
		
		try {
			h1.addService(s1);
			h1.addService(s2);
			h1.addService(s3);
			h2.addService(s4);
			h2.addService(s5);
			h3.addService(s6);
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		
		Hotelier u1 = new Hotelier("Hotelier1", "h1");
		u1.addHotel(h1);
		u1.addHotel(h2);
		
		HotelGast u2 = new HotelGast("Gast1", "g1", "12345");
		
		Analyst u3 = new Analyst("Analyst1", "a1", true, true, true);
		Analyst u4 = new Analyst("Analyst2", "a2", true, false, true);
		Analyst u5 = new Analyst("Analyst3", "a3", false, true, false);
		Analyst u6 = new Analyst("Analyst4", "a4", false, false, false);


		try {
			//out.write("DAO in " + request.getSession().getServletContext().getRealPath("data.ser") + "<br>");
			DAO dao = SerializedDAO.getInstance();
			//DAO dao = new SerializedDAO(request.getSession().getServletContext().getRealPath("data.ser"));
			dao.saveHotel(h1);
			dao.saveHotel(h2);
			dao.saveUser(u1);
			dao.saveUser(u2);
			dao.saveUser(u3);
			dao.saveUser(u4);
			dao.saveUser(u5);
			dao.saveUser(u6);

			Hotel h4 = dao.getHotelByName("CrazySharkyFish");
			ArrayList<Kategorie> katList = h4.getKategorien();
		
			for (Kategorie k : katList) {
				out.write(k.getName() + ": ");
			
				for (Entry<Integer, Zimmer> e : k.getZimmerMap().entrySet()) {
					out.write(e.getKey() + " ");
				}
			
				out.write("<br>");
			}
			
			for (AbstractUser u : dao.getUserList()) {
				if (u instanceof Hotelier) {
					Hotelier h = (Hotelier) u;
					out.write("Hotelier '" + h.getUsername() + "' verwaltet folgende Hotels:<br>");
					
					for (String s : h.getHotels()) {
						out.write(s + " ");
					}
					out.write("<br>");
					
				} else if (u instanceof HotelGast) {
					out.write(u.getUsername() + " ist ein Gast.<br>");
				}
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
