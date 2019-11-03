package dataTransferObjects;

import java.util.Date;

import dominio.CondicionIva;
import dominio.Direccion;
import dominio.EstadoCivil;
import dominio.Sexo;
import dominio.TipoDocumento;

public class ClienteDTO {
	public ClienteDTO(Integer idCliente2, String nombre2, String apellido2, TipoDocumento tipoDocumento2,
			Integer nroDocumento2, DireccionDTO direccion2) {
		idCliente = idCliente2;
		nombre = nombre2;
		apellido = apellido2;
		tipoDocumento = tipoDocumento2;
		nroDocumento = nroDocumento2;
		direccion = direccion2;
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Integer getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(Integer nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public CondicionIva getCondicionIva() {
		return condicionIva;
	}
	public void setCondicionIva(CondicionIva condicionIva) {
		this.condicionIva = condicionIva;
	}
	public DireccionDTO getDireccion() {
		return direccion;
	}
	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}
	private Integer idCliente;
	private String nombre;
	private String apellido;
	private TipoDocumento tipoDocumento;
	private Integer nroDocumento;
	private String cuil;
	private Sexo sexo;
	private Date fechaNacimiento;
	private String profesion;
	private EstadoCivil estadoCivil;
	private String email;
	private CondicionIva condicionIva;
	private DireccionDTO direccion;

}
