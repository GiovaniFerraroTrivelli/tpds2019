package gestores;

import java.util.ArrayList;
import java.util.Collections;

import dominio.Cuota;
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
}
