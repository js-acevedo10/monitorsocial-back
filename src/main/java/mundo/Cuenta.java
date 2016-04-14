package mundo;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity("Cuentas")
public class Cuenta {
	
	@Id ObjectId id;
	
	/**
	 * Nombre - nombre de la empresa
	 * Website - página web
	 * DireccionPrimaria - Direccion primaria
	 * DireccionSecundaria - En caso de tener otra direccion
	 * Ciudad - Ciudad de direccionPrimaria
	 * Departamento - Departamento Ciudad Primaria
	 * Pais - Pais Ciudad primaria
	 * correos
	 * Tipo - tipo de empresa (cliente, potencial, distribuidor, etc)
	 * Estado - El estado de la Empresa dado su tipo (activo, inactivo, cerrado)
	 * Sector - Industria
	 * Origen - El origen de la relacion con la empresa 
	 */
	private String nombre, website, direccionPrimaria, direccionSecundaria, ciudad, departamento, pais, correoInformacion, correoVentas, correoSoporte, correoGerencia, tipo, estado, sector, origen;
	
	private int codigoPostal, telefonoInformacion, telefonoVentas, telefonoSoporte, telefonoGerencia, extInformacion, extVentas, extSoporte, extGerencia;
	
	private Date fechaCreacion;
	
	@Reference private Empleado gestorDeCuenta;
	
	@Reference private List<Persona> personas;
	
	public Cuenta() {
		//MANDATORY
	}

	public Cuenta(String nombre, String website, String direccionPrimaria, String direccionSecundaria, String ciudad,
			String departamento, String pais, String correoInformacion, String correoVentas, String correoSoporte,
			String correoGerencia, String tipo, String estado, String sector, String origen, int codigoPostal,
			int telefonoInformacion, int telefonoVentas, int telefonoSoporte, int telefonoGerencia, int extInformacion,
			int extVentas, int extSoporte, int extGerencia) {
		super();
		this.nombre = nombre;
		this.website = website;
		this.direccionPrimaria = direccionPrimaria;
		this.direccionSecundaria = direccionSecundaria;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.pais = pais;
		this.correoInformacion = correoInformacion;
		this.correoVentas = correoVentas;
		this.correoSoporte = correoSoporte;
		this.correoGerencia = correoGerencia;
		this.tipo = tipo;
		this.estado = estado;
		this.sector = sector;
		this.origen = origen;
		this.codigoPostal = codigoPostal;
		this.telefonoInformacion = telefonoInformacion;
		this.telefonoVentas = telefonoVentas;
		this.telefonoSoporte = telefonoSoporte;
		this.telefonoGerencia = telefonoGerencia;
		this.extInformacion = extInformacion;
		this.extVentas = extVentas;
		this.extSoporte = extSoporte;
		this.extGerencia = extGerencia;
		fechaCreacion = new Date();
	}
	
	public String getId() {
		return id.toString();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDireccionPrimaria() {
		return direccionPrimaria;
	}

	public void setDireccionPrimaria(String direccionPrimaria) {
		this.direccionPrimaria = direccionPrimaria;
	}

	public String getDireccionSecundaria() {
		return direccionSecundaria;
	}

	public void setDireccionSecundaria(String direccionSecundaria) {
		this.direccionSecundaria = direccionSecundaria;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCorreoInformacion() {
		return correoInformacion;
	}

	public void setCorreoInformacion(String correoInformacion) {
		this.correoInformacion = correoInformacion;
	}

	public String getCorreoVentas() {
		return correoVentas;
	}

	public void setCorreoVentas(String correoVentas) {
		this.correoVentas = correoVentas;
	}

	public String getCorreoSoporte() {
		return correoSoporte;
	}

	public void setCorreoSoporte(String correoSoporte) {
		this.correoSoporte = correoSoporte;
	}

	public String getCorreoGerencia() {
		return correoGerencia;
	}

	public void setCorreoGerencia(String correoGerencia) {
		this.correoGerencia = correoGerencia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public int getTelefonoInformacion() {
		return telefonoInformacion;
	}

	public void setTelefonoInformacion(int telefonoInformacion) {
		this.telefonoInformacion = telefonoInformacion;
	}

	public int getTelefonoVentas() {
		return telefonoVentas;
	}

	public void setTelefonoVentas(int telefonoVentas) {
		this.telefonoVentas = telefonoVentas;
	}

	public int getTelefonoSoporte() {
		return telefonoSoporte;
	}

	public void setTelefonoSoporte(int telefonoSoporte) {
		this.telefonoSoporte = telefonoSoporte;
	}

	public int getTelefonoGerencia() {
		return telefonoGerencia;
	}

	public void setTelefonoGerencia(int telefonoGerencia) {
		this.telefonoGerencia = telefonoGerencia;
	}

	public int getExtInformacion() {
		return extInformacion;
	}

	public void setExtInformacion(int extInformacion) {
		this.extInformacion = extInformacion;
	}

	public int getExtVentas() {
		return extVentas;
	}

	public void setExtVentas(int extVentas) {
		this.extVentas = extVentas;
	}

	public int getExtSoporte() {
		return extSoporte;
	}

	public void setExtSoporte(int extSoporte) {
		this.extSoporte = extSoporte;
	}

	public int getExtGerencia() {
		return extGerencia;
	}

	public void setExtGerencia(int extGerencia) {
		this.extGerencia = extGerencia;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Empleado getGestorDeCuentas() {
		return gestorDeCuenta;
	}

	public void setGestorDeCuentas(Empleado gestorDeCuentas) {
		this.gestorDeCuenta = gestorDeCuentas;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
	public void addPersona(Persona persona) {
		this.personas.add(persona);
	}
	
}
