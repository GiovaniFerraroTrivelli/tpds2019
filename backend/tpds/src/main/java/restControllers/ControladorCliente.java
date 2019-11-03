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
import dominio.CondicionIva;
import dominio.EstadoCivil;
import dominio.Sexo;
import dominio.TipoDocumento;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorCliente {
	@PostMapping("/buscarCliente")
	public ResponseEntity<Object> buscarCliente(@RequestBody ParametrosDeBusqueda parametros){
		Cliente c = new Cliente();
		c.setApellido("Storani");
		c.setNombre("Miguel");
		c.setEmail("miguelignacitorani@gmail.com");
		c.setCuil("20409679049");
		c.setFechaNacimiento(new Date("03/04/1998"));
		c.setNroDocumento(40967904);
		c.setProfesion("Estudiante");
		c.setSexo(Sexo.MASCULINO);
		c.setTipoDocumento(TipoDocumento.DNI);
		c.setEstadoCivil(EstadoCivil.SOLTERO);
		c.setCondicionIva(CondicionIva.ConsumidorFinal);
		c.setIdCliente(42069);
		ArrayList<Cliente> result = new ArrayList<>();
		result.add(c);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}

