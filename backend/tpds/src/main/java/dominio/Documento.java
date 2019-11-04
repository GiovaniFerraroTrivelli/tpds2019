package dominio;

public class Documento {
	private Integer idDocumento;
	private TipoDocumento tipoDocumento;
	private Integer nroDocumento;

	public Documento() {
	}


//	public Documento(TipoDocumento tipoDocumento, Integer nroDocumento, Integer idCliente) {
//		super();
//		this.tipoDocumento = tipoDocumento;
//		this.nroDocumento = nroDocumento;
//		this.idCliente = idCliente;
//	}



	public Documento(TipoDocumento tipoDocumento, Integer nroDocumento) {
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
	}


	public Integer getIdDocumento() {
		return idDocumento;
	}


	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
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
