package dominio;

import dataTransferObjects.DireccionDTO;
import dataTransferObjects.LocalidadDTO;

public class Direccion {
	private String calle;
	private Integer numero;
	private Integer piso;
	private String departamento;
	private Localidad localidad;
	
	public Direccion(String calle2, Integer numero2, Integer piso2, String departamento2, Localidad localidad2) {
		calle =calle2;
		numero = numero2;
		piso = piso2;
		departamento = departamento2;
		localidad =localidad2;
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
		return new DireccionDTO(this.calle, this.numero, this.piso, this.departamento, this.localidad.getDTO());
	}
}
