package test;

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

import id1212.adisu.proj.model.User;
import id1212.adisu.proj.repository.UserRepo;

@Path("users")
public class UserService {

	private UserRepo user = new UserRepo();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<User> getUsers() {
		return user.getUsers();
	}

	@GET
	@Path("byId/{userid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User listByAccountType(@PathParam("userid") int userid) {
		return user.getUserByID(userid);
	}

	@POST
	@Path("admin/register")
	@Consumes("application/xml")
	@Produces(MediaType.TEXT_PLAIN)
	public String registerUser(User usr) {
		return user.registerUser(usr);
	}

	@PUT
	@Path("admin/update")
	@Consumes("application/xml")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUserInfo(User usr) {
		return user.updateUserInfo(usr);
	}

	@DELETE
	@Path("admin/delete/{userid}")
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(@PathParam("userid") int userid) {
		return user.deleteUserInfo(userid);
	}
}
