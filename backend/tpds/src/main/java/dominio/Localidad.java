package dominio;

import java.util.Set;

import dataTransferObjects.LocalidadDTO;
import historialValor.EntradaLocalidad;

public class Localidad {
	private Integer idLocalidad;
	private String nombre;
	private String CPA;
	private Provincia provincia;
	private Set<EntradaLocalidad> historialValores;

	public Integer getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(Integer idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String Nombre) {
		nombre = Nombre;
	}

	public String getCPA() {
		return CPA;
	}

	public void setCPA(String cPA) {
		CPA = cPA;
	}

	public Set<EntradaLocalidad> getHistorialValores() {
		return historialValores;
	}

	public void setHistorialValores(Set<EntradaLocalidad> historialValores) {
		this.historialValores = historialValores;
	}

	public LocalidadDTO getDTO() {
		LocalidadDTO result = new LocalidadDTO();
		result.setCPA(this.CPA);
		result.setNombre(this.nombre);
		result.setProvinciaId(this.provincia.getIdProvincia());
		result.setIdLocalidad(this.idLocalidad);
		return result;
	}
}
