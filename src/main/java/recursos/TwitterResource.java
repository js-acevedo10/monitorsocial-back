package recursos;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.TwitterDAO;
import security.Roles;

@PermitAll
@Path("/twitter")
public class TwitterResource {
	
	@RolesAllowed(Roles.EMPRESA)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userId}/startListening")
	public static Response startListening(@PathParam("userId") String userId) {
		return TwitterDAO.startListening(userId);
	}
	
	@RolesAllowed(Roles.EMPRESA)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userId}/stopListening")
	public static Response stopListening(@PathParam("userId") String userId) {
		return TwitterDAO.stopListening(userId);
	}
	
	@RolesAllowed(Roles.EMPRESA)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userId}/unreadMessages")
	public static Response getUnread(@PathParam("userId") String userId) {
		return TwitterDAO.getUnread(userId);
	}
}
