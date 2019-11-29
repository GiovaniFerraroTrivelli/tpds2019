package dominio;

import java.io.Serializable;

public class NumeroCliente implements Serializable {
	private Integer idCliente;
	private Integer idPais;


	public Integer getIdPais() {
		return idPais;
	}

	public NumeroCliente(Integer idCliente, Integer idPais) {
		this.idPais = idPais;
		this.idCliente = idCliente;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

}
