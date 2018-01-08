package id1212.adisu.proj.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import id1212.adisu.proj.model.Reserv;
import id1212.adisu.proj.repository.ReservRepo;

@Path("reserv")
public class ReservService {

	private ReservRepo reserv = new ReservRepo();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Reserv> getReservedLists() {
		return reserv.getReservs();
	}

	@GET
	@Path("getByUsername/{username}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Reserv> getReservByUsername(@PathParam("username") String username) {
		return reserv.getReservByUsername(username);
	}
	@POST
	@Path("makeReserv")
	@Consumes("application/xml")
	@Produces(MediaType.TEXT_PLAIN)
	public String makeReservation(Reserv rsv) {
		return reserv.makeReservation(rsv);
	}
}
