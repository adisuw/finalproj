package id1212.adisu.proj.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import id1212.adisu.proj.model.User;

public class UserRepo {
	private List<User> users;
	private User user;
	private PreparedStatement pst;
	private ResultSet rs;
	private static final String TABLE = "User";
	private static final String PRIMARY_KEY = "UserId";
	JDBCConnector connection = new JDBCConnector();

	public List<User> getUsers() {
		users = new ArrayList<>();
		try {
			rs = connection.selectAll(TABLE);
			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("UserId"));
				user.setName(rs.getString("Name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public User getUserByID(int userid) {
		try {
			rs = connection.selectOne(TABLE, PRIMARY_KEY, userid);
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("UserId"));
				user.setName(rs.getString("Name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public String registerUser(User usr) {
		String sql = "insert into "+TABLE+"(UserId, Name, email, phone, address) values(?, ?, ?, ?, ?)";
		String message = "Unable to register!..";
		try {
			pst = connection.prepare(sql);
			pst.setInt(1, usr.getUserId());
			pst.setString(2, usr.getName());
			pst.setString(3, usr.getEmail());
			pst.setString(4, usr.getPhone());
			pst.setString(5, usr.getAddress());
			pst.executeUpdate();
			message = "User Registered Successfully!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Check your user ID";
		}
		return message;
	}
	public String updateUserInfo(User usr) {
		String sql = "update "+TABLE+" set Name = ?, email = ?, phone = ?, address = ? where UserId = ?";
		String message = "Failed to update!";
		try {
			pst = connection.prepare(sql);
			pst.setString(1, usr.getName());
			pst.setString(2, usr.getEmail());
			pst.setString(3, usr.getPhone());
			pst.setString(4, usr.getAddress());
			pst.setInt(5, usr.getUserId());
			pst.executeUpdate();
			message = "User info Updated Successfully!";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}
	public String deleteUserInfo(int userid) {
		return connection.delete(TABLE, PRIMARY_KEY, userid);
	}
}
