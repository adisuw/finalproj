package id1212.adisu.proj.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import test.Person;
import test.PersonRepository;

@Path("test")
public class TestResource {
	PersonRepository repo = new PersonRepository();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Person> getPerson() {
		return repo.getPersons();
	}

	@GET
	@Path("single/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Person getSingle(@PathParam("id") int id) {
		System.out.println("Request Recieved");
		return repo.getPerson(id);
	}

	@POST
	@Path("person")
	public Person setPerson(Person p) {
		System.out.println(p);
		repo.setPerson(p);
		return p;
	}
	@DELETE
	@Path("delete{id}")
	public void deletePr(@PathParam("id")int id) {
		repo.delete(id);
	}
}
