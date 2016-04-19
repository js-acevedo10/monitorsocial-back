package dao;

import java.util.List;

import javax.ws.rs.core.Response;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.glassfish.jersey.internal.util.Base64;
import org.mongodb.morphia.query.Query;

import mundo.Usuario;
import security.Roles;
import utilidades.MorphiaDB;
import utilidades.ResponseMonitor;

public class AuthenticationDAO {

	private static String json = "";
	private static Response.Status status = Response.Status.OK;

	public static Response login(String email, String password) {
		try {
			Query<Usuario> q = MorphiaDB.getDatastore().createQuery(Usuario.class)
					.field("email").equal(email)
					.field("password").equal(password);
			List<Usuario> user = q.asList();
			if(user != null && !user.isEmpty()) {
				Usuario r = user.get(0);
				String at = "Basic " + Base64.encodeAsString(r.getId() + ":" + r.getPassword());
				Document resp = new Document()
						.append("id", r.getId())
						.append("name", r.getName())
						.append("twitterId", r.getTwitterId())
						.append("accessToken", at)
						.append("role", Roles.EMPRESA);
				json = resp.toJson();
				status = Response.Status.OK;
			} else {
				json = "{\"error\":\"not a valid user\"}";
				status = Response.Status.BAD_REQUEST;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseMonitor.buildResponse(json, status);
	}

	public static String authByID(String id, String token) {
		json = "{\"error\":\"not a valid user\"}";
		try {
			Query<Usuario> q = MorphiaDB.getDatastore().createQuery(Usuario.class)
					.field("id").equal(new ObjectId(id))
					.field("password").equal(token);
			List<Usuario> user = q.asList();
			if(user != null && !user.isEmpty()) {
				json = Roles.EMPRESA;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public static void main(String[] args) {
		Usuario emp = new Usuario("Nike", "nikemonitor", "123", "juan@me.com", "Bogota", "Cundinamarca", "Colombia", "@Nike");
		emp.addKeyWord("@Nike");
		emp.addKeyWord("#Nike");
		emp.addKeyWord("Nike");
		try {
			MorphiaDB.getDatastore().save(emp);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(login("juan@me.com", "MTIz").getEntity());
	}
}
