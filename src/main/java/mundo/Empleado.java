package mundo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity("Empleados")
public class Empleado {
	@Id ObjectId id;
	public Empleado() {
		
	}
}
