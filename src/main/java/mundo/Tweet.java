package mundo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@SuppressWarnings("unused")
@Entity("tweets")
public class Tweet {
	@Id ObjectId id;
	private String sender, mensaje, userId;
	private boolean unread;
	public Tweet(String mensaje, String sender, String userId) {
		this.mensaje = mensaje;
		this.sender = sender;
		this.userId = userId;
		this.unread = true;
	}
	public Tweet() {
		//MANDATORY
	}
}
