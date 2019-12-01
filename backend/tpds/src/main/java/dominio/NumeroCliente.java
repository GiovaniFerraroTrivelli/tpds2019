package dominio;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NumeroCliente implements Serializable {
	private Integer idCliente;
	private Integer idPais;

	public NumeroCliente() {

	}

	public NumeroCliente(Integer idCliente, Integer idPais) {
		this.idCliente = idCliente;
		this.idPais = idPais;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdPais() {
		return idPais;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

}
