package recursos;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.CuentaDAO;
import mundo.Cuenta;

@PermitAll
@Path("cuentas")
public class CuentaResource {
	
	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public static Response getCuentas() {
		return CuentaDAO.getCuentas();
	}
	
	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idCuenta}")
	public static Response getCuenta(@PathParam("idCuenta") String idCuenta) {
		return CuentaDAO.getCuenta(idCuenta);
	}
	
	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{idCuenta}/personas")
	public static Response getPersonasFromCuenta(@PathParam("idCuenta") String idCuenta) {
		return CuentaDAO.getPersonasFromCuenta(idCuenta);
	}
	
	@PermitAll
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public static Response addCuenta(Cuenta cuenta) {
		return CuentaDAO.addCuenta(cuenta);
	}
}
