package recursos;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.CasoDAO;
import mundo.casos.Historia;
import mundo.casos.Nota;
import security.Roles;

@RolesAllowed(Roles.EMPRESA)
@Path("/casos")
public class CasoResource {

	@GET
	@RolesAllowed(Roles.EMPRESA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idUsuario}")
	public static Response getCasos(@PathParam("idUsuario") String idUsuario) {
		return CasoDAO.getCasos(idUsuario);
	}
	
	@GET
	@RolesAllowed(Roles.EMPRESA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idUsuario}/caso/{idCaso}")
	public static Response getCaso(@PathParam("idUsuario") String idUsuario, @PathParam("idCaso") String idCaso) {
		return CasoDAO.getCaso(idUsuario, idCaso);
	}
	
	@RolesAllowed(Roles.EMPRESA)
	@POST
	@Path("/{idCaso}/notas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response addNota(@PathParam("idCaso") String idCaso, Nota nota) {
		return CasoDAO.addNota(nota, idCaso);
	}
	
	@RolesAllowed(Roles.EMPRESA)
	@POST
	@Path("/{idCaso}/historial")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response addHistoria(@PathParam("idCaso") String idCaso, Historia historia) {
		return CasoDAO.addHistoria(historia, idCaso);
	}
}
