package restControllers;

public class UserLogin {
	private String nombreUsuario;
	private String password;
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String username) {
		this.nombreUsuario = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
