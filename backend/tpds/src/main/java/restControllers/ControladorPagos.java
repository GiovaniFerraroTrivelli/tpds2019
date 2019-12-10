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
import enumeradores.Rol;
import excepciones.CuotaNoExistenteEnELContextoException;
import gestores.GestorPagos;
import usuarios.Usuario;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", exposedHeaders = "Date")
public class ControladorPagos {

	@PostMapping("/calcularImporteTotal")
	public ResponseEntity<Object> altaPago(@RequestBody CuotasAPagar p, HttpSession session) {
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
					new Error("No está definido el contexto, debe realizar la consulta a la póliza previamente."), HttpStatus.BAD_REQUEST);
	
		@SuppressWarnings("unchecked")
		Pago pago = (Pago) transaccion.getOrDefault("pago", null);
		if (pago == null)
			return new ResponseEntity<>(
					new Error("Se detecto un error procesando la petición."), HttpStatus.BAD_REQUEST);
		
		Pago pagoCuotasSeleccionadas;
		try {
			pagoCuotasSeleccionadas = GestorPagos.ActualizarCuotasAPagar(pago, p.getIdsCuotasAPagar());
		} catch (CuotaNoExistenteEnELContextoException e) {
			return new ResponseEntity<>(
					new Error("Alguna/s de las cuotas a pagar no se corresponde con una cuota válida"),
					HttpStatus.BAD_REQUEST);
		}

		BigDecimal importeTotal = GestorPagos.calcularImporteTotal(pagoCuotasSeleccionadas);
		ImporteAPagar result = new ImporteAPagar();
		transaccion.put("pagoCuotasSeleccionadas", pagoCuotasSeleccionadas);
		result.setImporteTotal(importeTotal);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public static class CuotasAPagar {
		private Integer idPoliza;
		private ArrayList<Integer> idsCuotasAPagar;
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
}
