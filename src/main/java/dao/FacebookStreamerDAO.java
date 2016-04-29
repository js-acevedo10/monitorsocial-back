package dao;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
		System.out.println("Cuerpo: " + body);
	}
}
