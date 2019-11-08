package restControllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dataTransferObjects.PolizaDTO;
import dataTransferObjects.TipoCoberturaDTO;
import dominio.TipoCobertura;
import excepciones.DatoNoEncontradoException;
import excepciones.NoHayValorException;
import gestores.GestorPoliza;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorPoliza {
	@PostMapping("/altaPoliza/validarDatos")
	public ResponseEntity<Object> validarDatos(@RequestBody PolizaDTO p)
			throws NoHayValorException, DatoNoEncontradoException {
		
		RespuestaValidarDatosPoliza result = new RespuestaValidarDatosPoliza();
		result.setCoberturasDisponibles(new ArrayList<TipoCoberturaDTO>());
		ArrayList<Error> errores = GestorPoliza.validarDatos(p);
		result.setErrores(errores);
		if (!errores.isEmpty()) return new ResponseEntity<>(result, HttpStatus.OK);
		for (TipoCobertura t : GestorPoliza.getCoberturasDisponibles(p))
			result.getCoberturasDisponibles().add(t.getDTO());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/altaPoliza/2")
	public ResponseEntity<Object> altaPoliza(@RequestBody PolizaDTO p) {
		Boolean result = GestorPoliza.generarPoliza(p);
		return new ResponseEntity<>(true, HttpStatus.OK);
		
	}

}
