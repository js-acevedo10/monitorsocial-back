package utilidades;

import java.util.Calendar;
import java.util.TimerTask;

import facebook4j.Facebook;

public class FacebookTokenExtender extends TimerTask {
	public Facebook facebook;

	public FacebookTokenExtender() {

	}

	@Override
	public void run() {
		try {
			Calendar x = Calendar.getInstance();
			if(facebook.getOAuthAccessToken().getExpires() != null) {
				x.setTimeInMillis(facebook.getOAuthAccessToken().getExpires());
			}
			Calendar n = Calendar.getInstance();
			int dif = (int) ((x.getTimeInMillis() - n.getTimeInMillis())/1000*60*60*24);
			System.out.println(dif);
			if(dif <= 10) {
				System.out.println("Facebook token actually expires on " + x.getTime());
				facebook.extendTokenExpiration();
				x.setTimeInMillis(facebook.getOAuthAccessToken().getExpires().longValue());
				System.out.println("Facebook token extended until " + x.getTime());
			} else {
				System.out.println("Revisión de Token efectuada. El tiempo aún es muy largo para la renovación.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
