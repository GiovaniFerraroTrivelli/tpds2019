package restControllers;

import java.math.BigDecimal;
import java.util.ArrayList;

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
		Pago pago = (Pago) session.getAttribute("pago");
		try {
			pago = GestorPagos.ActualizarCuotasAPagar(pago, p.getIdsCuotasAPagar());
		} catch (CuotaNoExistenteEnELContextoException e) {
			return new ResponseEntity<>(
					new Error("Alguna/s de las cuotas a pagar no se corresponde con una cuota válida"),
					HttpStatus.BAD_REQUEST);
		}

		BigDecimal importeTotal = GestorPagos.calcularImporteTotal(pago);
		ImporteAPagar result = new ImporteAPagar();

		result.setImporteTotal(importeTotal);
		result.setToken(new Token().toString());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public static class CuotasAPagar {
		private Integer idPoliza;
		private ArrayList<Integer> idsCuotasAPagar;

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
	}

	public static class ImporteAPagar {
		private BigDecimal importeTotal;
		private String Token;

		public BigDecimal getImporteTotal() {
			return importeTotal;
		}

		public void setImporteTotal(BigDecimal importeTotal) {
			this.importeTotal = importeTotal;
		}

		public String getToken() {
			return Token;
		}

		public void setToken(String token) {
			Token = token;
		}

	}
}
