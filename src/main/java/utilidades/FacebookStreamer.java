package utilidades;

import java.util.Calendar;
import java.util.Timer;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

public class FacebookStreamer {

	private static Facebook facebook;
	private static String appId = "536444756508677";
	private static String appSecret = "d5f1f984bceff758e91eb7e46bfce9ba";
	private static String accessToken = "EAAHn5McnQAUBAO4GQnF46RvHO5hoIkThTg9GDr4kgjVxb6fHvTEsqGcuzPvJi9i00M9jazz7JyRSWKH6KVq"
			+ "Ivnj7TX9zsx8cvZAs5NNKZCAXeIoELpkt2qwOy3hPwblT5ZCZALBEJJYGnI9HqP4WthV7EEEZAj74ZD";
	public static Timer timer;
	public static Calendar date;
	public static FacebookTokenExtender extender;

	public static Facebook getFacebook() {
		if(facebook == null) {			
			facebook = new FacebookFactory().getInstance();
			facebook.setOAuthAppId(appId, appSecret);
			facebook.setOAuthAccessToken(new AccessToken(accessToken));
			extender = new FacebookTokenExtender();
			extender.facebook = facebook;
			timer = new Timer();
			date = Calendar.getInstance();
			date.set(2016, 3, 10, 23, 0);
			timer.schedule(extender, date.getTime(), 1000*30);
		}
		return facebook;
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