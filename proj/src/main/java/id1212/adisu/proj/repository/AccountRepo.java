package id1212.adisu.proj.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import id1212.adisu.proj.model.Account;

public class AccountRepo {
	private List<Account> accounts;
	private Account account;
	private PreparedStatement pst;
	private ResultSet rs;
	private static final String TABLE = "Account";
	JDBCConnector connection = new JDBCConnector();

	public List<Account> getAccounts() {
		accounts = new ArrayList<>();
		try {
			rs = connection.selectAll(TABLE);
			while (rs.next()) {
				account = new Account();
				account.setUsername(rs.getString("Username"));
				account.setPassword(rs.getString("Password"));
				account.setAccountype(rs.getString("AccountType"));
				account.setUserID(rs.getInt("UserID"));
				accounts.add(account);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return accounts;
	}

	public Account getOneAccount(String username) {
		try {
			rs = connection.selectAccount(username);
			if (rs.next()) {
				account = new Account();
				account.setUsername(rs.getString("Username"));
				account.setPassword(rs.getString("Password"));
				account.setAccountype(rs.getString("AccountType"));
				account.setUserID(rs.getInt("UserID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	public String createAccount(Account ac) {
		String sql = "insert into Account(Username, Password, AccountType, UserID) values(?, ?, ?, ?)";
		String message = "failed creating account";
		try {
			pst = connection.prepare(sql);
			pst.setString(1, ac.getUsername());
			pst.setString(2, ac.getPassword());
			pst.setString(3, ac.getAccountype());
			pst.setInt(4, ac.getUserID());
			pst.executeUpdate();
			message = "Account Created Successfully!";
		} catch (SQLException e) {
			message = "Please check your userId or User name";
			e.printStackTrace();
			return message;
		}
		return message;
	}

	public List<Account> listByAccountType(String accountType) {
		accounts = new ArrayList<>();
		String sql = "select * from Account where AccountType = ?";
		try {
			pst = connection.prepare(sql);
			pst.setString(1, accountType);
			rs = pst.executeQuery();
			while (rs.next()) {
				account = new Account();
				account.setUsername(rs.getString("Username"));
				account.setPassword(rs.getString("Password"));
				account.setAccountype(rs.getString("AccountType"));
				account.setUserID(rs.getInt("UserID"));
				accounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public String authenticate(String user, String pass) {
		String ch = "Failed";
		try {
			System.out.println(user + pass);
			rs = connection.checkLogin(user, pass);
			System.out.println("got rs");
			if (rs.next()) {
				ch = "Login Successfull!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ch;
	}
}
