package dataTransferObjects;


public class DireccionDTO {
	private String calle;
	private Integer numero;
	private Integer piso;
	private String departamento;
	private LocalidadDTO localidad;
	private String codigoPostal;
	
	public DireccionDTO(String calle2, Integer numero2, Integer piso2, String departamento2, LocalidadDTO localidad2, String codigoPostal2) {
		calle =calle2;
		numero = numero2;
		piso = piso2;
		departamento = departamento2;
		localidad =localidad2;
		codigoPostal = codigoPostal2;
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

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
}
