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

import mundo.Cuenta;
import mundo.Empleado;
import mundo.Persona;
import mundo.TwitterStatus;
import mundo.TwitterUser;
import utilidades.Constantes;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity("Casos")
public class Caso {
	
	@Id ObjectId id;
	/**
	 * Titulo - Nombre Corto
	 * Descripcion - Descripción Corta
	 * Detalles - Extensión del caso
	 * userId - nombre de usuario
	 * Prioridad - Urgencia por resolver
	 * Etapa - Etapa en la que se encuentra
	 * Estado - Estado en el tiempo (pasado, presente, futuro)
	 * Gravedad - Impacto independiente de prioridad
	 * Categoria - Soporte, Queja, Reclamo, Peticion
	 */
	private String titulo, descripcion, detalles, twitterUserId, mongoUserId, categoria, etapa, estado, prioridad, gravedad, twitterUserName;
	
	/**
	 * FechaCreacion - Fecha en la que se registra
	 * FechaUltimaModificacion - Fecha en que cambia la etapa
	 */
	private Date fechaCreacion, fechaUltimaModificacion;
	
	private boolean casoTwitter, casoFacebook;
	
	/**
	 * Empresa - Empresa relacionada con el caso
	 */
	@Reference private Cuenta cuenta;
	
	/**
	 * Persona - Persona de la empresa relacionada con el caso
	 */
	@Reference private Persona persona;
	
	/**
	 * EmpleadoAsignado - Empleado asignado al caso
	 */
	@Reference private Empleado empleadoAsignado;
	
	/**
	 * TwitterUser - De tratarse de un caso twitter, el usuario
	 */
	@Reference private TwitterUser twitterUser;
	
	/**
	 * TwitterStatus - De tratarse de un caso twitter, el estado
	 */
	@Reference private TwitterStatus twitterStatus;
	
	@Reference private List<Nota> notas;
	
	@Reference private List<Historia> historial;
	
	@Reference private ConversacionTwitter conversacion;

	public Caso() {
		//MANDATORY
	}
	
	public Caso(String userId, String titulo, String descripcion, String detalles, String prioridad, String gravedad, String categoria, String estado, String mongoUserId) {
		this.twitterUserId = userId;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.detalles = detalles;
		this.prioridad = prioridad;
		this.etapa = Constantes.CASO_ETAPA_REGISTRADA;
		this.estado = estado;
		this.gravedad = gravedad;
		this.categoria = categoria;
		this.fechaCreacion = new Date();
		this.setMongoUserId(mongoUserId);
	}
	
	public String getUserId() {
		return twitterUserId;
	}
	
	public void setUserId(String userId) {
		this.twitterUserId = userId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getGravedad() {
		return gravedad;
	}

	public void setGravedad(String gravedad) {
		this.gravedad = gravedad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public boolean isCasoTwitter() {
		return casoTwitter;
	}

	public void setCasoTwitter(boolean casoTwitter) {
		this.casoTwitter = casoTwitter;
	}

	public boolean isCasoFacebook() {
		return casoFacebook;
	}

	public void setCasoFacebook(boolean casoFacebook) {
		this.casoFacebook = casoFacebook;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Empleado getEmpleadoAsignado() {
		return empleadoAsignado;
	}

	public void setEmpleadoAsignado(Empleado empleadoAsignado) {
		this.empleadoAsignado = empleadoAsignado;
	}

	public TwitterUser getTwitterUser() {
		return twitterUser;
	}

	public void setTwitterUser(TwitterUser twitterUser) {
		this.twitterUser = twitterUser;
	}

	public TwitterStatus getTwitterStatus() {
		return twitterStatus;
	}

	public void setTwitterStatus(TwitterStatus twitterStatus) {
		this.twitterStatus = twitterStatus;
	}

	public String getMongoUserId() {
		return mongoUserId;
	}

	public void setMongoUserId(String mongoUserId) {
		this.mongoUserId = mongoUserId;
	}

	public String getTwitterUserId() {
		return twitterUserId;
	}

	public void setTwitterUserId(String twitterUserId) {
		this.twitterUserId = twitterUserId;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
	
	public void addNota(Nota nota) {
		if(this.notas == null) {
			this.notas = new ArrayList<Nota>();
		}
		this.notas.add(nota);
	}

	public List<Historia> getHistorial() {
		return historial;
	}

	public void setHistorial(List<Historia> historial) {
		this.historial = historial;
	}
	
	public void addHistoria(Historia historia) {
		if(historial == null) {
			historial = new ArrayList<Historia>();
		}
		historial.add(historia);
	}

	public String getTwitterUserName() {
		return twitterUserName;
	}

	public void setTwitterUserName(String twitterUserName) {
		this.twitterUserName = twitterUserName;
	}

	public ConversacionTwitter getConversacion() {
		return conversacion;
	}

	public void setConversacion(ConversacionTwitter conversacion) {
		this.conversacion = conversacion;
	}
}
