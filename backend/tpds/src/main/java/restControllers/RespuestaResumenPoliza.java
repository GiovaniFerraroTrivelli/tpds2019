package restControllers;

import java.util.ArrayList;
import dominio.Poliza.ResumenPoliza;;

public class RespuestaResumenPoliza {
	private ArrayList<Error> errores;
	private ResumenPoliza datosPoliza;
	private String token;
	
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
