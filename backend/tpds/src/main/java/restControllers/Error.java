package restControllers;

public class Error {
	private String error = "no encontrado";
	
	public Error(String mensaje) {
		error = mensaje;
	}
	
	public String getMensaje() {
		return this.error;
	}
}
