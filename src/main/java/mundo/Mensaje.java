package mundo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

@Entity("mensajes")
@Indexes(
	@Index(fields = @Field("name"))
)
public class Mensaje {
	public Mensaje(String name) {
		this.name = name;
	}
	@Id
	private ObjectId id;
	private String name;
}