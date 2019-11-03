package dominio;

import dataTransferObjects.ModeloDTO;

public class Modelo {
	private Integer idModelo;
	private String nombre;
	private Marca marca;

	public Modelo() {

	}

	public Modelo(Integer idModelo, String nombre, Marca marca) {
		super();
		this.idModelo = idModelo;
		this.nombre = nombre;
		this.marca = marca;
	}

	public Integer getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public ModeloDTO getDTO() {
		return new ModeloDTO(this.idModelo, this.nombre, this.marca.getDTO());
	}

}
