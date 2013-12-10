package hm.users;

public abstract class AbstractUser {

	/**
	 * Benutzername des Benutzers
	 * @uml.property  name="username"
	 */
	protected String username;
	
	/**
	 * Passwort des Benutzers
	 * @uml.property  name="password"
	 */
	protected String password;

	/**
	 * @return Name (username) des Benutzers
	 * @uml.property  name="username"
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 * @uml.property  name="username"
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return Password des Users
	 * @uml.property  name="password"
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 * @uml.property  name="password"
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
