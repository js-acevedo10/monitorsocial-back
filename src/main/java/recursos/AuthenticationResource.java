package recursos;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import dao.AuthenticationDAO;

@PermitAll
@Path("/auth")
public class AuthenticationResource {

	@PermitAll
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public static Response login(String json) {
		Document j = Document.parse(json);
		return AuthenticationDAO.login(j.getString("email"), j.getString("password"));
	}
	
}
