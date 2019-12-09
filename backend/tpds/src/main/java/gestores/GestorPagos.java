package gestores;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import dominio.Cuota;
import dominio.Descuento;
import dominio.Pago;
import dominio.PagoCuota;
import dominio.Poliza;
import dominio.Recargo;
import enumeradores.EstadoCuota;
import excepciones.CuotaNoExistenteEnELContextoException;

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
				descuentos.add(new Descuento(1, "descuento por pago adelantado", 0.10));
			} else if (c.getFechaVencimiento().compareTo(fechaActual) < 0) {
				recargos.add(new Recargo(1, "recargo por pago atrasado", 0.10));
			}
		}
		
		pagoCuota.setDescuentos(descuentos);
		pagoCuota.setRecargos(recargos);
		pagoCuota.setCuota(c);
		
		return pagoCuota;		
	}

	public static Boolean altaPago(ArrayList<Cuota> cuotas) {
		
		return null;
	}

	public static Pago getUltimoPago(Poliza poliza) {
		ArrayList<Pago> pagos = new ArrayList<>();
		pagos.addAll(poliza.getPagos());
		Collections.sort(pagos);
		if (pagos.size()!=0) return pagos.get(pagos.size()-1);
		else return null;
	}
	
	public static Pago ActualizarCuotasAPagar(Pago p, ArrayList<Integer> cuotasAPagar) throws CuotaNoExistenteEnELContextoException {
		ArrayList<PagoCuota> cuotas = new ArrayList<>();
		Pago pago = new Pago(p);
		for(Integer i : cuotasAPagar) {
			Boolean flag = false;
			for(PagoCuota c : p.getCuotas()) {
				if (c.getCuota().getIdCuota() == i) {
					cuotas.add(c);
					flag = true;
				}
			}
			if (!flag) throw new CuotaNoExistenteEnELContextoException();
		}
		pago.setCuotas(new HashSet<PagoCuota>(cuotas));
		return pago;
	}

	public static BigDecimal calcularImporteTotal(Pago pago) {
		BigDecimal importe = new BigDecimal("0");
		for(PagoCuota p : pago.getCuotas()) {
			importe = importe.add(p.importeFinal());
		}
		return importe;
	}
}
