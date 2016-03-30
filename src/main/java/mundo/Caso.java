package mundo;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import utilidades.Constantes;

@Entity("casos")
public class Caso {
	
	@Id ObjectId id;
	/**
	 * Titulo - Nombre Corto
	 * Descripcion - Descripción Corta
	 * Detalles - Extensión del caso
	 * userId - nombre de usuario
	 */
	private String titulo, descripcion, detalles, twitterUserId, mongoUserId;
	
	/**
	 * Prioridad - Urgencia por resolver
	 * Etapa - Etapa en la que se encuentra
	 * Estado - Estado en el tiempo (pasado, presente, futuro)
	 * Gravedad - Impacto independiente de prioridad
	 * Categoria - Soporte, Queja, Reclamo, Peticion
	 */
	private int prioridad, etapa, estado, gravedad, categoria;
	
	/**
	 * FechaCreacion - Fecha en la que se registra
	 * FechaUltimaModificacion - Fecha en que cambia la etapa
	 */
	private Date fechaCreacion, fechaUltimaModificacion;
	
	private boolean casoTwitter, casoFacebook;
	
	/**
	 * Empresa - Empresa relacionada con el caso
	 */
	@Reference private Empresa empresa;
	
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

	public Caso() {
		//MANDATORY
	}
	
	public Caso(String userId, String titulo, String descripcion, String detalles, int prioridad, int gravedad, int categoria, int estado, String mongoUserId) {
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

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public int getEtapa() {
		return etapa;
	}

	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getGravedad() {
		return gravedad;
	}

	public void setGravedad(int gravedad) {
		this.gravedad = gravedad;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
}
