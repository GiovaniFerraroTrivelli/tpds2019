package restControllers;

import java.util.ArrayList;

import dataTransferObjects.TipoCoberturaDTO;

public class RespuestaValidarDatosPoliza {
	private ArrayList<Error> errores;
	private ArrayList<TipoCoberturaDTO> coberturasDisponibles;

	public ArrayList<Error> getErrores() {
		return errores;
	}

	public void setErrores(ArrayList<Error> arrayList) {
		this.errores = arrayList;
	}

	public ArrayList<TipoCoberturaDTO> getCoberturasDisponibles() {
		return coberturasDisponibles;
	}

	public void setCoberturasDisponibles(ArrayList<TipoCoberturaDTO> coberturasDisponibles) {
		this.coberturasDisponibles = coberturasDisponibles;
	}
}
