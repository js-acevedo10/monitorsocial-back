package mundo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("empleados")
public class Empleado {
	@Id ObjectId id;
	public Empleado() {
		
	}
}
