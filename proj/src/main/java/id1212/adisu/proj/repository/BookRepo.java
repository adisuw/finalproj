package id1212.adisu.proj.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import id1212.adisu.proj.model.Book;

public class BookRepo {

	private List<Book> books;
	private Book book;
	private PreparedStatement pst;
	private ResultSet rs;
	private static final String TABLE = "Book";
	private static final String PRIMARY_KEY = "ISBN";
	JDBCConnector connection = new JDBCConnector();

	public List<Book> getBooks() {
		books = new ArrayList<>();
		try {
			rs = connection.selectAll(TABLE);
			while (rs.next()) {
				book = new Book();
				book.setIsbn(rs.getInt("ISBN"));
				book.setTitle(rs.getString("Title"));
				book.setAuthor(rs.getString("Author"));
				book.setEdition(rs.getString("Edition"));
				book.setQuantity(rs.getInt("Quantity"));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public Book getByISBN(int isbn) {
		try {
			rs = connection.selectOne(TABLE, PRIMARY_KEY, isbn);
			book = new Book();
			book.setIsbn(rs.getInt("ISBN"));
			book.setTitle(rs.getString("Title"));
			book.setAuthor(rs.getString("Author"));
			book.setEdition(rs.getString("Edition"));
			book.setQuantity(rs.getInt("Quantity"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public List<Book> getBooksByAuthor(String author) {
		books = new ArrayList<>();
		String sql = "select * from " + TABLE + " where Author = ?";
		try {
			pst = connection.prepare(sql);
			pst.setString(1, author);
			rs = pst.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setIsbn(rs.getInt("ISBN"));
				book.setTitle(rs.getString("Title"));
				book.setAuthor(rs.getString("Author"));
				book.setEdition(rs.getString("Edition"));
				book.setQuantity(rs.getInt("Quantity"));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public String addBook(Book bk) {
		String sql = "insert into " + TABLE + " (ISBN, Title, Author, Edition, Quantity) values(?, ?, ?, ?, ?)";
		String message = "Unable to register the book";
		try {
			pst = connection.prepare(sql);
			pst.setInt(1, bk.getIsbn());
			pst.setString(2, bk.getTitle());
			pst.setString(3, bk.getAuthor());
			pst.setString(4, bk.getEdition());
			pst.setInt(5, bk.getQuantity());
			pst.executeUpdate();
			message = "The book is added successfully";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Check the ISBN";
		}
		return message;
	}

	public String update(Book bk) {
		String sql = "update " + TABLE + " set Title = ?, Author = ?, Edition = ?, Quantity = ? where ISBN = ?";
		String message = "Unable to register the book";
		try {
			pst = connection.prepare(sql);
			pst.setString(1, bk.getTitle());
			pst.setString(2, bk.getAuthor());
			pst.setString(3, bk.getEdition());
			pst.setInt(4, bk.getQuantity());
			pst.setInt(5, bk.getIsbn());
			pst.executeUpdate();
			message = "Updated successfully!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Check the ISBN";
		}
		return message;
	}

	public String deleteBook(int isbn) {
		return connection.delete(TABLE, PRIMARY_KEY, isbn);
	}
}
