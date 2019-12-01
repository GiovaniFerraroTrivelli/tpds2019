package dataTransferObjects;

import dominio.Documento;
import enumeradores.CondicionIva;

public class ParametrosDeConsulta {
	private String nroCliente;
	private String nombre;
	private String apellido;
	private Documento documento;
	private CondicionIva condicionIva;
	private Integer numeroPagina;
	private Integer resultadosPorPagina;

	public ParametrosDeConsulta() {

	}

	public String getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(String nroCliente) {
		this.nroCliente = nroCliente;
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

	public CondicionIva getCondicionIva() {
		return condicionIva;
	}

	public void setCondicionIva(CondicionIva condicionIva) {
		this.condicionIva = condicionIva;
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

	public Integer getIdCliente() {
		return Integer.parseInt(this.nroCliente.substring(2, this.nroCliente.length()));
	}

	public Integer getIdPais() {
		return Integer.parseInt(this.nroCliente.substring(0, 2));
	}

	public boolean nroClienteValido() {
		if (this.nroCliente == null || this.nroCliente.length() != 10) {
			return false;
		}

		try {
			Integer.parseInt(this.nroCliente);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public boolean nulo() {
		Boolean idClienteNulo = false;
		Boolean nombreNulo = false;
		Boolean apellidoNulo = false;
		Boolean documentoNulo = false;
		Boolean condicionIvaNulo = false;

		if (this.getNroCliente() == null || this.getNroCliente() == "")
			idClienteNulo = true;
		if (this.getNombre() == null || this.getNombre() == "")
			nombreNulo = true;
		if (this.getApellido() == null || this.getApellido() == "")
			apellidoNulo = true;
		if (this.getDocumento() == null)
			documentoNulo = true;
		if (this.getCondicionIva() == null)
			condicionIvaNulo = true;
		else {
			if (this.getDocumento().getTipoDocumento() == null)
				documentoNulo = true;
			if (this.getDocumento().getNroDocumento() == null)
				documentoNulo = true;
		}

		return idClienteNulo && nombreNulo && apellidoNulo && documentoNulo && condicionIvaNulo;
	}

	public boolean paginaValida() {
		return (this.getNumeroPagina() != null && this.getNumeroPagina() >= 1 && this.getResultadosPorPagina() != null
				&& this.getResultadosPorPagina() >= 1);
	}

}
