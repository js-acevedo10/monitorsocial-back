package mundo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("empresas")
public class Empresa {
	@Id ObjectId id;
	public Empresa() {
		
	}
}
