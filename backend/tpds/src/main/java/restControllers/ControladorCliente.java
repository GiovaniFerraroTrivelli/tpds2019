package restControllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.hibernate.PersistentObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DaoCliente;
import dataTransferObjects.ClienteDTO;
import dataTransferObjects.ParametrosDeBusqueda;
import dataTransferObjects.ParametrosDeConsulta;
import dataTransferObjects.altaClienteDTO;
import dominio.Cliente;
import dominio.Direccion;
import excepciones.DatoNoEncontradoException;
import excepciones.NoExisteClienteException;
import gestores.GestorClientes;
import gestores.GestorGeografico;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorCliente {
		@PostMapping("/buscarCliente")
		public ResponseEntity<Object> buscarCliente(@RequestBody ParametrosDeBusqueda parametros) {
			if (!parametros.paginaValida()) {
				return new ResponseEntity<>(new Error("Parámetros de paginación inválidos"), HttpStatus.OK);
			}
	
			if (parametros.nulo()) {
				return new ResponseEntity<>(new Error("Ningún campo de búsqueda fue completado"), HttpStatus.OK);
			}
	
			try {
				ArrayList<Cliente> listaClientes = GestorClientes.buscarClientes(parametros);
				ArrayList<ClienteDTO> result = new ArrayList<>();
				for (Cliente c : listaClientes) {
				result.add(c.getDTO());
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (NoResultException e) {
			return new ResponseEntity<>(new Error("No se encontró un cliente con los campos especificados"),
					HttpStatus.OK);
		}
	}

	@PostMapping("/consultarCliente")
	public ResponseEntity<Object> consultarCliente(@RequestBody ParametrosDeConsulta parametros) {
		if (!parametros.paginaValida()) {
			return new ResponseEntity<>(new Error("Parámetros de paginación inválidos"), HttpStatus.OK);
		}
		
		if (parametros.nulo()) {
			return new ResponseEntity<>(new Error("Ningún campo de búsqueda fue completado"), HttpStatus.OK);
		}

		try {
			ArrayList<Cliente> listaClientes = GestorClientes.consultarClientes(parametros);
			ArrayList<ClienteDTO> result = new ArrayList<>();
			for (Cliente c : listaClientes) {
				result.add(c.getDTO());
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (NoResultException e) {
			return new ResponseEntity<>(new Error("No se encontró un cliente con los campos especificados"),
					HttpStatus.OK);
		}
	}

	@PostMapping("/altaCliente")
	public ResponseEntity<Object> altaCliente(@RequestBody altaClienteDTO clienteDTO)
			throws SQLIntegrityConstraintViolationException {
		try {
			GestorClientes.altaCliente(clienteDTO);
		} catch (DatoNoEncontradoException e) {
			return new ResponseEntity<>(new Error("No existe la localidad indicada"), HttpStatus.PRECONDITION_FAILED);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(
					new Error("Error, no se han especificado todos los parámetros necesarios para generar el cliente"),
					HttpStatus.BAD_REQUEST);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(
					new Error("No se puede crear un cliente con datos duplicados. Error: ".concat(e.getMessage())),
					HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/probandoCookie")
	public ResponseEntity<Object> probandoCookie(HttpSession session){
		ArrayList<String> lista;
		if (session.isNew()) {
			lista = new ArrayList<String>();
			lista.add("Hola, esta es la sesion " + session.getId() + ", es una sesion nueva");
			session.setAttribute("Mensajes", lista);
		} else {
			lista = (ArrayList<String>) session.getAttribute("Mensajes");
			lista.add("Hola, esta es la sesion " + session.getId() + ", es una sesion reutilizada");
		}
		
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
}