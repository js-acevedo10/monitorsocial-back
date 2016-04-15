package mundo.casos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import mundo.TwitterStatus;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity("Conversaciones")
public class ConversacionTwitter {
	@Id ObjectId id;
	private Date fechaCreacion;
	private String idUsuario;
	private String idEmpresa;
	@Reference private List<TwitterStatus> mensajes;
	
	public ConversacionTwitter() {
		
	}
	
	public ConversacionTwitter(Date fechaCreacion, String idUsuario, String idEmpresa) {
		this.fechaCreacion = fechaCreacion;
		this.idUsuario = idUsuario;
		this.idEmpresa = idEmpresa;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public List<TwitterStatus> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<TwitterStatus> mensajes) {
		this.mensajes = mensajes;
	}
	
	public void addMensaje(TwitterStatus mensaje) {
		if(mensajes == null) {
			mensajes = new ArrayList<TwitterStatus>();
		}
		this.mensajes.add(mensaje);
	}
}
