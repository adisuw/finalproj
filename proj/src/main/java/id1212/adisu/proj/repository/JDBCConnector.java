package id1212.adisu.proj.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCConnector {

	private static final String URL = "jdbc:mysql://localhost:3306/Reservation";
	private static final String PASS = "root";
	private static final String USER = "root";
	private PreparedStatement pst;
	private ResultSet rs;
	private Connection con;

	public Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return con;
	}

	public PreparedStatement prepareAll(String table) throws SQLException {
		String sql = "select * from " + table;
		return connect().prepareStatement(sql);
	}

	public ResultSet selectAll(String table) throws SQLException {
		return prepareAll(table).executeQuery();
	}

	public PreparedStatement prepare(String sql) throws SQLException {
		return connect().prepareStatement(sql);
	}

	public ResultSet selectOne(String table, String primaryKey, int id) throws SQLException {
		String sql = "select * from " + table + " where " + primaryKey + " = ?";

		pst = prepare(sql);
		pst.setInt(1, id);
		rs = pst.executeQuery();
		return rs;
	}

	public ResultSet selectAccount(String username) throws SQLException {
		String sql = "select * from Account where Username = ?";
		pst = prepare(sql);
		pst.setString(1, username);
		rs = pst.executeQuery();
		return rs;
	}

	public ResultSet checkLogin(String user, String pass) throws SQLException {
		String sql = "select * from Account where Username = ? and Password = ?";
		pst = prepare(sql);
		pst.setString(1, user);
		pst.setString(2, pass);
		System.out.println("feching");
		return pst.executeQuery();
	}

	public String delete(String table, String primaryKey, int id) {
		String sql = "delete from " + table + " where " + primaryKey + " = ?";
		String message = "Unable to delte the record!";
		try {
			pst = prepare(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			message = "Recored is deleted Successfully!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Something went wrong";
		}
		return message;
	}
}
