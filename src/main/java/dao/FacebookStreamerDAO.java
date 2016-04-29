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
			JSONObject jsonObject = new JSONObject(body);
			String object = jsonObject.getString("object");
			if(object.equals("page")) {
				JSONArray arr = jsonObject.getJSONArray("entry").getJSONArray(0);
				for(int i = 0; i < arr.length(); i++) {
					String field = arr.getJSONObject(i).getString("field");
					System.out.println(field);
					switch (field) {
					case "conversations":
						FacebookStreamer.fetchPageConversations();
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
}
