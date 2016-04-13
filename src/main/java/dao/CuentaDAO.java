package dao;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mundo.Cuenta;
import utilidades.MorphiaDB;
import utilidades.ObjectIdTypeAdapter;
import utilidades.ResponseMonitor;

public class CuentaDAO {
	
	private static String json = "";
	private static Response.Status status = Status.OK;
	private static Gson gson = new GsonBuilder().registerTypeAdapter(ObjectId.class, new ObjectIdTypeAdapter()).create();
	
	public static Response getCuentas() {
		try {
			Query<Cuenta> q = MorphiaDB.getDatastore().createQuery(Cuenta.class);
			List<Cuenta> cuentas = (List<Cuenta>) q.asList();
			if(cuentas != null && !cuentas.isEmpty()) {
				json = gson.toJson(cuentas);
				status = Status.OK;
			} else {
				Document resp = new Document()
						.append("found", false);
				json = resp.toJson();
				status = Status.NOT_FOUND;
			}
		} catch(Exception e) {
			Document resp = new Document()
					.append("found", false)
					.append("error", e.getMessage());
			json = resp.toJson();
			status = Status.INTERNAL_SERVER_ERROR;
		}
		return ResponseMonitor.buildResponse(json, status);
	}
	
	public static Response getCuenta(String idCuenta) {
		try {
			Query<Cuenta> q = MorphiaDB.getDatastore().createQuery(Cuenta.class)
					.field("_id").equal(new ObjectId(idCuenta));
			Cuenta cuenta = q.get();
			if(cuenta != null) {
				json = gson.toJson(cuenta);
				status = Status.OK;
			} else {
				Document resp = new Document()
						.append("found", false);
				json = resp.toJson();
				status = Status.NOT_FOUND;
			}
		} catch(Exception e) {
			Document resp = new Document()
					.append("found", false)
					.append("error", e.getMessage());
			json = resp.toJson();
			status = Status.INTERNAL_SERVER_ERROR;
		}
		return ResponseMonitor.buildResponse(json, status);
	}

	public static Response getPersonasFromCuenta(String idCuenta) {
		try {
			Query<Cuenta> q = MorphiaDB.getDatastore().createQuery(Cuenta.class)
					.field("_id").equal(new ObjectId(idCuenta));
			Cuenta cuenta = q.get();
			if(cuenta != null) {
				json = gson.toJson(cuenta.getPersonas());
				status = Status.OK;
			} else {
				Document resp = new Document()
						.append("found", false);
				json = resp.toJson();
				status = Status.NOT_FOUND;
			}
		} catch(Exception e) {
			Document resp = new Document()
					.append("found", false)
					.append("error", e.getMessage());
			json = resp.toJson();
			status = Status.INTERNAL_SERVER_ERROR;
		}
		return ResponseMonitor.buildResponse(json, status);
	}

	public static Response addCuenta(Cuenta cuenta) {
		try {
			MorphiaDB.getDatastore().save(cuenta);
			json = gson.toJson(cuenta);
			status = Status.OK;
		} catch(Exception e) {
			Document resp = new Document()
					.append("found", false)
					.append("error", e.getMessage());
			json = resp.toJson();
			status = Status.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return ResponseMonitor.buildResponse(json, status);
	}
}
