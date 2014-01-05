package hm.managers;

import java.io.IOException;
import java.util.ArrayList;

import hm.dao.DAO;
import hm.dao.SerializedDAO;
import hm.exceptions.DAOException;
import hm.exceptions.UserException;
import hm.users.AbstractUser;

public class UserManagement {
	private DAO dao;
	
	/**
	 * Überprüft ob die übergebene Username/Passwort-Kombination korrekt ist
	 */
	public boolean checkLogin(String username, String password) throws UserException, DAOException {
		return getUser(username).getPassword().equals(password);
	}
	
	/**
	 * Gibt den User mit dem übergebenen Username zurück
	 */
	public AbstractUser getUser(String username) throws UserException, DAOException  {
		ArrayList<AbstractUser> list;
		try {
			list = dao.getUserList();
		} catch (ClassNotFoundException | IOException e) {
			throw new DAOException("Fehler beim Zugriff auf die Datenspeicherung", e);
		}
		
		for (AbstractUser u : list) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		
		throw new UserException("Dieser User existiert nicht.");
	}
	
	/**
	 * Instanziert DAO
	 */
	public void instantiateDAO() throws IOException {
		dao = SerializedDAO.getInstance();
	}
}
