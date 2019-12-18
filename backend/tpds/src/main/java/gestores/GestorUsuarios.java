package gestores;

import dao.DaoUsuario;
import usuarios.Usuario;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class GestorUsuarios{
	
	
	public static Usuario getUsuario(String nombreUsuario) {
		return DaoUsuario.getUsuario(nombreUsuario);
	}
	
	public static Boolean autenticarUsuario(Usuario usuario, String password){
		return BCrypt.checkpw(password, usuario.getHashedPassword());
	}
}
