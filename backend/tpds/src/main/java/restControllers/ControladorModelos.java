package restControllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dataTransferObjects.MarcaDTO;
import dataTransferObjects.ModeloDTO;
import gestores.GestorModelos;
import usuarios.Usuario;
import dominio.Marca;
import dominio.Modelo;
import enumeradores.Rol;


@RestController
@CrossOrigin(origins = "*", allowCredentials="true")
public class ControladorModelos {
	
	
	@GetMapping("/marcas")
	public static ResponseEntity<Object> getMarcas(HttpSession session){
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null)
			return new ResponseEntity<>(new Error("No se encuentra autenticado en el sistema"), HttpStatus.FORBIDDEN);

		if (!(usuario.getRol() == Rol.ProductorDeSeguros) && !(usuario.getRol() == Rol.Cobrador))
			return new ResponseEntity<>(new Error("No tiene permisos suficientes para realizar esta operación"),
					HttpStatus.FORBIDDEN);
		
		try {
			ArrayList<Marca> marcas = GestorModelos.getMarcas();
			ArrayList<MarcaDTO> result = new ArrayList<>();
			for(Marca marca : marcas) result.add(marca.getDTO());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Error("Un error no permite recuperar la información"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/modelos/{idMarca}")
	public ResponseEntity<Object> getModelos(@PathVariable("idMarca") Integer idMarca, HttpSession session){
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null)
			return new ResponseEntity<>(new Error("No se encuentra autenticado en el sistema"), HttpStatus.FORBIDDEN);

		if (!(usuario.getRol() == Rol.ProductorDeSeguros) && !(usuario.getRol() == Rol.Cobrador))
			return new ResponseEntity<>(new Error("No tiene permisos suficientes para realizar esta operación"),
					HttpStatus.FORBIDDEN);
		
		try {
			ArrayList<Modelo> modelos = GestorModelos.getModelosDeMarca(idMarca);
			ArrayList<ModeloDTO> result = new ArrayList<>();
			for(Modelo modelo : modelos) result.add(modelo.getDTO());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Error("No se pudo recuperar los modelos de la marca elegida"), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/anios/{idModelo}")
	public ResponseEntity<Object> getAnios(@PathVariable("idModelo") Integer idModelo, HttpSession session){
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null)
			return new ResponseEntity<>(new Error("No se encuentra autenticado en el sistema"), HttpStatus.FORBIDDEN);

		if (!(usuario.getRol() == Rol.ProductorDeSeguros) && !(usuario.getRol() == Rol.Cobrador))
			return new ResponseEntity<>(new Error("No tiene permisos suficientes para realizar esta operación"),
					HttpStatus.FORBIDDEN);
		
		try {
			ArrayList<Integer> result = GestorModelos.getAnios(idModelo);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Error("No se pudo recuperar los años del modelo elegido"), HttpStatus.NOT_FOUND);
		}
	}

}
