package dominio;

import java.util.Set;

import dataTransferObjects.ModeloDTO;
import historialValor.EntradaModelo;
import historialValor.EntradaTipoCobertura;

public class Modelo {
	private Integer idModelo;
	private String nombre;
	private Marca marca;
	private Set<Cotizacion> anios;
	private Set<EntradaModelo> historialValores;

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

	public Set<Cotizacion> getAnios() {
		return anios;
	}

	public void setAnios(Set<Cotizacion> anios) {
		this.anios = anios;
	}

	public Set<EntradaModelo> getHistorialValores() {
		return historialValores;
	}

	public void setHistorialValores(Set<EntradaModelo> historialValores) {
		this.historialValores = historialValores;
	}
	
	public Integer getSumaAsegurada(Integer anio) {
		for(Cotizacion c : this.anios) {
			if(c.getAnio().equals(anio)) {
				Double ajuste = 1.0;
				if(c.getUnidad() != "ARS") {
					ajuste = 59.89;
				}
				return (int) ((int) c.getCotizacion()*1000*ajuste);
			}
		}
		
		System.out.println("here");
		return null;
	}

	public ModeloDTO getDTO() {
		return new ModeloDTO(this.idModelo, this.nombre, this.marca.getDTO());
	}

}
