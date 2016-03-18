package recursos;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mongodb.morphia.Datastore;

import mundo.Mensaje;
import utilidades.MorphiaDB;
import utilidades.ResponseMonitor;

@PermitAll
@Path("/open")
public class openResource {
	
	@GET
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public static Response open() {
		Datastore datastore = MorphiaDB.getDatastore();
		final Mensaje mensaje = new Mensaje("hola");
		datastore.save(mensaje);
		return ResponseMonitor.buildResponse("hola", Response.Status.OK); 
	}
	
}
