package dominio;

import dataTransferObjects.TipoCoberturaDTO;
import excepciones.NoHayValorException;
import historialValor.HistorialValor;

public class TipoCobertura {
	private Integer idCobertura;
	private String nombre;
	private String descripcion;
	private HistorialValor<Float> factorCobertura;
	
	public TipoCobertura(Integer id, String nombre2, String descripcion2, HistorialValor<Float> historial) {
		idCobertura = id;
		nombre = nombre2;
		descripcion = descripcion2;
		factorCobertura = historial;
	}
	
	public TipoCoberturaDTO getDTO() throws NoHayValorException{
		return new TipoCoberturaDTO(this.idCobertura, this.nombre, this.descripcion, this.factorCobertura.valorActual());
	}

	public Integer getIdCobertura() {
		return this.idCobertura;
	}

	public void setHistorialFactorCobertura(HistorialValor<Float> historialFactorCobertura) {
		// TODO Auto-generated method stub
		
	}
}
