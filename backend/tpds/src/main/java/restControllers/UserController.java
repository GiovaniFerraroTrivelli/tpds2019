package restControllers;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestores.GestorUsuarios;
import usuarios.Usuario;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserController {

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody UserLogin userLogin, HttpSession session) {
		try {
			String nombreUsuario = userLogin.getNombreUsuario();
			String password = userLogin.getPassword();
			Usuario usuario = GestorUsuarios.getUsuario(nombreUsuario);
			if (GestorUsuarios.autenticarUsuario(usuario, password)) {
				if (!usuario.equals(session.getAttribute("usuario"))) {
					return new ResponseEntity<>(new Error("Su sesión ya se encuentra asociada a otro usuario"),
							HttpStatus.UNPROCESSABLE_ENTITY);
				} else {
					session.setAttribute("usuario", usuario);
					return new ResponseEntity<>(usuario.getDTO(), HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(new Error("Usuario o contraseña inválidos"), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(new Error("No se especificó el nombre de usuario o contraseña"),
					HttpStatus.BAD_REQUEST);
		}
	}

}