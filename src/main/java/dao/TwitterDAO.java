package dao;

import java.util.List;

import javax.ws.rs.core.Response;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.google.gson.Gson;
import com.mongodb.util.JSON;

import mundo.TwitterStatus;
import mundo.TwitterUser;
import mundo.Usuario;
import mundo.casos.Caso;
import twitter4j.Status;
import utilidades.MorphiaDB;
import utilidades.ResponseMonitor;
import utilidades.TwitterStreamer;

public class TwitterDAO {

	private static String json = "";
	private static Response.Status status = Response.Status.OK;

	public static Response startListening(String userId) {
		try {
			Query<Usuario> q = MorphiaDB.getDatastore().createQuery(Usuario.class)
					.field("id").equal(new ObjectId(userId));
			List<Usuario> r = (List<Usuario>) q.asList();
			if(r != null && !r.isEmpty()) {
				Usuario e = r.get(0);
				TwitterStreamer.startListening("bGPtpsnIx0eQJuVtXtHncfZUA", "dAg6JZB84XXlKn21VGqxGSofaJtkIaD8c9pUrqCJJbyWSMZ2bD", "2334095631-QwupYxwRjThfNx7s4j24Vo28ylS64NFs7LA4Fqh", "4XVrQTuvs02XO6WTqhwJz8Pq2P9m4B00JI4X0lVZnafV9", e.getKeyWords(), e.getId());
				Document resp = new Document()
						.append("listening", true)
						.append("terms", e.getKeyWords());
				json = resp.toJson();
				status = Response.Status.OK;
			} else {
				Document resp = new Document()
						.append("listening", false);
				json = resp.toJson();
				status = Response.Status.NOT_FOUND;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Document resp = new Document()
					.append("listening", false)
					.append("error", e.getMessage());
			json = resp.toJson();
			status = Response.Status.INTERNAL_SERVER_ERROR;
		}
		return ResponseMonitor.buildResponse(json, status);
	}

	public static Response stopListening(String userId) {
		try {
			Query<Usuario> q = MorphiaDB.getDatastore().createQuery(Usuario.class)
					.field("id").equal(new ObjectId(userId));
			List<Usuario> r = (List<Usuario>) q.asList();
			if(r != null && !r.isEmpty()) {
				TwitterStreamer.stopListening();
				Document resp = new Document()
						.append("stopped", true);
				json = resp.toJson();
				status = Response.Status.OK;
			} else {
				Document resp = new Document()
						.append("stopped", false);
				json = resp.toJson();
				status = Response.Status.NOT_FOUND;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Document resp = new Document()
					.append("stopped", false)
					.append("error", e.getMessage());
			json = resp.toJson();
			status = Response.Status.INTERNAL_SERVER_ERROR;
		}
		return ResponseMonitor.buildResponse(json, status);
	}

	public static Response getUnread(String userId) {
		try {
			Query<TwitterStatus> q = MorphiaDB.getDatastore().createQuery(TwitterStatus.class)
					.field("unread").equal(true)
					.field("empresaId").equal(userId);
			List<TwitterStatus> r = (List<TwitterStatus>) q.asList();
			if(r != null && !r.isEmpty()) {
				new JSON();
				Document resp = new Document()
						.append("found", true)
						.append("mensajes", JSON.parse(new Gson().toJson(r)))
						.append("count", r.size());
				json = resp.toJson();
				status = Response.Status.OK;
			} else {
				Document resp = new Document()
						.append("found", false);
				json = resp.toJson();
				status = Response.Status.NOT_FOUND;
			}
		} catch(Exception e) {
			e.printStackTrace();
			Document resp = new Document()
					.append("found", false)
					.append("error", e.getMessage());
			json = resp.toJson();
			status = Response.Status.INTERNAL_SERVER_ERROR;
		}
		return ResponseMonitor.buildResponse(json, status);
	}

	public static Response getPositive(String userId) {
		try {
			Query<TwitterStatus> q = MorphiaDB.getDatastore().createQuery(TwitterStatus.class)
					.field("unread").equal(true)
					.field("empresaId").equal(userId)
					.field("sentimiento").greaterThan(5);
			List<TwitterStatus> r = (List<TwitterStatus>) q.asList();
			if(r != null && !r.isEmpty()) {
				new JSON();
				Document resp = new Document()
						.append("found", true)
						.append("mensajes", JSON.parse(new Gson().toJson(r)))
						.append("count", r.size());
				json = resp.toJson();
				status = Response.Status.OK;
			} else {
				Document resp = new Document()
						.append("found", false);
				json = resp.toJson();
				status = Response.Status.NOT_FOUND;
			}
		} catch(Exception e) {
			e.printStackTrace();
			Document resp = new Document()
					.append("found", false)
					.append("error", e.getMessage());
			json = resp.toJson();
			status = Response.Status.INTERNAL_SERVER_ERROR;
		}
		return ResponseMonitor.buildResponse(json, status);
	}

	public static Response getNegative(String userId) {
		try {
			Query<TwitterStatus> q = MorphiaDB.getDatastore().createQuery(TwitterStatus.class)
					.field("unread").equal(true)
					.field("empresaId").equal(userId)
					.field("sentimiento").lessThan(5);
			List<TwitterStatus> r = (List<TwitterStatus>) q.asList();
			if(r != null && !r.isEmpty()) {
				new JSON();
				Document resp = new Document()
						.append("found", true)
						.append("mensajes", JSON.parse(new Gson().toJson(r)))
						.append("count", r.size());
				json = resp.toJson();
				status = Response.Status.OK;
			} else {
				Document resp = new Document()
						.append("found", false);
				json = resp.toJson();
				status = Response.Status.NOT_FOUND;
			}
		} catch(Exception e) {
			e.printStackTrace();
			Document resp = new Document()
					.append("found", false)
					.append("error", e.getMessage());
			json = resp.toJson();
			status = Response.Status.INTERNAL_SERVER_ERROR;
		}
		return ResponseMonitor.buildResponse(json, status);
	}

	public static Response getNeutral(String userId) {
		try {
			Query<TwitterStatus> q = MorphiaDB.getDatastore().createQuery(TwitterStatus.class)
					.field("unread").equal(true)
					.field("empresaId").equal(userId)
					.field("sentimiento").equal(5);
			List<TwitterStatus> r = (List<TwitterStatus>) q.asList();
			if(r != null && !r.isEmpty()) {
				new JSON();
				Document resp = new Document()
						.append("found", true)
						.append("mensajes", JSON.parse(new Gson().toJson(r)))
						.append("count", r.size());
				json = resp.toJson();
				status = Response.Status.OK;
			} else {
				Document resp = new Document()
						.append("found", false);
				json = resp.toJson();
				status = Response.Status.NOT_FOUND;
			}
		} catch(Exception e) {
			e.printStackTrace();
			Document resp = new Document()
					.append("found", false)
					.append("error", e.getMessage());
			json = resp.toJson();
			status = Response.Status.INTERNAL_SERVER_ERROR;
		}
		return ResponseMonitor.buildResponse(json, status);
	}

	public static void handleNewStatus(Status s, String empresaId) {
		try {
			System.out.println(s.getText());
			Datastore db = MorphiaDB.getDatastore();

			TwitterStatus status = new TwitterStatus(s, empresaId);
			ResponseMonitor.classifyTweet(status);
			db.save(status);
			if(status.getCategoria() != utilidades.Constantes.TWEET_TIPO_OTROS) {
				TwitterUser user = null;
				Query<TwitterUser> q = db.createQuery(TwitterUser.class)
						.field("name").equal(s.getUser().getName())
						.field("screenName").equal(s.getUser().getScreenName());
				List<TwitterUser> listUser = (List<TwitterUser>) q.asList();
				if(listUser != null && !listUser.isEmpty()) {
					user = listUser.get(0);
					user.setFollowersCount(s.getUser().getFollowersCount());
					user.setFriendsCount(s.getUser().getFriendsCount());
					user.setTimeZone(s.getUser().getTimeZone());
					user.setName(s.getUser().getName());
					user.setProfileImageUrl(s.getUser().getOriginalProfileImageURL());
					user.setMiniProfileImageUrl(s.getUser().getMiniProfileImageURL());
					user.setVerified(s.getUser().isVerified());
				} else {
					user = new TwitterUser(s.getUser());					
				}
				db.save(user);
				user.addStatus(status);
				Caso caso = CasoDAO.addTwitterCaso(status, empresaId);
				user.addCaso(caso);
				db.save(user);
			}
		} catch(Exception e) {
			if(!e.getMessage().contains("E11000")) {
				e.printStackTrace();
			}
		}
	}
}
