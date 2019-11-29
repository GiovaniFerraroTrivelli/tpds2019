package dataTransferObjects;

import dominio.Marca;

public class ModeloDTO {
	private Integer idModelo;
	private String nombre;
	private MarcaDTO marca;
	
	public ModeloDTO(Integer idModelo2, String nombre2, MarcaDTO marca2) {
		idModelo = idModelo2;
		nombre = nombre2;
		marca = marca2;
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

	public MarcaDTO getMarca() {
		return marca;
	}

	public void setMarca(MarcaDTO marca) {
		this.marca = marca;
	}
}
