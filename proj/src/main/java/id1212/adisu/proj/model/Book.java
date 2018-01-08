package id1212.adisu.proj.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {

	private int isbn;
	private int quantity;
	private String title;
	private String author;
	private String edition;

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", quantity=" + quantity + ", title=" + title + ", author=" + author
				+ ", edition=" + edition + "]";
	}

}
