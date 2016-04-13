package recursos;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Path;

import security.Roles;

@RolesAllowed(Roles.EMPRESA)
@Path("personas")
public class PersonaResource {

}
