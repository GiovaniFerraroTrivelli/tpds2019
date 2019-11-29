package dataTransferObjects;

import dominio.Documento;
import dominio.NumeroCliente;
import enumeradores.CondicionIva;

public class ParametrosDeConsulta {
	private NumeroCliente nroCliente;
	private String nombre;
	private String apellido;
	private Documento documento;
	private CondicionIva condicionIva;
	private Integer numeroPagina;
	private Integer resultadosPorPagina;

	public ParametrosDeConsulta() {
		// TODO Auto-generated constructor stub
	}

	public NumeroCliente getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(NumeroCliente nroCliente) {
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

	public boolean nulo() {
		Boolean idClienteNulo = false;
		Boolean nombreNulo = false;
		Boolean apellidoNulo = false;
		Boolean documentoNulo = false;
		Boolean condicionIvaNulo = false;

		if (this.getNroCliente() == null || this.getNroCliente().getIdCliente() == null
				|| this.getNroCliente().getIdPais() == null)
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
