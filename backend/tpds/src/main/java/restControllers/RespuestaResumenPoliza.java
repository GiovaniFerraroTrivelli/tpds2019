package restControllers;

import java.util.ArrayList;

public class RespuestaResumenPoliza {
	private ArrayList<Error> errores;
	private ResumenPolizaDTO datosPoliza;
	
	public ArrayList<Error> getErrores() {
		return errores;
	}
	public void setErrores(ArrayList<Error> errores) {
		this.errores = errores;
	}
	public ResumenPolizaDTO getDatosPoliza() {
		return datosPoliza;
	}
	public void setDatosPoliza(ResumenPolizaDTO datosPoliza) {
		this.datosPoliza = datosPoliza;
	}
	
	
}
