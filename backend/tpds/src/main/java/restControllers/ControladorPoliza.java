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
import dominio.Cliente.Documento;
import dominio.Cuota;
import dominio.Modelo;
import dominio.NumeroPoliza;
import dominio.Pago;
import dominio.Pago.PagoDTO;
import dominio.Poliza;
import dominio.Poliza.ResumenPoliza;
import dominio.TipoCobertura;
import enumeradores.Rol;
import excepciones.DatoNoEncontradoException;
import excepciones.NoExisteClienteException;
import excepciones.NoHayValorException;
import gestores.GestorClientes;
import gestores.GestorModelos;
import gestores.GestorPagos;
import gestores.GestorPoliza;
import usuarios.Usuario;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class ControladorPoliza {
	@PostMapping("/altaPoliza/1")
	public ResponseEntity<Object> validarDatos(@RequestBody PolizaDTO p, HttpSession session)
			throws NoHayValorException, DatoNoEncontradoException {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario==null) return new ResponseEntity<>(new Error("No está autenticado en el sistema"),
				HttpStatus.FORBIDDEN);
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

	public static class NroPoliza{
		public String numeroPoliza;
		
		public void setNumeroPoliza(String numero) {
			this.numeroPoliza = numero;
		}
	}
	
	public static class RespuestaBuscarPoliza{
		public ArrayList<EntradaListado> polizas;
	}
	
	@PostMapping("/buscarPoliza")
	public ResponseEntity<Object> buscarPolizas(@RequestBody NroPoliza nroPoliza, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null)
			return new ResponseEntity(new Error("No se encuentra autenticado en el sistema"), HttpStatus.FORBIDDEN);

		if (!(usuario.getRol() == Rol.ProductorDeSeguros) && !(usuario.getRol() == Rol.Cobrador))
			return new ResponseEntity<>(new Error("No tiene permisos suficientes para realizar esta operación"),
					HttpStatus.FORBIDDEN);

		ArrayList<Poliza> polizas = GestorPoliza.buscarPoliza(nroPoliza.numeroPoliza);
		
		
		ArrayList<EntradaListado> lista = new ArrayList<>();
		for (Poliza p : polizas) {
			EntradaListado e =new EntradaListado();
			e.setApellidoCliente(p.getCliente().getApellido());
			e.setNombreCliente(p.getCliente().getNombre());
			e.setDocumento(p.getCliente().getDocumento());
			e.setNumeroPoliza(p.getNroPoliza());
			e.setDocumento(p.getCliente().getDocumento());
			e.setIdPoliza(p.getIdPoliza());
			Pago pago = GestorPagos.getUltimoPago(p);
			if (pago != null) e.setUltimoPago(pago.getDTO());
			lista.add(e);
		}
		
		RespuestaBuscarPoliza respuesta = new RespuestaBuscarPoliza();
		respuesta.polizas = lista;
		
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}
	
	private class EntradaListado{
		private Integer idPoliza;
		private String numeroPoliza;
		private String nombreCliente;
		private String apellidoCliente;
		private Documento documento;
		private PagoDTO ultimoPago;
		
		
		public Integer getIdPoliza() {
			return idPoliza;
		}
		public void setIdPoliza(Integer idPoliza) {
			this.idPoliza = idPoliza;
		}
		public String getNumeroPoliza() {
			return numeroPoliza;
		}
		public void setNumeroPoliza(NumeroPoliza numeroPoliza2) {
			this.numeroPoliza = numeroPoliza2.toString();
		}
		public String getNombreCliente() {
			return nombreCliente;
		}
		public void setNombreCliente(String nombreCliente) {
			this.nombreCliente = nombreCliente;
		}
		public String getApellidoCliente() {
			return apellidoCliente;
		}
		public void setApellidoCliente(String apellidoCliente) {
			this.apellidoCliente = apellidoCliente;
		}
		public Documento getDocumento() {
			return documento;
		}
		public void setDocumento(Documento documento) {
			this.documento = documento;
		}
		public PagoDTO getUltimoPago() {
			return ultimoPago;
		}
		public void setUltimoPago(PagoDTO ultimoPago) {
			this.ultimoPago = ultimoPago;
		}
		
	}
	
	@GetMapping("/poliza/{idPoliza}")
	public ResponseEntity<Object> getPoliza(@PathVariable("idPoliza") Integer idPoliza, HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null)
			return new ResponseEntity<>(new Error("No se encuentra autenticado en el sistema"), HttpStatus.FORBIDDEN);

		if (!(usuario.getRol() == Rol.ProductorDeSeguros) && !(usuario.getRol() == Rol.Cobrador))
			return new ResponseEntity<>(new Error("No tiene permisos suficientes para realizar esta operación"),
					HttpStatus.FORBIDDEN);
		Poliza poliza = GestorPoliza.getPoliza(idPoliza);
		
		if (poliza == null) return new ResponseEntity<>(new Error("La poliza solicitada no existe"),
				HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(poliza.getResumenPoliza(),
				HttpStatus.OK);
	}
}


