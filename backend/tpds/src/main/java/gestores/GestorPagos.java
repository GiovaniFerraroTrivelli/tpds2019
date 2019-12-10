package gestores;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import dao.DaoPago;
import dao.DaoPoliza;
import dao.DaoUsuario;
import dominio.Cuota;
import dominio.Descuento;
import dominio.Pago;
import dominio.PagoCuota;
import dominio.Poliza;
import dominio.Recargo;
import dominio.Recibo;
import enumeradores.EstadoCuota;
import excepciones.CuotaNoExistenteEnELContextoException;
import usuarios.Usuario;

public class GestorPagos {
	public static ArrayList<Cuota> getCuotas(Poliza poliza) {
		ArrayList<Cuota> cuotas = new ArrayList<>();
		for (Cuota cuota : poliza.getCuotas()) {
			cuotas.add(cuota);
		}
		Collections.sort(cuotas);
		return cuotas;
	}

	public static PagoCuota calcularDescuentosYRecargos(Cuota c) {
		Date fechaActual = new Date();
		Date mesSiguiente = Date.from(ZonedDateTime.now().plusMonths(1).toInstant());
		HashSet<Descuento> descuentos = new HashSet<>();
		HashSet<Recargo> recargos = new HashSet<>();
		PagoCuota pagoCuota = new PagoCuota();

		if (c.getEstadoCuota() != EstadoCuota.PAGA) {
			if (c.getFechaVencimiento().compareTo(mesSiguiente) >= 0) {
				descuentos.add(DaoPago.getDescuentoPagoAdelantado());
			} else if (c.getFechaVencimiento().compareTo(fechaActual) < 0) {
				recargos.add(DaoPago.getRecargoMora());
			}
		}

		pagoCuota.setDescuentos(descuentos);
		pagoCuota.setRecargos(recargos);
		pagoCuota.setCuota(c);

		return pagoCuota;
	}

	public static Integer registrarPago(Pago pago, Poliza poliza, BigDecimal importe, Usuario usuario) {
		BigDecimal importeTotal = GestorPagos.calcularImporteTotal(pago);
		pago.setImporte(importeTotal);
		HashSet<Pago> pagos;
		if (poliza.getPagos() == null)
			pagos = new HashSet<Pago>();
		else
			pagos = new HashSet<>(poliza.getPagos());
		pagos.add(pago);

		for (PagoCuota pagoCuota : pago.getCuotas()) {
			pagoCuota.setPago(pago);
			pagoCuota.getCuota().setEstadoCuota(EstadoCuota.PAGA);
		}
		pago.setFechaHora(new Date());
		pago.setUsuario(usuario);
		pago.setPoliza(poliza);

		DaoPoliza.update(poliza);
		DaoPago.guardarPago(pago);

		Recibo recibo = new Recibo(pago, poliza, poliza.getCliente());
		pago.setRecibo(recibo);
		Integer nroRecibo = DaoPago.guardarRecibo(recibo);
		DaoPago.updatePago(pago);
		GestorPoliza.updatePoliza(poliza);
		return nroRecibo;
	}

	public static Pago getUltimoPago(Poliza poliza) {
		ArrayList<Pago> pagos = new ArrayList<>();
		if (poliza.getPagos() != null)
			pagos.addAll(poliza.getPagos());
		Collections.sort(pagos);
		if (pagos.size() != 0)
			return pagos.get(pagos.size() - 1);
		else
			return null;
	}

	public static Pago ActualizarCuotasAPagar(Pago p, ArrayList<Integer> cuotasAPagar)
			throws CuotaNoExistenteEnELContextoException {
		HashSet<PagoCuota> cuotas = new HashSet<>();
		Pago pago = new Pago(p);
		for (Integer i : cuotasAPagar) {
			Boolean flag = true;
			for (PagoCuota c : p.getCuotas()) {
				if (c.getCuota().getIdCuota().compareTo(i) == 0) {
					cuotas.add(c);
					flag = false;
				}
			}
			if (flag)
				throw new CuotaNoExistenteEnELContextoException();
		}
		pago.setCuotas(cuotas);
		return pago;
	}

	public static BigDecimal calcularImporteTotal(Pago pago) {
		BigDecimal importe = new BigDecimal("0");
		for (PagoCuota p : pago.getCuotas()) {
			importe = importe.add(p.importeFinal());
		}
		return importe;
	}
}
