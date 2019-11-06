package dominio;

public class AnioModelo {
	private Integer idAnioModelo;
	private Modelo modelo;
	private Integer valor;

	public AnioModelo() {

	}

	public Integer getIdAnioModelo() {
		return idAnioModelo;
	}

	public void setIdAnioModelo(Integer idAnioModelo) {
		this.idAnioModelo = idAnioModelo;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
