package recursos;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

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
		System.out.println("Hola");
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
	
	@RolesAllowed(Roles.EMPRESA)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userId}/messageCount")
	public static Response getMessageCount(@PathParam("userId") String userId) {
		return TwitterDAO.getMensajesCount(userId);
	}
	
	@RolesAllowed(Roles.EMPRESA)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userId}/positiveMessages")
	public static Response getPositive(@PathParam("userId") String userId) {
		return TwitterDAO.getPositive(userId);
	}
	
	
	@RolesAllowed(Roles.EMPRESA)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userId}/negativeMessages")
	public static Response getNegavite(@PathParam("userId") String userId) {
		return TwitterDAO.getNegative(userId);
	}
	
	@RolesAllowed(Roles.EMPRESA)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userId}/neutralMessages")
	public static Response getNeutral(@PathParam("userId") String userId) {
		return TwitterDAO.getNeutral(userId);
	}
	
	@RolesAllowed(Roles.EMPRESA)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userId}/reply")
	public static Response postReply(@PathParam("userId") String userId, String json) {
		Document doc = Document.parse(json);
		return TwitterDAO.postReply(userId, doc);
	}
	
	@RolesAllowed(Roles.EMPRESA)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userId}/reply/simple")
	public static Response postReplySimple(@PathParam("userId") String userId, String json) {
		Document doc = Document.parse(json);
		return TwitterDAO.postReplySimple(userId, doc);
	}
	
	@RolesAllowed(Roles.EMPRESA)
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userId}/menciones/{mencionId}")
	public static Response muteMencion(@PathParam("userId") String userId, @PathParam("mencionId") String mencionId) {
		return TwitterDAO.muteMencion(userId, mencionId);
	}
}
