package gestores;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.DaoCliente;
import dao.DaoGeografico;
import dao.DaoVehiculo;
import dataAccess.HibernateUtil;
import dataTransferObjects.PolizaDTO;
import dominio.Cliente;
import dominio.Cotizacion;
import dominio.Hijo;
import dominio.MedidasSeguridad;
import dominio.Modelo;
import dominio.Poliza;
import dominio.TipoCobertura;
import excepciones.DatoNoEncontradoException;
import restControllers.Error;

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

	
	// TODO: Corregir esto
	public static ArrayList<Error> validarDatos(PolizaDTO p){
		return new ArrayList<>();
	}
	
	public static ArrayList<Error> validarDatos2(PolizaDTO p) {
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

		// Validar ModelP
		if (p.getModelo() == null)
			errores.add(new Error("No se definió el modelo del vehículo"));
		else {
			if (GestorModelos.getModelo(p.getModelo()) == null)
				errores.add(new Error("No existe el modelo de vehículo especificado"));
			else {
				// Validar anio
				Boolean existe = false;
				for (Cotizacion m : GestorModelos.getModelo(p.getModelo()).getAnios()) {
					if (m.getAnio() - p.getAnio() == 0)
						existe = true;
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

		// Validar Medidas de Seguridad
		if (p.getPoseeAlarma() == null || p.getPoseeRastreoVehicular() == null || p.getPoseeTuercasAntirrobo() == null
				|| p.getPoseeAlarma() == null)
			errores.add(new Error("No se especificaron las medidas de seguridad"));

		// Validar hijos
		if (p.getHijos() != null)
			for (Hijo h : p.getHijos()) {
				if (h.getSexo() == null)
					errores.add(new Error("El sexo de al menos un hijo no fue especificado"));
				if (h.getEstadoCivil() == null)
					errores.add(new Error("El estado civil de al menos un hijo no fue especificado"));
				if (h.getFechaNacimiento() == null)
					errores.add(new Error("La fecha de nacimiento de al menos un hijo no fue especificado"));

				LocalDate date = h.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				Integer diff = Period.between(date, java.time.LocalDate.now()).getYears();
				if (diff < 18 || diff > 32) {
					errores.add(new Error(
							"La edad de al menos un hijo no se encuentra dentro de los parámetros esperados"));
				}
			}
		
		// Validar siniestros
		if (p.getSiniestros() == null) errores.add(new Error("No se especificó la cantidad de siniestros"));
		else if(p.getSiniestros() < 0 || p.getSiniestros() > 3)
			errores.add(new Error("La cantidad de siniestros especficada no se encuentra dentro de los parametros esperadas"));
		

		return errores;
	}

	
	// TODO: Modificar (hibernate)
	@SuppressWarnings("deprecation")
	public static void generarPoliza(PolizaDTO p) {

		Poliza poliza = new Poliza();
		poliza.setAnioFabricacion(p.getAnio());
		poliza.setChasis(p.getChasis());
		
		Cliente c = DaoCliente.getCliente(p.getIdCliente());
		poliza.setCliente(c);
		
		MedidasSeguridad m = new MedidasSeguridad();
		m.setPoseeAlarma(p.getPoseeAlarma());
		m.setPoseeRastreoVehicular(p.getPoseeRastreoVehicular());
		m.setPoseeTuercasAntirrobo(p.getPoseeTuercasAntirrobo());
		m.setSeGuardaEnGarage(p.getSeGuardaEnGarage());
		
		poliza.setMedidasSeguridad(m);
		poliza.setSiniestros(p.getSiniestros());
		
		Set<Hijo> hijos = new HashSet<Hijo>(p.getHijos());
		poliza.setHijos(hijos);
		
		poliza.setTipoCobertura(HibernateUtil.getSession().get(TipoCobertura.class, p.getIdCobertura()));
		poliza.setInicioVigencia(new Date(p.getFechaVigencia()));
		
		Modelo mod = DaoVehiculo.getModelo(p.getModelo());
		poliza.setModelo(mod);
		
		poliza.setKmsAnuales(p.getKmAnio());
		
		Session s = HibernateUtil.getSession();
		Transaction t = s.beginTransaction();
		s.save(poliza);
		t.commit();
	}
}
