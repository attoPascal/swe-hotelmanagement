package hm.users;

public abstract class AbstractUser {

	/**
	 * Benutzername des Benutzers
	 */
	protected String username;
	
	/**
	 * Passwort des Benutzers
	 */
	protected String password;

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
