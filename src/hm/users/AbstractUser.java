package hm.users;

import java.io.Serializable;

/**
 * Generischer Benutzer
 */
public abstract class AbstractUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Benutzername des Benutzers
	 */
	private String username;
	
	/**
	 * Passwort des Benutzers
	 */
	private String password;
	
	/**
	 * Konstruiert einen neuen User
	 * @param username Username
	 * @param password Passwort
	 */
	public AbstractUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * @return Name (username) des Benutzers
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return Password des Users
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
