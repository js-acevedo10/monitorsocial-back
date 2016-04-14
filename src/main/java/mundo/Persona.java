package mundo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity("Personas")
public class Persona {
	@Id ObjectId id;
	
	/**
	 * Nombre - Nombre de la persona
	 * Cargo - El cargo dentro de la Cuenta
	 * Titulo - Mr, Ms, Mrs, Dr, etc
	 * Correo - email
	 * NombreCuenta - Empresa para la que trabaja
	 * IdCuenta - El id de la empresa para la que trabaja
	 */
	private String nombre, cargo, titulo, correo, direccionPrimaria, direccionSecundaria, ciudad, departamento, pais, nombreCuenta, idCuenta;
	private int celular, telefono;
	public Persona() {
		
	}
	public Persona(String nombre, String cargo, String titulo, String correo, String direccionPrimaria,
			String direccionSecundaria, String ciudad, String departamento, String pais, String nombreCuenta,
			String idCuenta, int celular, int telefono) {
		this.nombre = nombre;
		this.cargo = cargo;
		this.titulo = titulo;
		this.correo = correo;
		this.direccionPrimaria = direccionPrimaria;
		this.direccionSecundaria = direccionSecundaria;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.pais = pais;
		this.nombreCuenta = nombreCuenta;
		this.idCuenta = idCuenta;
		this.celular = celular;
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
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
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	public String getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}
	public int getCelular() {
		return celular;
	}
	public void setCelular(int celular) {
		this.celular = celular;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
}
