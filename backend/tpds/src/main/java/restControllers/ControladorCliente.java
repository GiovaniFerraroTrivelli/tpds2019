package restControllers;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dataTransferObjects.ClienteDTO;
import dataTransferObjects.ParametrosDeBusqueda;
import dominio.Cliente;
import dominio.Direccion;
import dominio.Documento;
import enumeradores.CondicionIva;
import enumeradores.EstadoCivil;
import enumeradores.Sexo;
import enumeradores.TipoDocumento;
import excepciones.DatoNoEncontradoException;
import excepciones.NoExisteClienteException;
import gestores.GestorClientes;
import gestores.GestorGeografico;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorCliente {
	@PostMapping("/buscarCliente")
	public ResponseEntity<Object> buscarCliente(@RequestBody ParametrosDeBusqueda parametros) throws NoExisteClienteException{
		Cliente c = GestorClientes.getCliente(2);
		c.setIdCliente(999999999);
		System.out.println(c.getDireccion().getLocalidad().getNombre());
		ArrayList<ClienteDTO> result = new ArrayList<>();
		result.add(c.getDTO());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}

