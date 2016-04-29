package recursos;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.FacebookStreamerDAO;

@PermitAll
@Path("facebookNotification")
public class FacebookStreamingResource {

	@GET
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public static Response get(@QueryParam("hub.mode") String hubMode, @QueryParam("hub.challenge") String hubChallenge, @QueryParam("hub.verify_token") String hubToken) {
		return FacebookStreamerDAO.verifyFacebook(hubMode, hubChallenge, hubToken);
	}
	
	@POST
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	public static Response post(String body) {
		FacebookStreamerDAO.prccessPost(body);
		return Response.status(200).build();
	}
}
