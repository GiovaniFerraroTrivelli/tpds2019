package dataTransferObjects;

import dominio.Documento;

public class ParametrosDeBusqueda {
	private Integer idCliente;
	private String nombre;
	private String apellido;
	private Documento documento;
	private Integer numeroPagina;
	private Integer resultadosPorPagina;

	public ParametrosDeBusqueda(Integer idCliente, String nombre, String apellido, Documento documento) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
	}

	public ParametrosDeBusqueda(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Integer numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public Integer getResultadosPorPagina() {
		return resultadosPorPagina;
	}

	public void setResultadosPorPagina(Integer resultadosPorPagina) {
		this.resultadosPorPagina = resultadosPorPagina;
	}

	public boolean nulo() {
		Boolean idClienteNulo = false;
		Boolean nombreNulo = false;
		Boolean apellidoNulo = false;
		Boolean documentoNulo = false;

		if (this.getIdCliente() == null)
			idClienteNulo = true;
		if (this.getNombre() == null || this.getNombre() == "")
			nombreNulo = true;
		if (this.getApellido() == null || this.getApellido() == "")
			apellidoNulo = true;
		if (this.getDocumento() == null)
			documentoNulo = true;
		else {
			if (this.getDocumento().getTipoDocumento() == null && this.getDocumento().getNroDocumento() == null)
				documentoNulo = true;
		}

		return idClienteNulo && nombreNulo && apellidoNulo && documentoNulo;
	}

	public boolean paginaValida() {
		return (this.getNumeroPagina() != null && this.getNumeroPagina() >= 1 && this.getResultadosPorPagina() != null
				&& this.getResultadosPorPagina() >= 1);
	}
}
