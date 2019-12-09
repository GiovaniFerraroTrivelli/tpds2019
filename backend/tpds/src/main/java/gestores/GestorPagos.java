package gestores;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import dominio.Cuota;
import dominio.Cuota.CuotaDTO;
import dominio.Descuento;
import dominio.Pago;
import dominio.Poliza;
import dominio.Recargo;
import enumeradores.EstadoCuota;

public class GestorPagos {
	public static ArrayList<Cuota> getCuotas(Poliza poliza) {
		ArrayList<Cuota> cuotas = new ArrayList<>();
		for (Cuota cuota : poliza.getCuotas()) {
			cuotas.add(cuota);
		}
		Collections.sort(cuotas);
		return cuotas;
	}
	
	public static CuotaDTO calcularDescuentosYRecargos(Cuota c) {
		Date fechaActual = new Date();
		Date mesSiguiente = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		HashSet<Descuento> descuentos = new HashSet<>();
		HashSet<Recargo> recargos = new HashSet<>();
		if (c.getEstadoCuota() != EstadoCuota.PAGA) {
			if (c.getFechaVencimiento().compareTo(mesSiguiente) >= 0) {
				descuentos.add(new Descuento(1, "descuento por pago adelantado", 0.10));
			} else if (c.getFechaVencimiento().compareTo(fechaActual) < 0) {
				recargos.add(new Recargo(1, "recargo por pago atrasado", 0.10));
			} 
		}
		
		CuotaDTO cuota = c.getDTO();
		
		cuota.setDescuentos(descuentos);
		cuota.setRecargos(recargos);
		return cuota;		
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
}
