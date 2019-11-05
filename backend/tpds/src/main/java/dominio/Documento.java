package dominio;

import enumeradores.TipoDocumento;

public class Documento {
	private TipoDocumento tipoDocumento;
	private Integer nroDocumento;

	public Documento() {
	}

	public Documento(TipoDocumento tipoDocumento, Integer nroDocumento) {
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(Integer nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
}
