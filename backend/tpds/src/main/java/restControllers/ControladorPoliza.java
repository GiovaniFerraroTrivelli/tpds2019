package restControllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dominio.Poliza.PolizaDTO;
import dominio.Poliza.ResumenPoliza;
import dataTransferObjects.TipoCoberturaDTO;
import dominio.Cliente.Documento;
import dominio.Cuota.CuotaDTO;
import dominio.Cuota;
import dominio.NumeroPoliza;
import dominio.Pago;
import dominio.Pago.PagoDTO;
import dominio.PagoCuota;
import dominio.Poliza;
import dominio.TipoCobertura;
import enumeradores.Rol;
import excepciones.DatoNoEncontradoException;
import excepciones.NoExisteClienteException;
import excepciones.NoHayValorException;
import gestores.GestorPagos;
import gestores.GestorPoliza;
import usuarios.Usuario;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", exposedHeaders = "Date")
public class ControladorPoliza {
	@PostMapping("/altaPoliza/1")
	public ResponseEntity<Object> validarDatos(@RequestBody PolizaDTO p, HttpSession session)
			throws NoHayValorException, DatoNoEncontradoException {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null)
			return new ResponseEntity<>(new Error("No está autenticado en el sistema"), HttpStatus.FORBIDDEN);
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
		Token token = new Token();
		poliza.setUsuario(usuario);
		session.setAttribute(token.token, poliza);
		result.setDatosPoliza(poliza.getResumenPoliza());
		result.setToken(token.token);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}



	@PostMapping("/altaPoliza/confirmar")
	public ResponseEntity<Object> confirmarAltaPoliza(@RequestBody Token token, HttpSession session) {

		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario.getRol() != Rol.ProductorDeSeguros)
			return new ResponseEntity<>(new Error("No tiene permisos para realizar esta operación"),
					HttpStatus.FORBIDDEN);

		Poliza poliza = (Poliza) session.getAttribute(token.token);
		if (poliza == null)
			return new ResponseEntity<>(new Error("No existe una póliza a confirmar en el contexto"),
					HttpStatus.BAD_REQUEST);
		Boolean result = GestorPoliza.altaPoliza(poliza);
		session.removeAttribute(token.token);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public static class NroPoliza {
		public String numeroPoliza;

		public void setNumeroPoliza(String numero) {
			this.numeroPoliza = numero;
		}
	}

	public static class RespuestaBuscarPoliza {
		public ArrayList<EntradaListado> polizas;
	}

	@PostMapping("/buscarPoliza")
	public ResponseEntity<Object> buscarPolizas(@RequestBody NroPoliza nroPoliza, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null)
			return new ResponseEntity<>(new Error("No se encuentra autenticado en el sistema"), HttpStatus.FORBIDDEN);

		if (!(usuario.getRol() == Rol.ProductorDeSeguros) && !(usuario.getRol() == Rol.Cobrador))
			return new ResponseEntity<>(new Error("No tiene permisos suficientes para realizar esta operación"),
					HttpStatus.FORBIDDEN);
		if (nroPoliza.numeroPoliza.length() != 13)
			return new ResponseEntity<>(
					new Error("No se indicó un número de póliza válido (debe tener exactamente 13 dígitos"),
					HttpStatus.BAD_REQUEST);

		ArrayList<Poliza> polizas = GestorPoliza.buscarPoliza(nroPoliza.numeroPoliza);

		ArrayList<EntradaListado> lista = new ArrayList<>();
		for (Poliza p : polizas) {
			EntradaListado e = new EntradaListado();
			e.setApellidoCliente(p.getCliente().getApellido());
			e.setNombreCliente(p.getCliente().getNombre());
			e.setNumeroCliente(p.getCliente().nroCliente());
			e.setDocumento(p.getCliente().getDocumento());
			e.setNumeroPoliza(p.getNroPoliza());
			e.setDocumento(p.getCliente().getDocumento());
			e.setIdPoliza(p.getIdPoliza());
			Pago pago = GestorPagos.getUltimoPago(p);
			if (pago != null)
				e.setUltimoPago(pago.getDTO());
			lista.add(e);
		}

		RespuestaBuscarPoliza respuesta = new RespuestaBuscarPoliza();
		respuesta.polizas = lista;

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}

	@SuppressWarnings("unused")
	private class EntradaListado {
		private Integer idPoliza;
		private String numeroPoliza;
		private String nombreCliente;
		private String apellidoCliente;
		private String numeroCliente;
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

		public String getNumeroCliente() {
			return numeroCliente;
		}

		public void setNumeroCliente(String numeroCliente) {
			this.numeroCliente = numeroCliente;
		}

	}

	@GetMapping("/poliza/{idPoliza}")
	public ResponseEntity<Object> getPoliza(@PathVariable("idPoliza") Integer idPoliza, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null)
			return new ResponseEntity<>(new Error("No se encuentra autenticado en el sistema"), HttpStatus.FORBIDDEN);

		if (!(usuario.getRol() == Rol.ProductorDeSeguros) && !(usuario.getRol() == Rol.Cobrador))
			return new ResponseEntity<>(new Error("No tiene permisos suficientes para realizar esta operación"),
					HttpStatus.FORBIDDEN);

		Poliza poliza = GestorPoliza.getPoliza(idPoliza);
		if (poliza == null)
			return new ResponseEntity<>(new Error("La poliza solicitada no existe"), HttpStatus.NOT_FOUND);
		String token = new Token().toString();
		Map<String, Object> transaccion = new HashMap<String, Object>();
		Pago pago = new Pago();
		
		ResumenPoliza resumen = poliza.getResumenPoliza();
		resumen.setToken(token);
		ArrayList<CuotaDTO> cuotasDTO = new ArrayList<>();
		
		HashSet<PagoCuota> pagoCuota = new HashSet<>();
		for (Cuota cuota : poliza.getCuotas()) {
			PagoCuota pC = GestorPagos.calcularDescuentosYRecargos(cuota);
			pagoCuota.add(pC);
			cuotasDTO.add(pC.getDTO());
		}
		Collections.sort(cuotasDTO);
		resumen.setCuotas(cuotasDTO);
		pago.setCuotas(pagoCuota);
		
		transaccion.put("poliza", poliza);
		transaccion.put("pago", pago);
		session.setAttribute(token, transaccion);
		return new ResponseEntity<>(resumen, HttpStatus.OK);
	}
}
