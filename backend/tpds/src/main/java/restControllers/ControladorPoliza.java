package restControllers;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dataTransferObjects.CuotaDTO;
import dataTransferObjects.PolizaDTO;
import dataTransferObjects.TipoCoberturaDTO;
import dominio.Cliente;
import dominio.Cuota;
import dominio.Modelo;
import dominio.Poliza;
import dominio.TipoCobertura;
import excepciones.DatoNoEncontradoException;
import excepciones.NoExisteClienteException;
import excepciones.NoHayValorException;
import gestores.GestorClientes;
import gestores.GestorModelos;
import gestores.GestorPoliza;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorPoliza {
	@PostMapping("/altaPoliza/1")
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
	public ResponseEntity<Object> resumenPoliza(@RequestBody PolizaDTO p)
			throws NoHayValorException, DatoNoEncontradoException, NoExisteClienteException {
		
		RespuestaResumenPoliza result = new RespuestaResumenPoliza();
		ArrayList<Error> errores = GestorPoliza.validarDatos(p);
		result.setErrores(errores);
		if (!errores.isEmpty()) return new ResponseEntity<>(result, HttpStatus.OK);
		
		ResumenPolizaDTO resumen = new ResumenPolizaDTO();
		Cliente c = GestorClientes.getCliente(p.getIdCliente());
		resumen.setNombreTitular(c.getNombre());
		resumen.setApellidoTitular(c.getApellido());
		Modelo m = GestorModelos.getModelo(p.getModelo());
		resumen.setModelo(m.getNombre());
		resumen.setMarca(m.getMarca().getNombre());
		resumen.setMotor(p.getMotor());
		resumen.setChasis(p.getChasis());
		resumen.setPatente(p.getPatente());
		Poliza poliza = GestorPoliza.generarPoliza(p);
		resumen.setInicioVigencia(poliza.getInicioVigencia());
		resumen.setFinVigencia(poliza.getFinVigencia());
		resumen.setSumaAsegurada("32332.23");
		resumen.setPremio("4353.34");
		resumen.setDescuentos("234.23");
		LocalDate inicioVigencia = LocalDate.parse( poliza.getInicioVigencia().toString());
		resumen.setUltimoDiaPago(java.sql.Date.valueOf(inicioVigencia.minusDays(1)));
		resumen.setMontoTotal("42534.43");
		ArrayList<CuotaDTO> cuotas = new ArrayList<>();
		for (Cuota cuota : poliza.getCuotas()) {
			cuotas.add(cuota.getDTO());
		}
		resumen.setCuotas(cuotas);
		
		
		result.setDatosPoliza(resumen);
		
		
		
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/altaPoliza/3")
	public ResponseEntity<Object> altaPoliza(@RequestBody PolizaDTO p) {
		Poliza result = GestorPoliza.generarPoliza(p);
		Boolean resultSave = GestorPoliza.savePoliza(result);
		return new ResponseEntity<>(resultSave, HttpStatus.OK);
	}

}
