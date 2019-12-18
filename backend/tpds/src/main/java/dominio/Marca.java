package dominio;

import dataTransferObjects.MarcaDTO;

public class Marca {
	private Integer idMarca;
	private String nombre;

	public Marca() {

	}

	public Marca(Integer idMarca, String nombre) {
		this.idMarca = idMarca;
		this.nombre = nombre;
	}

	public Integer getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public MarcaDTO getDTO() {
		return new MarcaDTO(this.idMarca, this.nombre);
	}

}
