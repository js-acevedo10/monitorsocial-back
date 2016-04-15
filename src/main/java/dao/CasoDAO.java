package dao;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mundo.TwitterStatus;
import mundo.casos.Caso;
import mundo.casos.Historia;
import mundo.casos.Nota;
import utilidades.Constantes;
import utilidades.MorphiaDB;
import utilidades.ObjectIdTypeAdapter;
import utilidades.ResponseMonitor;

public class CasoDAO {

	private static String json = "";
	private static Response.Status status = Response.Status.OK;
	private static Gson gson = new GsonBuilder().registerTypeAdapter(ObjectId.class, new ObjectIdTypeAdapter()).create();
	
	public static Response getCasos(String idUsuario) {
		try {
			Query<Caso> q = MorphiaDB.getDatastore().createQuery(Caso.class)
					.field("mongoUserId").equal(idUsuario);
			List<Caso> casos = (List<Caso>) q.asList();
			if(casos != null && !casos.isEmpty()) {
				json = gson.toJson(casos);
				status = Response.Status.OK;
			} else {
				Document r = new Document()
						.append("found", false);
				json = r.toJson();
				status = Response.Status.NOT_FOUND;
			}
		} catch(Exception e) {
			Document r = new Document()
					.append("found", false)
					.append("error", e.getMessage());
			json = r.toJson();
			status = Response.Status.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return ResponseMonitor.buildResponse(json, status);
	}
	
	public static Response getCaso(String idUsuario, String idCaso) {
		try {
			Query<Caso> q = MorphiaDB.getDatastore().createQuery(Caso.class)
					.field("_id").equal(new ObjectId(idCaso))
					.field("mongoUserId").equal(idUsuario);
			List<Caso> casos = (List<Caso>) q.asList();
			if(casos != null && !casos.isEmpty()) {
				json = gson.toJson(casos.get(0));
				status = Response.Status.OK;
			} else {
				Document r = new Document()
						.append("found", false);
				json = r.toJson();
				status = Response.Status.NOT_FOUND;
			}
		} catch(Exception e) {
			Document r = new Document()
					.append("found", false)
					.append("error", e.getMessage());
			json = r.toJson();
			status = Response.Status.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return ResponseMonitor.buildResponse(json, status);
	}
	
	/**
	 * Añade un caso a la base de datos
	 */
	public static Response addCaso(Caso caso) {
		try {
			MorphiaDB.getDatastore().save(caso);
			Document resp = new Document()
					.append("saved", true);
			json = resp.toJson();
			status = Response.Status.OK;
		} catch(Exception e) {
			e.printStackTrace();
			Document resp = new Document()
					.append("saved", false);
			json = resp.toJson();
			status = Response.Status.NOT_MODIFIED;
		}
		return ResponseMonitor.buildResponse(json, status);
	}
	
	public static Response addNota(Nota nota, String idCaso) {
		nota.setFechaCreacion(new Date());
		try {
			Datastore db = MorphiaDB.getDatastore();
			Query<Caso> q = db.createQuery(Caso.class)
					.field("_id").equal(new ObjectId(idCaso));
			Caso caso = (Caso) q.get();
			if(caso != null) {
				db.save(nota);
				caso.addNota(nota);
				db.save(caso);
				json = gson.toJson(caso);
				status = Response.Status.OK;
			} else {
				Document r = new Document()
						.append("found", false);
				json = r.toJson();
				status = Response.Status.NOT_FOUND;
			}
		} catch(Exception e) {
			Document r = new Document()
					.append("found", false)
					.append("modified", false)
					.append("error", e.getMessage());
			json = r.toJson();
			status = Response.Status.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return ResponseMonitor.buildResponse(json, status);
	}
	
	public static Response addHistoria(Historia historia, String idCaso) {
		historia.setFechaSuceso(new Date());
		try {
			Datastore db = MorphiaDB.getDatastore();
			Query<Caso> q = db.createQuery(Caso.class)
					.field("_id").equal(new ObjectId(idCaso));
			Caso caso = (Caso) q.get();
			if(caso != null) {
				db.save(historia);
				caso.addHistoria(historia);
				db.save(caso);
				json = gson.toJson(caso);
				status = Response.Status.OK;
			} else {
				Document r = new Document()
						.append("found", false);
				json = r.toJson();
				status = Response.Status.NOT_FOUND;
			}
		} catch(Exception e) {
			Document r = new Document()
					.append("found", false)
					.append("modified", false)
					.append("error", e.getMessage());
			json = r.toJson();
			status = Response.Status.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return ResponseMonitor.buildResponse(json, status);
	}
	
	/**
	 * Arma un caso de twitter y lo guarda en la base de datos
	 */
	public static Caso addTwitterCaso(TwitterStatus status, String empresaId) {
		Caso caso = null;
		String prioridad = Constantes.PRIORIDAD_MEDIA;
		String gravedad = Constantes.PRIORIDAD_MEDIA;
		if(status.getSentimiento() < 5) {
			prioridad = Constantes.PRIORIDAD_ALTA;
			gravedad = Constantes.PRIORIDAD_ALTA;
		} else if(status.getSentimiento() > 15) {
			prioridad = Constantes.PRIORIDAD_BAJA;
			gravedad = Constantes.PRIORIDAD_BAJA;
		}
		caso = new Caso(status.getUserId()+"", 
				"Caso Twitter", 
				status.getText(),
				status.getText(), 
				prioridad, 
				gravedad,
				status.getCategoria(),
				Constantes.CASO_ESTADO_PRESENTE,
				empresaId);
		caso.setTwitterUserName("@" + status.getUserScreenName());
		Historia h = new Historia(new Date(), "MonitorSocialCRM", "System", "Etapa: Creado --> Registrado");
		try {
		Datastore db = MorphiaDB.getDatastore();
		db.save(h);
		caso.addHistoria(h);
		caso.setCasoTwitter(true);
		caso.setCasoFacebook(false);
		db.save(caso);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return caso;
	}
}
