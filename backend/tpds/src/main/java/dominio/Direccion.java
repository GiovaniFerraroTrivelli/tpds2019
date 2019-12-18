package dominio;

import dataTransferObjects.DireccionDTO;


public class Direccion {
	private Integer idDireccion;
	private String calle;
	private Integer numero;
	private Integer piso;
	private String departamento;
	private Localidad localidad;
	private String codigoPostal;

	public Direccion() {
		// TODO Auto-generated constructor stub
	}

	public Direccion(String calle2, Integer numero2, Integer piso2, String departamento2, Localidad localidad2, String cPA) {
		calle = calle2;
		numero = numero2;
		piso = piso2;
		departamento = departamento2;
		localidad = localidad2;
		codigoPostal = cPA;
	}

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

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public DireccionDTO getDTO() {
		return new DireccionDTO(this.calle, this.numero, this.piso, this.departamento, this.localidad.getDTO(), this.codigoPostal);
	}

	public Integer getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Integer idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
}
