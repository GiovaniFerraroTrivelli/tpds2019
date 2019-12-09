package restControllers;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestores.GestorUsuarios;
import usuarios.Usuario;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", exposedHeaders="Date")
public class ControladorUsuario {

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody UserLogin userLogin, HttpSession session) {
		try {
			String nombreUsuario = userLogin.getNombreUsuario();
			String password = userLogin.getPassword();
			if (nombreUsuario == null || nombreUsuario.isEmpty())
				throw new NullPointerException();
			if (password == null || password.isEmpty())
				throw new NullPointerException();
			Usuario usuario = GestorUsuarios.getUsuario(nombreUsuario);
			if (usuario == null)
				throw new IllegalArgumentException();
			if (GestorUsuarios.autenticarUsuario(usuario, password)) {
				session.setAttribute("usuario", usuario);
				return new ResponseEntity<>(usuario.getDTO(), HttpStatus.OK);
			}
			return new ResponseEntity<>(new Error("Usuario o contraseña inválidos"), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Error("Usuario o contraseña inválido"), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(new Error("No se especificó el nombre de usuario o contraseña"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<Object> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/checkLogin")
	public ResponseEntity<Object> chechLogin(HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null)
			return new ResponseEntity<>(new Error("No se encuentra autenticado en el sistema"),
					HttpStatus.I_AM_A_TEAPOT);
		else
			return new ResponseEntity<>(usuario.getDTO(), HttpStatus.ACCEPTED);
	}
}