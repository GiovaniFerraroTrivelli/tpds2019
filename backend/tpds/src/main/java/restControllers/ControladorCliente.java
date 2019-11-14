package restControllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DaoCliente;
import dataTransferObjects.ClienteDTO;
import dataTransferObjects.ParametrosDeBusqueda;
import dominio.Cliente;
import excepciones.NoExisteClienteException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorCliente {
	@PostMapping("/buscarCliente")
	public ResponseEntity<Object> buscarCliente(@RequestBody ParametrosDeBusqueda parametros)
			throws NoExisteClienteException {
		ArrayList<Cliente> listaClientes = DaoCliente.buscarCliente(parametros);
		ArrayList<ClienteDTO> result = new ArrayList<>();
		for (Cliente c : listaClientes) {
			result.add(c.getDTO());
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
