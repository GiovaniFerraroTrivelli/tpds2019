package restControllers;

import java.util.ArrayList;
import dominio.Poliza.ResumenPoliza;;

public class RespuestaResumenPoliza {
	private ArrayList<Error> errores;
	private ResumenPoliza datosPoliza;
	
	public ArrayList<Error> getErrores() {
		return errores;
	}
	public void setErrores(ArrayList<Error> errores) {
		this.errores = errores;
	}
	public ResumenPoliza getDatosPoliza() {
		return datosPoliza;
	}
	public void setDatosPoliza(ResumenPoliza datosPoliza) {
		this.datosPoliza = datosPoliza;
	}
	
	
}
