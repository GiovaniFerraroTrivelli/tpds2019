package dataTransferObjects;

import java.util.Date;

import dominio.Documento;
import enumeradores.CondicionIva;
import enumeradores.EstadoCivil;
import enumeradores.Sexo;

public class ClienteDTO {
	private Integer idCliente;
	private String nombre;
	private String apellido;
	private Documento documento;
	private String cuil;
	private Sexo sexo;
	private Date fechaNacimiento;
	private String profesion;
	private EstadoCivil estadoCivil;
	private String email;
	private CondicionIva condicionIva;
	private DireccionDTO direccion;

	public ClienteDTO(Integer idCliente, String nombre, String apellido, Documento documento, String cuil, Sexo sexo,
			Date fechaNacimiento, String profesion, EstadoCivil estadoCivil, String email, CondicionIva condicionIva,
			DireccionDTO direccion) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.cuil = cuil;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.profesion = profesion;
		this.estadoCivil = estadoCivil;
		this.email = email;
		this.condicionIva = condicionIva;
		this.direccion = direccion;
	}

	public ClienteDTO() {
		// TODO Auto-generated constructor stub
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

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
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

}
