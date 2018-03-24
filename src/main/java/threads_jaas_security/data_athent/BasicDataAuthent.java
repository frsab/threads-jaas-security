package threads_jaas_security.data_athent;

public class BasicDataAuthent extends DataAuthent{
	private String hashLogin;
	private String hashPassword;
	private String hashConcatLoginPassword;
	/**
	 * @return the hashLogin
	 */
	public String getHashLogin() {
		return hashLogin;
	}
	/**
	 * @param hashLogin the hashLogin to set
	 */
	public void setHashLogin(String hashLogin) {
		this.hashLogin = hashLogin;
	}
	/**
	 * @return the hashPassword
	 */
	public String getHashPassword() {
		return hashPassword;
	}
	/**
	 * @param hashPassword the hashPassword to set
	 */
	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}
	public String getHashConcatLoginPassword() {
		return hashConcatLoginPassword;
	}
	public void setHashConcatLoginPassword(String hashConcatLoginPassword) {
		this.hashConcatLoginPassword = hashConcatLoginPassword;
	}
	
	

}
