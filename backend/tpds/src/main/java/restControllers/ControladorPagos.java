package restControllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dominio.Pago;
import dominio.Poliza;
import enumeradores.Rol;
import excepciones.CuotaAnteriorNoPagaException;
import excepciones.CuotaNoExistenteEnELContextoException;
import excepciones.CuotaPagaException;
import gestores.GestorPagos;
import usuarios.Usuario;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", exposedHeaders = "Date")
public class ControladorPagos {

	@PostMapping("/calcularImporteTotal")
	public ResponseEntity<Object> altaPago(@RequestBody PostBody p, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null)
			return new ResponseEntity<>(new Error("No se encuentra autenticado en el sistema"), HttpStatus.FORBIDDEN);

		if (!(usuario.getRol() == Rol.Cobrador))
			return new ResponseEntity<>(new Error("No tiene permisos suficientes para realizar esta operación"),
					HttpStatus.FORBIDDEN);

		if (p.getIdsCuotasAPagar().isEmpty())
			return new ResponseEntity<>(new Error("No se seleccionó ninguna cuota a pagar"), HttpStatus.BAD_REQUEST);

		@SuppressWarnings("unchecked")
		Map<String, Object> transaccion = (Map<String, Object>) session.getAttribute(p.getToken());

		if (transaccion == null)
			return new ResponseEntity<>(
					new Error("No está definido el contexto, debe realizar la consulta a la póliza previamente."),
					HttpStatus.BAD_REQUEST);

		Pago pago = (Pago) transaccion.getOrDefault("pago", null);
		if (pago == null)
			return new ResponseEntity<>(new Error("Se detecto un error procesando la petición."),
					HttpStatus.BAD_REQUEST);

		Pago pagoCuotasSeleccionadas;
		try {
			pagoCuotasSeleccionadas = GestorPagos.ActualizarCuotasAPagar(pago, p.getIdsCuotasAPagar());
		} catch (CuotaNoExistenteEnELContextoException e) {
			return new ResponseEntity<>(
					new Error("Alguna/s de las cuotas a pagar no se corresponde con una cuota válida"),
					HttpStatus.BAD_REQUEST);
		} catch (CuotaPagaException e) {
			return new ResponseEntity<>(new Error("No se puede seleccionar una cuota paga"), HttpStatus.BAD_REQUEST);
		} catch (CuotaAnteriorNoPagaException e) {
			return new ResponseEntity<>(new Error("No se puede pagar una cuota sin antes haber pagado las anteriores"),
					HttpStatus.BAD_REQUEST);
		}

		BigDecimal importeTotal = GestorPagos.calcularImporteTotal(pagoCuotasSeleccionadas);
		ImporteAPagar result = new ImporteAPagar();
		transaccion.put("pagoCuotasSeleccionadas", pagoCuotasSeleccionadas);
		result.setImporteTotal(importeTotal);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/registrarPago")
	public ResponseEntity<Object> registrarPago(@RequestBody PostBody post, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null)
			return new ResponseEntity<>(new Error("No se encuentra autenticado en el sistema"), HttpStatus.FORBIDDEN);

		if (!(usuario.getRol() == Rol.Cobrador))
			return new ResponseEntity<>(new Error("No tiene permisos suficientes para realizar esta operación"),
					HttpStatus.FORBIDDEN);

		@SuppressWarnings("unchecked")
		Map<String, Object> transaccion = (Map<String, Object>) session.getAttribute(post.getToken());

		if (transaccion == null)
			return new ResponseEntity<>(
					new Error("No está definido el contexto, debe realizar la consulta a la póliza previamente."),
					HttpStatus.I_AM_A_TEAPOT);

		Pago pago = (Pago) transaccion.getOrDefault("pagoCuotasSeleccionadas", null);
		if (pago == null)
			return new ResponseEntity<>(new Error(
					"Se produjo un error interno y no se pudo recuperar el pago a confirmar, cierre sesión y vuelvalo a intentar"),
					HttpStatus.I_AM_A_TEAPOT);
		Poliza poliza = (Poliza) transaccion.getOrDefault("poliza", null);
		if (poliza == null)
			return new ResponseEntity<>(new Error(
					"Se produjo un error interno y no se pudo recuperar la poliza a pagar, cierre sesión y vuelvalo a intentar"),
					HttpStatus.I_AM_A_TEAPOT);

		BigDecimal importeTotal = GestorPagos.calcularImporteTotal(pago);
		BigDecimal importe = new BigDecimal(post.getMontoAbonado());
		BigDecimal vuelto = importe.subtract(importeTotal);
		if (vuelto.compareTo(new BigDecimal("0")) < 0)
			return new ResponseEntity<>(new Error("No se abonó una cantidad suficiente de dinero"),
					HttpStatus.BAD_REQUEST);

		Integer nroRecibo = GestorPagos.registrarPago(pago, poliza, importe, usuario);
		Vuelto respuesta = new Vuelto();
		respuesta.setNumeroRecibo(nroRecibo);
		respuesta.setPagoConfirmado(nroRecibo != null);
		respuesta.setVuelto(vuelto);

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}

	public static class PostBody {
		private Integer idPoliza;
		private ArrayList<Integer> idsCuotasAPagar;
		private String montoAbonado;
		private String token;

		public ArrayList<Integer> getIdsCuotasAPagar() {
			return idsCuotasAPagar;
		}

		public void setIdsCuotasAPagar(ArrayList<Integer> idsCuotasAPagar) {
			this.idsCuotasAPagar = idsCuotasAPagar;
		}

		public Integer getIdPoliza() {
			return idPoliza;
		}

		public void setIdPoliza(Integer idPoliza) {
			this.idPoliza = idPoliza;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getMontoAbonado() {
			return montoAbonado;
		}

		public void setMontoAbonado(String montoAbonado) {
			this.montoAbonado = montoAbonado;
		}
	}

	public static class ImporteAPagar {
		private BigDecimal importeTotal;

		public BigDecimal getImporteTotal() {
			return importeTotal;
		}

		public void setImporteTotal(BigDecimal importeTotal) {
			this.importeTotal = importeTotal;
		}
	}

	public static class Vuelto {
		private BigDecimal vuelto;
		private Boolean pagoConfirmado;
		private Integer numeroRecibo;

		public BigDecimal getVuelto() {
			return vuelto;
		}

		public void setVuelto(BigDecimal vuelto) {
			this.vuelto = vuelto;
		}

		public Boolean getPagoConfirmado() {
			return pagoConfirmado;
		}

		public void setPagoConfirmado(Boolean pagoConfirmado) {
			this.pagoConfirmado = pagoConfirmado;
		}

		public Integer getNumeroRecibo() {
			return numeroRecibo;
		}

		public void setNumeroRecibo(Integer numeroRecibo) {
			this.numeroRecibo = numeroRecibo;
		}
	}
}
