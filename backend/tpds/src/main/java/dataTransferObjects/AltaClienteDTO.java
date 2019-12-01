package dataTransferObjects;

import java.util.Date;

import dominio.Documento;
import enumeradores.CondicionIva;
import enumeradores.EstadoCivil;
import enumeradores.Sexo;

public class AltaClienteDTO {

	public class AltaClienteDireccion {
		private String calle;
		private Integer numero;
		private Integer piso;
		private String departamento;
		private String codigoPostal;
		private Integer Localidad;

		public String getCalle() {
			return calle;
		}

		public void setCalle(String calle) {
			this.calle = calle;
		}

		public Integer getNumero() {
			return numero;
		}

		public void setNumero(Integer numero) {
			this.numero = numero;
		}

		public Integer getPiso() {
			return piso;
		}

		public void setPiso(Integer piso) {
			this.piso = piso;
		}

		public String getDepartamento() {
			return departamento;
		}

		public void setDepartamento(String departamento) {
			this.departamento = departamento;
		}

		public String getCodigoPostal() {
			return codigoPostal;
		}

		public void setCodigoPostal(String codigoPostal) {
			this.codigoPostal = codigoPostal;
		}

		public Integer getLocalidad() {
			return Localidad;
		}

		public Integer getIdLocalidad() {
			return Localidad;
		}

		public void setLocalidad(Integer localidad) {
			Localidad = localidad;
		}
	}

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

	private AltaClienteDireccion direccion;

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

	public AltaClienteDireccion getDireccion() {
		return direccion;
	}

	public void setDireccion(AltaClienteDireccion direccion) {
		this.direccion = direccion;
	}

}
