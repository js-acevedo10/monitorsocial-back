package recursos;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.TwitterUserDAO;

@PermitAll
@Path("twitterUsers")
public class TwitterUserResource {

	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTwitterUsers() {
		return TwitterUserDAO.getTwitterUsers();
	}

	@PermitAll
	@GET
	@Path("/{idUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTwitterUser(@PathParam("idUser") String idUsuario) {
		return TwitterUserDAO.getTwitterUser(idUsuario);
	}
}
