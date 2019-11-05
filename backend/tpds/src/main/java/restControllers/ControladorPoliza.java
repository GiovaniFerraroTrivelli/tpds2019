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
import gestores.GestorCoberturas;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ControladorPoliza {
	@PostMapping("/verificarDatos")
	public ResponseEntity<Object> verificarDatos(@RequestBody PolizaDTO p) throws NoHayValorException, DatoNoEncontradoException{
		RespuestaVerificarDatosPoliza result = new RespuestaVerificarDatosPoliza();
		result.setCoberturasDisponibles(new ArrayList<TipoCoberturaDTO>());
		result.setErrores(new ArrayList<Error>());
		for (TipoCobertura t : GestorCoberturas.getCoberturas()) result.getCoberturasDisponibles().add(t.getDTO());
		return new ResponseEntity<>(result, HttpStatus.OK);	
	}
	
}
