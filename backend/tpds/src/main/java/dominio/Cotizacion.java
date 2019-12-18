package dominio;

public class Cotizacion {
	private Integer idCotizacion;
	private Modelo modelo;
	private Integer anio;
	private Integer cotizacion;
	private String unidad;

	public Cotizacion() {

	}

	public Integer getIdCotizacion() {
		return idCotizacion;
	}

	public void setIdCotizacion(Integer idAnioModelo) {
		this.idCotizacion = idAnioModelo;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Integer cotizacion) {
		this.cotizacion = cotizacion;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

}
