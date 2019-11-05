package dominio;

import dataTransferObjects.TipoCoberturaDTO;
import excepciones.NoHayValorException;
import historialValor.HistorialValor;

public class TipoCobertura {
	private Integer idCobertura;
	private String nombre;
	private String descripcion;
	private HistorialValor<Float> factorCobertura;

	public TipoCobertura() {

	}

	public TipoCobertura(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
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

	public HistorialValor<Float> getFactorCobertura() {
		return factorCobertura;
	}

	public void setFactorCobertura(HistorialValor<Float> factorCobertura) {
		this.factorCobertura = factorCobertura;
	}

	public void setIdCobertura(Integer idCobertura) {
		this.idCobertura = idCobertura;
	}

	public TipoCobertura(Integer id, String nombre2, String descripcion2, HistorialValor<Float> historial) {
		idCobertura = id;
		nombre = nombre2;
		descripcion = descripcion2;
		factorCobertura = historial;
	}

	public TipoCoberturaDTO getDTO() throws NoHayValorException {
		return new TipoCoberturaDTO(this.idCobertura, this.nombre, this.descripcion);
	}

	public Integer getIdCobertura() {
		return this.idCobertura;
	}

	public void setHistorialFactorCobertura(HistorialValor<Float> historialFactorCobertura) {
		// TODO Auto-generated method stub

	}
}
