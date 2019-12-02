package restControllers;

import java.time.LocalDate;

import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dataTransferObjects.PolizaDTO;
import dataTransferObjects.TipoCoberturaDTO;
import dominio.Cliente;
import dominio.Cuota;
import dominio.Modelo;
import dominio.Poliza;
import dominio.Poliza.ResumenPoliza;
import dominio.TipoCobertura;
import enumeradores.Rol;
import excepciones.DatoNoEncontradoException;
import excepciones.NoExisteClienteException;
import excepciones.NoHayValorException;
import gestores.GestorClientes;
import gestores.GestorModelos;
import gestores.GestorPoliza;
import usuarios.Usuario;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class ControladorPoliza {
	@PostMapping("/altaPoliza/1")
	public ResponseEntity<Object> validarDatos(@RequestBody PolizaDTO p, HttpSession session)
			throws NoHayValorException, DatoNoEncontradoException {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario.getRol() != Rol.ProductorDeSeguros)
			return new ResponseEntity<>(new Error("No tiene permisos para realizar esta operación"),
					HttpStatus.FORBIDDEN);

		RespuestaValidarDatosPoliza result = new RespuestaValidarDatosPoliza();
		result.setCoberturasDisponibles(new ArrayList<TipoCoberturaDTO>());
		ArrayList<Error> errores = GestorPoliza.validarDatos(p);
		result.setErrores(errores);
		if (!errores.isEmpty())
			return new ResponseEntity<>(result, HttpStatus.OK);
		for (TipoCobertura t : GestorPoliza.getCoberturasDisponibles(p))
			result.getCoberturasDisponibles().add(t.getDTO());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/altaPoliza/2")
	public ResponseEntity<Object> resumenPoliza(@RequestBody PolizaDTO p, HttpSession session)
			throws NoHayValorException, DatoNoEncontradoException, NoExisteClienteException {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario.getRol() != Rol.ProductorDeSeguros)
			return new ResponseEntity<>(new Error("No tiene permisos para realizar esta operación"),
					HttpStatus.FORBIDDEN);

		RespuestaResumenPoliza result = new RespuestaResumenPoliza();
		ArrayList<Error> errores = GestorPoliza.validarDatos(p);
		result.setErrores(errores);
		if (!errores.isEmpty())
			return new ResponseEntity<>(result, HttpStatus.OK);

		Poliza poliza = GestorPoliza.generarPoliza(p);
		session.setAttribute("polizaGeneradaSinConfirmar", poliza);
		result.setDatosPoliza(poliza.getResumenPoliza());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/altaPoliza/3")
	public ResponseEntity<Object> altaPoliza(@RequestBody PolizaDTO p, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario.getRol() != Rol.ProductorDeSeguros)
			return new ResponseEntity<>(new Error("No tiene permisos para realizar esta operación"),
					HttpStatus.FORBIDDEN);

		RespuestaResumenPoliza result = new RespuestaResumenPoliza();
		ArrayList<Error> errores = GestorPoliza.validarDatos(p);
		result.setErrores(errores);
		if (!errores.isEmpty())
			return new ResponseEntity<>(result, HttpStatus.OK);

		Poliza polizaPreviaGenerada = (Poliza) session.getAttribute("polizaGeneradaSinConfirmar");

		Boolean resultSave = GestorPoliza.savePoliza(polizaPreviaGenerada);
		return new ResponseEntity<>(resultSave, HttpStatus.OK);
	}

	@PostMapping("/altaPoliza/confirmar")
	public ResponseEntity<Object> confirmarAltaPoliza(@RequestBody PolizaDTO p, HttpSession session){
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario.getRol() != Rol.ProductorDeSeguros)
			return new ResponseEntity<>(new Error("No tiene permisos para realizar esta operación"),
					HttpStatus.FORBIDDEN);
		
		Poliza poliza = (Poliza) session.getAttribute("polizaGeneradaSinConfirmar");
		if (poliza == null) return new ResponseEntity<>(new Error("No existe una póliza a confirmar en el contexto"), HttpStatus.BAD_REQUEST);
		
		Boolean resultSave = GestorPoliza.savePoliza(poliza);
		session.removeAttribute("polizaGeneradaSinConfirmar");
		
		return new ResponseEntity<>(poliza.getResumenPoliza(), HttpStatus.OK);
	}

	@GetMapping("/poliza/{nroPoliza}")
	public ResponseEntity<Object> getPoliza(@PathVariable("nroPoliza") Integer nroPoliza, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null)
			return new ResponseEntity(new Error("No se encuentra autenticado en el sistema"), HttpStatus.FORBIDDEN);

		if (!(usuario.getRol() == Rol.ProductorDeSeguros) && !(usuario.getRol() == Rol.Cobrador))
			return new ResponseEntity<>(new Error("No tiene permisos suficientes para realizar esta operación"),
					HttpStatus.FORBIDDEN);

		Poliza poliza = GestorPoliza.getPoliza(nroPoliza);
		return new ResponseEntity<>(poliza.getResumenPoliza(), HttpStatus.OK);
	}
}
