package restControllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.persistence.NoResultException;

import org.hibernate.PersistentObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DaoCliente;
import dataTransferObjects.ClienteDTO;
import dataTransferObjects.ParametrosDeBusqueda;
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
		try {
			ArrayList<Cliente> listaClientes = DaoCliente.buscarCliente(parametros);
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
			System.out.println("iuigu");
			return new ResponseEntity<>(
					new Error("No se puede crear un cliente con datos duplicados. Error: ".concat(e.getMessage())),
					HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
