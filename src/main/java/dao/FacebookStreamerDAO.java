package dao;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONObject;
import utilidades.FacebookStreamer;

public class FacebookStreamerDAO {
	
	private static String json = "";
	private static Response.Status status = Status.OK;
	private static String originalToken = "monitorSocialCRM-FacebookToken";
	
	public static Response verifyFacebook(String hubMode, String challenge, String token) {
		if(hubMode.equals("subscribe") && token.equals(originalToken)) {
			return Response
					.status(Status.OK)
					.entity(challenge)
					.build();
		} else {
			return Response
					.status(400)
					.build();
		}
	}
	
	public static void processPost(String body) {
		try {
			System.out.println(body);
			JSONObject jsonObject = new JSONObject(body);
			String object = jsonObject.getString("object");
			if(object.equals("page")) {
				JSONArray entry = jsonObject.getJSONArray("entry");
				JSONArray changes = entry.getJSONObject(0).getJSONArray("changes");
				for(int i = 0; i < changes.length(); i++) {
					String field = changes.getJSONObject(i).getString("field");
					JSONObject value = changes.getJSONObject(i).getJSONObject("value");
					String thread_id = value.getString("thread_id");
					System.out.println(field);
					switch (field) {
					case "conversations":
						FacebookStreamer.fetchPageConversation(thread_id);
						break;
					default:
						break;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		processPost("{\"entry\":[{\"changes\":[{\"field\":\"conversations\",\"value\":{\"thread_id\":\"t_mid.1461901431620:479d8656e1fd0c4390\",\"page_id\":1452109465045155}}],\"id\":\"1452109465045155\",\"time\":1461903971}],\"object\":\"page\"}");
	}
}
