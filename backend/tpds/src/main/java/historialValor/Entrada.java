package historialValor;

import java.sql.Timestamp;

public class Entrada<T> {
	private T valor;
	Timestamp inicioVigencia;
	Timestamp finVigencia;
	
	public T getValor() {
		return valor;
	}
	public void setValor(T valor) {
		this.valor = valor;
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
