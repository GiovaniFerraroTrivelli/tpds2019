package dominio;

import java.util.Set;

import dataTransferObjects.ProvinciaDTO;

public class Provincia {
	private Integer idProvincia;
	private Pais pais;
	private String nombre;


	public Provincia() {

	}

	public Provincia(Integer idProvincia, Pais pais, String nombre) {
		super();
		this.idProvincia = idProvincia;
		this.pais = pais;
		this.nombre = nombre;
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
	
	public Set<Localidad> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(Set<Localidad> localidades) {
		this.localidades = localidades;
	}

	private Set<Localidad> localidades;

	public ProvinciaDTO getDTO() {
		ProvinciaDTO result = new ProvinciaDTO();
		result.setIdProvincia(this.idProvincia);
		result.setNombre(this.nombre);
		result.setIdPais(this.pais.getIdPais());
		return result;
	}
}
