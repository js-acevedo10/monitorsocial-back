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
public class Historia {
	
	@Id ObjectId id;
	private Date fechaSuceso;
	private String creador, creadorId, descripción;
	
	public Historia() {
		
	}
	
	public Historia(Date fechaSuceso, String creador, String creadorId, String descripción) {
		this.fechaSuceso = new Date();
		this.creador = creador;
		this.creadorId = creadorId;
		this.descripción = descripción;
	}

	public Date getFechaSuceso() {
		return fechaSuceso;
	}

	public void setFechaSuceso(Date fechaSuceso) {
		this.fechaSuceso = fechaSuceso;
	}

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}

	public String getCreadorId() {
		return creadorId;
	}

	public void setCreadorId(String creadorId) {
		this.creadorId = creadorId;
	}

	public String getDescripción() {
		return descripción;
	}

	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}		
}
