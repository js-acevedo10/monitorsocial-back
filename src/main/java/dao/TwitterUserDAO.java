package dao;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mundo.TwitterUser;
import utilidades.MorphiaDB;
import utilidades.ObjectIdTypeAdapter;
import utilidades.ResponseMonitor;

public class TwitterUserDAO {
	
	private static String json = "";
	private static Response.Status status = Response.Status.OK;
	private static Gson gson = new GsonBuilder().registerTypeAdapter(ObjectId.class, new ObjectIdTypeAdapter()).create();

	public static Response getTwitterUsers() {
		try {
			Query<TwitterUser> q = MorphiaDB.getDatastore().createQuery(TwitterUser.class);
			List<TwitterUser> tUsers = (List<TwitterUser>) q.asList();
			if(tUsers != null && !tUsers.isEmpty()) {
				json = gson.toJson(tUsers);
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

	public static Response getTwitterUser(String idUsuario) {
		try {
			Query<TwitterUser> q = MorphiaDB.getDatastore().createQuery(TwitterUser.class)
					.field("twitterId").equal(Long.parseLong(idUsuario));
			TwitterUser tUser = (TwitterUser) q.get();
			if(tUser != null) {
				json = gson.toJson(tUser);
				status = Status.OK;
//				return ResponseMonitor.buildResponse(tUser, status);
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
			e.printStackTrace();
		}
		return ResponseMonitor.buildResponse(json, status);
	}
	
}
