package utilidades;

import java.util.Calendar;
import java.util.Timer;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

public class FacebookStreamer {

	private static Facebook facebook;
	private static String appId = "536444756508677";
	private static String appSecret = "d5f1f984bceff758e91eb7e46bfce9ba";
	private static String accessToken = "EAAHn5McnQAUBAO4GQnF46RvHO5hoIkThTg9GDr4kgjVxb6fHvTEsqGcuzPvJi9i00M9jazz7JyRSWKH6KVqIvnj7TX9zsx8cvZAs5NNKZCAXeIoELpkt2qwOy3hPwblT5ZCZALBEJJYGnI9HqP4WthV7EEEZAj74ZD";
	public static Timer timer;
	public static Calendar date;
	public static FacebookTokenExtender extender;

	public static Facebook getFacebook() {
		if(facebook == null) {			
			facebook = new FacebookFactory().getInstance();
			facebook.setOAuthAppId(appId, appSecret);
			facebook.setOAuthAccessToken(new AccessToken(accessToken));
//			extender = new FacebookTokenExtender();
//			extender.facebook = facebook;
//			timer = new Timer();
//			date = Calendar.getInstance();
//			date.set(2016, 3, 10, 23, 0);
//			timer.schedule(extender, date.getTime(), 1000*30);
		}
		return facebook;
	}
	
	public static void fetchPageConversation(String id) throws FacebookException, JSONException {
		facebook = getFacebook();
		JSONObject messages = facebook.callGetAPI(id + "/messages").asJSONObject();
		System.out.println("Resultado: " + messages.toString());
		JSONArray data = messages.getJSONArray("data");
		for(int i = 0; i < data.length(); i++) {
			String messageId = data.getJSONObject(i).getString("id");
			System.out.println("ID del mensaje: " + messageId);
			JSONObject message = facebook.callGetAPI(messageId).asJSONObject();
			System.out.println("Mensaje: " + message.toString());
		}
	}

	public static void main(String[] args) {
		Facebook f = getFacebook();
		try {
			System.out.println(f.getPage().getId());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}