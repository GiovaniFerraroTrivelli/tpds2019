package dataTransferObjects;


public class TipoCoberturaDTO {

	private Integer idCobertura;
	private String nombre;
	private String descripcion;
	private Float factorCoberturaActual;
	
	public TipoCoberturaDTO(Integer id, String nombre2, String descripcion2, Float factorCoberturaActual2) {
		idCobertura = id;
		nombre = nombre2;
		descripcion = descripcion2;
		factorCoberturaActual = factorCoberturaActual2;
	}
	
	public TipoCoberturaDTO(Integer idCobertura, String nombre, String descripcion) {
		super();
		this.idCobertura = idCobertura;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}


	public Integer getIdCobertura() {
		return idCobertura;
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
}
