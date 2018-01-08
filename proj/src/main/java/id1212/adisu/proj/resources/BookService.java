package id1212.adisu.proj.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import id1212.adisu.proj.model.Book;
import id1212.adisu.proj.repository.BookRepo;

@Path("books")
public class BookService {

	private BookRepo book = new BookRepo();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Book> getBooks() {
		return book.getBooks();
	}

	@GET
	@Path("byISBN/{isbn}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Book getByISBN(@PathParam("isbn") int isbn) {
		return book.getByISBN(isbn);
	}

	@GET
	@Path("byAuthor/{author}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Book> getBooksByAuthor(@PathParam("author") String author) {
		return book.getBooksByAuthor(author);
	}

	@POST
	@Path("admin/addBook")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	public String addBook(Book bk) {
		return book.addBook(bk);
	}

	@PUT
	@Path("admin/update")
	@Consumes("application/xml")
	@Produces(MediaType.TEXT_PLAIN)
	public String update(Book bk) {
		return book.update(bk);
	}
	
	@DELETE
	@Path("admin/delete/{isbn}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBook(@PathParam("isbn") int isbn) {
		return book.deleteBook(isbn);
	}
}
