package gestores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dominio.Cliente;
import dominio.Cuota;
import dominio.Pago;
import dominio.Poliza;

public class GestorPagos {
	public static ArrayList<Cuota> getCuotas(Poliza poliza) {
		ArrayList<Cuota> cuotas = new ArrayList<>();
		for (Cuota cuota : poliza.getCuotas()) {
			cuotas.add(cuota);
		}
		Collections.sort(cuotas);
		return cuotas;
	}
	
	public static Boolean altaPago(ArrayList<Cuota> cuotas) {
		return null;
	}

	public static Pago getUltimoPago(Poliza poliza) {
		ArrayList<Pago> pagos = new ArrayList();
		pagos.addAll(poliza.getPagos());
		Collections.sort(pagos);
		if (pagos.size()!=0) return pagos.get(pagos.size()-1);
		else return null;
	}
}