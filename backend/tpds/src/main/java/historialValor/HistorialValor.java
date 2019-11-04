package historialValor;

import java.sql.Timestamp;
import java.util.ArrayList;

import excepciones.NoHayValorException;

public class HistorialValor<T> {
	protected ArrayList<Entrada<T>> historial;

	public ArrayList<Entrada<T>> getHistorial() {
		return historial;
	}

	public void setHistorial(ArrayList<Entrada<T>> historial) {
		this.historial = historial;
	}

	public T valorActual() throws NoHayValorException {
		for (Entrada<T> entrada : historial) {
			if (entrada.getFinVigencia() == null)
				return entrada.getValor();
		}

		throw new NoHayValorException();
	}

	public T valorEnFecha(Timestamp fecha) throws NoHayValorException {
		for (Entrada<T> entrada : historial) {
			if (entrada.getFinVigencia() != null && entrada.getInicioVigencia() != null
					&& entrada.getFinVigencia().compareTo(fecha) <= 0
					&& entrada.getInicioVigencia().compareTo(fecha) >= 0)
				return entrada.getValor();
		}

		throw new NoHayValorException();
	}
	
}
