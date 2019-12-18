package historialValor;

import java.sql.Timestamp;

public class Entrada {
	private Integer idEntrada;
	private Double valor;
	Timestamp inicioVigencia;
	Timestamp finVigencia;

	public Integer getIdEntrada() {
		return idEntrada;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setIdEntrada(Integer idEntrada) {
		this.idEntrada = idEntrada;
	}

	public Timestamp getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Timestamp inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public Timestamp getFinVigencia() {
		return finVigencia;
	}

	public void setFinVigencia(Timestamp finVigencia) {
		this.finVigencia = finVigencia;
	}
}
