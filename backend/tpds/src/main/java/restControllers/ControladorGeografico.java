package restControllers;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dataTransferObjects.LocalidadDTO;
import dataTransferObjects.PaisDTO;
import dataTransferObjects.ProvinciaDTO;
import dominio.Localidad;
import dominio.Pais;
import dominio.Provincia;
import excepciones.DatoNoEncontradoException;
import gestores.GestorGeografico;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorGeografico {

	@GetMapping("/provincias/{idPais}")
	public ResponseEntity<Object> getProvincias(@PathVariable("idPais") Integer idPais) {
		try {
			ArrayList<Provincia> provincias = GestorGeografico.getProvinciasDePais(idPais);
			ArrayList<ProvinciaDTO> result = new ArrayList<ProvinciaDTO>();
			for (Provincia provincia : provincias)
				result.add(provincia.getDTO());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Error("País no encontrado"), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/provincia/{idProvincia}")
	public ResponseEntity<Object> getProvincia(@PathVariable("idProvincia") Integer idProvincia) {
		try {
			Provincia provincia = GestorGeografico.getProvincia(idProvincia);
			return new ResponseEntity<>(provincia.getDTO(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Error("Provincia no encontrada"), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/localidades/{idProvincia}")
	public ResponseEntity<Object> getLocalidadesDeProvincia(@PathVariable("idProvincia") Integer idProvincia)
			throws DatoNoEncontradoException {
		try {
			ArrayList<Localidad> localidades = GestorGeografico.getLocalidadesDeProvincia(idProvincia);
			ArrayList<LocalidadDTO> result = new ArrayList<LocalidadDTO>();
			for (Localidad localidad : localidades)
				result.add(localidad.getDTO());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Error("Provincia no encontrada"), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/pais/{idPais}")
	public ResponseEntity<Object> getPais(@PathVariable("idPais") Integer idPais) {
		try {
			PaisDTO pais = GestorGeografico.getPais(idPais).getDTO();
			return new ResponseEntity<>(pais, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new Error("País no encontrado"), HttpStatus.NOT_FOUND);
		}
	}

}
