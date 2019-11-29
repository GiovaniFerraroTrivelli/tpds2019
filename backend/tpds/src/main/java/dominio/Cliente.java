package dominio;

import java.util.Date;

import dataTransferObjects.ClienteDTO;
import enumeradores.CondicionCliente;
import enumeradores.CondicionIva;
import enumeradores.EstadoCivil;
import enumeradores.Sexo;

public class Cliente {
	// TODO: El número del cliente tiene que ser un entero largo
	private Integer idCliente;

	private NumeroCliente nroCliente;

	private String nombre;
	private String apellido;
	private Documento documento;
	// TODO: Cambiar a Integer
	private String cuil;
	private Sexo sexo;
	private Date fechaNacimiento;
	private String profesion;
	private EstadoCivil estadoCivil;
	private String email;
	private CondicionCliente condicionCliente;
	private CondicionIva condicionIva;
	private Direccion direccion;
	private Pais pais;

	public ClienteDTO getDTO() {
		ClienteDTO result = new ClienteDTO(this.idCliente, this.nombre, this.apellido, this.documento, this.cuil,
				this.sexo, this.fechaNacimiento, this.profesion, this.estadoCivil, this.email, this.condicionIva,
				this.direccion.getDTO());
		return result;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public NumeroCliente getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(NumeroCliente nroCliente) {
		this.nroCliente = nroCliente;
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

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
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

	public CondicionCliente getCondicionCliente() {
		return condicionCliente;
	}

	public void setCondicionCliente(CondicionCliente condicionCliente) {
		this.condicionCliente = condicionCliente;
	}

	public CondicionIva getCondicionIva() {
		return condicionIva;
	}

	public void setCondicionIva(CondicionIva condicionIva) {
		this.condicionIva = condicionIva;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
