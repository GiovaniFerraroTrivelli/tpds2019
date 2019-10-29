package dominio;

import DTOs.ProvinciaDTO;

public class Provincia {
	private Integer idProvincia;
	private Pais pais;
	private String nombre;

	public Provincia() {

	}

	public Integer getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ProvinciaDTO getDTO() {
		ProvinciaDTO result = new ProvinciaDTO();
		result.setIdProvincia(this.idProvincia);
		result.setNombre(this.nombre);
		return result;
	}
}
