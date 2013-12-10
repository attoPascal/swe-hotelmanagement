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
	 * @return
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
	 * @return
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
