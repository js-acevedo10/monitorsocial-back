package mundo;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import utilidades.Constants;

@Entity("Caso")
public class TwitterCaso {
	@Id ObjectId id;
	@Reference private TwitterUser user;
	@Reference private TwitterStatus status;
	private Date fechaCreacion, fechaLectura, fechaFinalizacion;
	private int estado, categoria;
	private boolean asignado;
	
	public TwitterCaso() {
		//MANDATORY
	}
	
	public TwitterCaso(TwitterStatus status, TwitterUser user) {
		this.status = status;
		this.user = user;
		fechaCreacion = new Date();
		estado = Constants.SIN_REVISAR;
		asignado = false;
		categoria = status.getCategoria();
	}

	public TwitterUser getUser() {
		return user;
	}

	public TwitterStatus getStatus() {
		return status;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public Date getFechaLectura() {
		return fechaLectura;
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public int getEstado() {
		return estado;
	}

	public boolean isAsignado() {
		return asignado;
	}

	public void setUser(TwitterUser user) {
		this.user = user;
	}

	public void setStatus(TwitterStatus status) {
		this.status = status;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setFechaLectura(Date fechaLectura) {
		this.fechaLectura = fechaLectura;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void setAsignado(boolean asignado) {
		this.asignado = asignado;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
}