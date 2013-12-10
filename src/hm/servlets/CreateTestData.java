package hm.servlets;

import hm.Hotel;
import hm.Kategorie;
import hm.Zimmer;
import hm.dao.DAO;
import hm.dao.SerializedDAO;

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
     * @see HttpServlet#HttpServlet()
     */
    public CreateTestData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		
		Hotel hotel = new Hotel("CrazySharkyFish");
		
		Zimmer z1 = new Zimmer(101);
		Zimmer z2 = new Zimmer(102);
		Zimmer z3 = new Zimmer(103);
		Zimmer z4 = new Zimmer(104);
		Zimmer z5 = new Zimmer(105);
		
		ArrayList<Zimmer> zList = new ArrayList<Zimmer>();
		zList.add(z1);
		zList.add(z2);
		zList.add(z3);
		zList.add(z4);
		zList.add(z5);
		hotel.setZimmerList(zList);
		
		Kategorie kat1 = new Kategorie("Einzel", 50, "Bad");
		Kategorie kat2 = new Kategorie("Doppel", 100, "Bad & Klo");
		Kategorie kat3 = new Kategorie("Suite", 200, "Bad & Klo, Affenbutler");
		
		kat1.addZimmer(z1);
		kat1.addZimmer(z2);
		kat2.addZimmer(z3);
		kat2.addZimmer(z4);
		kat3.addZimmer(z5);
		
		ArrayList<Kategorie> katList = new ArrayList<Kategorie>();
		katList.add(kat1);
		katList.add(kat2);
		katList.add(kat3);
		hotel.setKategorien(katList);
		
		//out.write("DAO in " + request.getSession().getServletContext().getRealPath("data.ser") + "<br>");
		DAO dao = new SerializedDAO("data.ser");
		//DAO dao = new SerializedDAO(request.getSession().getServletContext().getRealPath("data.ser"));
		dao.saveHotel(hotel);
		
		Hotel h2 = dao.getHotelByName("CrazySharkyFish");
		ArrayList<Kategorie> katList2 = h2.getKategorien();
		
		for (Kategorie k : katList2) {
			out.write(k.getName() + ": ");
			
			for (Entry<Integer, Zimmer> e : k.getZimmerMap().entrySet()) {
				out.write(e.getKey() + " ");
			}
			
			out.write("<br>");
		}
	}
}
