package restControllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dataTransferObjects.AltaClienteDTO;
import dataTransferObjects.ClienteDTO;
import dataTransferObjects.ParametrosDeBusqueda;
import dataTransferObjects.ParametrosDeConsulta;
import dominio.Cliente;
import enumeradores.Rol;
import excepciones.DatoNoEncontradoException;
import gestores.GestorClientes;
import usuarios.Usuario;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class ControladorCliente {
	@PostMapping("/buscarCliente")
	public ResponseEntity<Object> buscarCliente(@RequestBody ParametrosDeBusqueda parametros, HttpSession session) {
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null) return new ResponseEntity<>(new Error("No está autenticado en el sistema"),
				HttpStatus.FORBIDDEN);
		if (usuario.getRol() != Rol.ProductorDeSeguros)
			return new ResponseEntity<>(new Error("No tiene permisos para realizar esta operación"),
					HttpStatus.FORBIDDEN);
		if (!parametros.nroClienteValido()) {
			return new ResponseEntity<>(new Error("Número de cliente inválido"), HttpStatus.OK);
		}

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
			class Respuesta{
				public Integer pagina;
				public Integer resultadosPorPagina;
				public Integer cantidadPaginas;
				public ArrayList<ClienteDTO> clientes;
			}
			
			Respuesta respuesta = new Respuesta();
			respuesta.pagina = parametros.getNumeroPagina();
			respuesta.resultadosPorPagina = parametros.getResultadosPorPagina();
			respuesta.clientes = result;
			respuesta.cantidadPaginas = 1;
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} catch (NoResultException e) {
			return new ResponseEntity<>(new Error("No se encontró un cliente con los campos especificados"),
					HttpStatus.OK);
		}
	}

	@PostMapping("/consultarCliente")
	public ResponseEntity<Object> consultarCliente(@RequestBody ParametrosDeConsulta parametros) {
		if (!parametros.nroClienteValido()) {
			return new ResponseEntity<>(new Error("Número de cliente inválido"), HttpStatus.OK);
		}

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
	public ResponseEntity<Object> altaCliente(@RequestBody AltaClienteDTO clienteDTO)
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
}
