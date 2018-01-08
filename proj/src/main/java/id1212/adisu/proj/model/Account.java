package id1212.adisu.proj.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {
	private String username;
	private String password;
	private String accountype;
	private int userID;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountype() {
		return accountype;
	}

	public void setAccountype(String accountype) {
		this.accountype = accountype;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userid) {
		this.userID = userid;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", accountype=" + accountype + "]";
	}

}
