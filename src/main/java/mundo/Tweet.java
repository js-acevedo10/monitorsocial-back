package mundo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@SuppressWarnings("unused")
@Entity("tweets")
public class Tweet {
	@Id ObjectId id;
	private String sender, mensaje;
	public Tweet(String mensaje, String sender) {
		this.mensaje = mensaje;
		this.sender = sender;
	}
}
