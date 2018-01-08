package id1212.adisu.proj.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reserv {

	private int reservId;
	private int quantity;
	private int isbn;
	private String username;
	private Date date;

	public int getReservId() {
		return reservId;
	}

	public void setReservId(int reservId) {
		this.reservId = reservId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setDate(String date) {
		this.date = new Date(parseDate(date).getTime());
	}

	public String getDate() {
		return parseString(date);
	}

	public java.util.Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String parseString(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String convDate = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);

		return convDate;
	}

	@Override
	public String toString() {
		return "Reserv [reservId=" + reservId + ", quantity=" + quantity + ", isbn=" + isbn + ", username=" + username
				+ ", date=" + date + "]";
	}

}
