package mundo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("tweets")
public class Tweet {
	@Id ObjectId id;
	private String sender;
	private String mensaje;
	public Tweet(String mensaje, String sender) {
		this.mensaje = mensaje;
		this.sender = sender;
	}
}
