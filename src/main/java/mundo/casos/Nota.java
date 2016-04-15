package mundo.casos;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity("Notas")
public class Nota {
	@Id private ObjectId id;
	private String creatorId;
	private String texto;
	private Date fechaCreacion;
	private String nombreCreador;
	
	public Nota() {
		
	}

	public Nota(String creatorId, String texto, Date fechaCreacion, String nombreCreador) {
		this.creatorId = creatorId;
		this.texto = texto;
		this.fechaCreacion = new Date();
		this.nombreCreador = nombreCreador;
	}
	
	public void addId() {
		id = new ObjectId();
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombreCreador() {
		return nombreCreador;
	}

	public void setNombreCreador(String nombreCreador) {
		this.nombreCreador = nombreCreador;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
}
