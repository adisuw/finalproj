package id1212.adisu.proj.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import id1212.adisu.proj.model.Reserv;

public class ReservRepo {

	private List<Reserv> reservs;
	private Reserv reserv;
	private PreparedStatement pst;
	private ResultSet rs;
	private static final String TABLE = "Reserv";
	private static final String PRIMARY_KEY = "ReservID";
	JDBCConnector connection = new JDBCConnector();

	public List<Reserv> getReservs() {
		reservs = new ArrayList<>();
		try {
			rs = connection.selectAll(TABLE);
			while (rs.next()) {
				reserv = new Reserv();
				reserv.setReservId(rs.getInt("ReservID"));
				reserv.setDate(rs.getString("Date"));
				reserv.setQuantity(rs.getInt("Quantity"));
				reserv.setUsername(rs.getString("Username"));
				reserv.setIsbn(rs.getInt("ISBN"));
				reservs.add(reserv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservs;
	}

	public Reserv getByReservId(int reservid) {
		try {
			rs = connection.selectOne(TABLE, PRIMARY_KEY, reservid);
			if (rs.next()) {
				reserv = new Reserv();
				reserv.setReservId(rs.getInt("ReservID"));
				reserv.setDate(rs.getString("Date"));
				reserv.setQuantity(rs.getInt("Quantity"));
				reserv.setUsername(rs.getString("Username"));
				reserv.setIsbn(rs.getInt("ISBN"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reserv;
	}

	public List<Reserv> getReservByUsername(String username) {
		reservs = new ArrayList<>();
		String sql = "select * from " + TABLE + " where Username = ?";
		try {
			pst = connection.prepare(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			while (rs.next()) {
				reserv = new Reserv();
				reserv.setReservId(rs.getInt("ReservID"));
				reserv.setDate(rs.getString("Date"));
				reserv.setQuantity(rs.getInt("Quantity"));
				reserv.setUsername(rs.getString("Username"));
				reserv.setIsbn(rs.getInt("ISBN"));
				reservs.add(reserv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservs;
	}

	public String makeReservation(Reserv rsv) {
		String sql = "insert into " + TABLE + " (ReservID, Date, Quantity, Username, ISBN) values(?, ?, ?, ?, ?)";
		String query = "select Quantity from Book where ISBN = ?";
		String q = "update Book set Quantity = ? where ISBN = ?";
		String message = "";
		try {
			pst = connection.prepare(query);
			pst.setInt(1, rsv.getIsbn());
			System.out.println("The required ISBN is: " + rsv.getIsbn());
			rs = pst.executeQuery();
			if (rs.next()) {
				int bookquantity = rs.getInt("Quantity");
				int reservquantity = rsv.getQuantity();
				if (reservquantity > bookquantity) {
					message = "The book is already taken";
				} else {
					pst = connection.prepare(sql);
					pst.setInt(1, rsv.getReservId());
					pst.setString(2, rsv.getDate());
					pst.setInt(3, rsv.getQuantity());
					pst.setString(4, rsv.getUsername());
					pst.setInt(5, rsv.getIsbn());
					pst.executeUpdate();
					int rst = bookquantity - reservquantity;
					pst = connection.prepare(q);
					pst.setInt(1, rst);
					pst.setInt(2, rsv.getIsbn());
					pst.executeUpdate();
					message = "Reserved Seccessfully!";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Ops sql Exception...";
		}
		return message;
	}
}
