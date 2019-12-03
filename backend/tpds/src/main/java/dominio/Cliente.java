package dominio;

import java.util.Date;

import dataTransferObjects.ClienteDTO;
import enumeradores.CondicionCliente;
import enumeradores.CondicionIva;
import enumeradores.EstadoCivil;
import enumeradores.Sexo;
import enumeradores.TipoDocumento;

public class Cliente {
	private NumeroCliente nroCliente;
	private String nombre;
	private String apellido;
	private Documento documento;
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
	private Integer cantidadPolizas;

	public ClienteDTO getDTO() {
		ClienteDTO result = new ClienteDTO(this.nroCliente(), this.nombre, this.apellido, this.documento, this.cuil,
				this.sexo, this.fechaNacimiento, this.profesion, this.estadoCivil, this.email, this.condicionIva,
				this.direccion.getDTO());
		return result;
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

	public String nroCliente() {
		String idPais = Integer.toString(this.nroCliente.getIdPais());
		String idCliente = Integer.toString(this.nroCliente.getIdCliente());

		StringBuilder result = new StringBuilder();
		if (idPais.length() != 2) {
			result.append("0");
		}
		result.append(idPais);
		Integer ceros = 8 - idCliente.length();
		for (int i = 0; i < ceros; i++) {
			result.append("0");
		}
		result.append(idCliente);
		return new String(result);
	}

	public Integer getCantidadPolizas() {
		return cantidadPolizas;
	}

	public void setCantidadPolizas(Integer cantidadPolizas) {
		this.cantidadPolizas = cantidadPolizas;
	}

	public static class Documento {
		private TipoDocumento tipoDocumento;
		private Integer nroDocumento;

		public Documento() {
		}

		public Documento(TipoDocumento tipoDocumento, Integer nroDocumento) {
			this.tipoDocumento = tipoDocumento;
			this.nroDocumento = nroDocumento;
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
	}

}
