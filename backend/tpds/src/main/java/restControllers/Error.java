package restControllers;

public class Error {
	private String mensaje = "no encontrado";

	public Error  Error(String mensaje) {
		
		this.mensaje = mensaje;
		return this;
	}
}
