package id1212.adisu.proj.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import id1212.adisu.proj.model.Account;
import id1212.adisu.proj.repository.AccountRepo;

@Path("accounts")
public class LoginService {

	AccountRepo acr = new AccountRepo();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Account> listAccounts() {
		return acr.getAccounts();
	}

	@GET
	@Path("list/{accountType}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Account> listByAccountType(@PathParam("accountType") String accountType) {
		return acr.listByAccountType(accountType);
	}

	@POST
	@Path("login/{user}/{pass}")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@PathParam("user") String user, @PathParam("pass") String pass) {
		return acr.authenticate(user, pass);
	}

	@POST
	@Path("createAccount")
	@Consumes("application/xml")
	@Produces(MediaType.TEXT_PLAIN)
	public String createAccount(Account ac) {
		System.out.println("Request Received...");
		return acr.createAccount(ac);
	}
}
