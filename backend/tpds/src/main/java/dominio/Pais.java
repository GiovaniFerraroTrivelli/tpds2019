package dominio;

import java.util.Set;

import DTOs.PaisDTO;

public class Pais {
	private Integer idPais;
	private String nombre;
	private Set<Provincia> provincias;

	public Pais() {

	}

	public Integer getIdPais() {
		return idPais;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(Set<Provincia> provincias) {
		this.provincias = provincias;
	}
	
	public PaisDTO getDTO() {
		PaisDTO result = new PaisDTO();
		result.setIdPais(this.idPais);
		result.setNombre(this.nombre);
		return result;
	}

}
