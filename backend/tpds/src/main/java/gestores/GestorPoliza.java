package gestores;

import java.time.Year;
import java.util.ArrayList;

import dao.DaoCliente;
import dao.DaoGeografico;
import dao.DaoVehiculo;
import dataTransferObjects.PolizaDTO;
import dominio.Cotizacion;
import dominio.Modelo;
import dominio.TipoCobertura;
import excepciones.DatoNoEncontradoException;

public class GestorPoliza {
	public static ArrayList<TipoCobertura> getCoberturasDisponibles(PolizaDTO p) {
		ArrayList<TipoCobertura> coberturasDisponibles = new ArrayList<>();
		try {
			if (p.getAnio() <= Year.now().getValue() - 10) {
				coberturasDisponibles.add(GestorCoberturas.getCoberturaContraTerceros());
			} else {
				coberturasDisponibles.addAll(GestorCoberturas.getCoberturas());
			}
		} catch (DatoNoEncontradoException e) {
			e.printStackTrace();
		}
		return coberturasDisponibles;
	}

	public static ArrayList<Error> validarDatos(PolizaDTO p) {
		ArrayList<Error> errores = new ArrayList<>();

		
		// Validar idUsuario
		if (p.getIdCliente() == null)
			errores.add(new Error("Falta definir el cliente"));
		else if (DaoCliente.getCliente(p.getIdCliente()) == null)
			errores.add(new Error("No existe el cliente especificado"));

		
		// Validar Domicilio de riesgo

		if (p.getLocalidad() == null)
			errores.add(new Error("No se definió una localidad de riesgo"));
		else if (DaoGeografico.getLocalidad(p.getLocalidad()) == null)
			errores.add(new Error("No existe el domicilio de riesgo especificado"));
		
		
		// Validar Modelo
		
		if (p.getModelo() == null)
			errores.add(new Error("No se definió el modelo del vehículo"));
		else {
			if (GestorModelos.getModelo(p.getModelo()) == null)
				errores.add(new Error("No existe el modelo de vehículo especificado"));
			else {
				// Validar anio
				
				Boolean existe = false;
				for (Cotizacion m : GestorModelos.getModelo(p.getModelo()).getAnios()) {
					if (m.getAnio() == p.getAnio()) existe = true;
				}
				if (!existe)
					errores.add(new Error("El modelo seleccionado no se fabricó en el año indicado"));
			}
		}
		
		// Validar existencia de anio de fabricación
		
		if (p.getAnio() == null)
			errores.add(new Error("No de definió un año de fabricación"));
		
		// Validar Motor
		
		if (p.getMotor() == null)
			errores.add(new Error("No se ingresó el número de motor"));
		
		return errores;
	}
}
