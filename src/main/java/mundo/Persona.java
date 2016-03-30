package mundo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("personas")
public class Persona {
	@Id ObjectId id;
	public Persona() {
		
	}
}
