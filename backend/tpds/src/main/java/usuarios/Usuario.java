package usuarios;

import java.util.Set;

import dominio.Hijo;
import enumeradores.Rol;

public class Usuario {
	private String nombreUsuario;
	private String hashedPassword;
	private String nombre;
	private String apellido;
	private String email;
	private Rol rol;

	
	public class UsuarioDTO{
		private String nombreUsuario;
		private String nombre;
		private String apellido;
		private String email;
		private Rol rol;
		public String getNombreUsuario() {
			return nombreUsuario;
		}
		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApellido() {
			return apellido;
		}
		public void setApellido(String apellido) {
			this.apellido = apellido;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Rol getRol() {
			return rol;
		}
		public void setRol(Rol rol) {
			this.rol = rol;
		}
	}
	
	
	public UsuarioDTO getDTO() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		
		usuarioDTO.setNombreUsuario(this.nombreUsuario);
		usuarioDTO.setNombre(this.nombre);
		usuarioDTO.setApellido(this.apellido);
		usuarioDTO.setEmail(this.email);
		usuarioDTO.setRol(this.rol);
		
		return usuarioDTO;
	}
	
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
