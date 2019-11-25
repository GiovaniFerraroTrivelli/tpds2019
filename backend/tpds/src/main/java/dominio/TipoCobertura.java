package dominio;

import java.util.Set;

import dataTransferObjects.TipoCoberturaDTO;
import excepciones.NoHayValorException;
import historialValor.EntradaTipoCobertura;

public class TipoCobertura {
	private Integer idCobertura;
	private String nombre;
	private String descripcion;
	private Set<EntradaTipoCobertura> historialValores;

	public TipoCobertura() {

	}

	public TipoCobertura(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Integer getIdCobertura() {
		return this.idCobertura;
	}

	public void setIdCobertura(Integer idCobertura) {
		this.idCobertura = idCobertura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<EntradaTipoCobertura> getHistorialValores() {
		return historialValores;
	}

	public void setHistorialValores(Set<EntradaTipoCobertura> historialValores) {
		this.historialValores = historialValores;
	}

	public TipoCoberturaDTO getDTO() throws NoHayValorException {
		return new TipoCoberturaDTO(this.idCobertura, this.nombre, this.descripcion);
	}
}
