package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
	private static final String URL = "jdbc:mysql://localhost:3306/FileCatalog";
	private static final String PASS = "root";
	private static final String USER = "root";
	private PreparedStatement pst;
	private ResultSet rs;
	private Connection con;
	private List<Person> pr;
	private Person prsn;
	Statement st = null;
	public PersonRepository() {
		connect();
	}

	public List<Person> getPersons() {
		String sql = "select * from test";
		pr = new ArrayList<>();
		try {
			pst = connect().prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				prsn = new Person();
				prsn.setId(rs.getInt("id"));
				prsn.setFn(rs.getString("Fn"));
				prsn.setLn(rs.getString("Ln"));
				prsn.setAge(rs.getInt("Age"));
				pr.add(prsn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pr;
	}

	public Person getPerson(int id) {
		String sql = "select * from test where id = ?";
		try {
			pst = connect().prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				prsn = new Person();
				prsn.setId(rs.getInt("id"));
				prsn.setFn(rs.getString("Fn"));
				prsn.setLn(rs.getString("Ln"));
				prsn.setAge(rs.getInt("Age"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prsn;
	}

	public Person setPerson(Person p) {
		String sql = "insert into test(id, Fn, Ln, Age) values(?, ?, ?, ?)";
		try {
			pst = connect().prepareStatement(sql);
			pst.setInt(1, p.getId());
			pst.setString(2, p.getFn());
			pst.setString(3, p.getLn());
			pst.setInt(4, p.getAge());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	public void delete(int id) {
		pr.remove(id);

	}

	public int update(int id) {

		return 0;
	}

	public Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return con;
	}
}
