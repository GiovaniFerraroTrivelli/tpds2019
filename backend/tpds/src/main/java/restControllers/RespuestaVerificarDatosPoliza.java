package restControllers;

import java.util.ArrayList;

import dataTransferObjects.TipoCoberturaDTO;
import dominio.TipoCobertura;

public class RespuestaVerificarDatosPoliza {
	private ArrayList<Error> errores;
	private ArrayList<TipoCoberturaDTO> coberturasDisponibles;

	public ArrayList<Error> getErrores() {
		return errores;
	}

	public void setErrores(ArrayList<Error> errores) {
		this.errores = errores;
	}

	public ArrayList<TipoCoberturaDTO> getCoberturasDisponibles() {
		return coberturasDisponibles;
	}

	public void setCoberturasDisponibles(ArrayList<TipoCoberturaDTO> coberturasDisponibles) {
		this.coberturasDisponibles = coberturasDisponibles;
	}
}
