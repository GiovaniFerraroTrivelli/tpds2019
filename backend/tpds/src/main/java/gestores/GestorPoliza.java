package gestores;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.*;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dao.DaoCliente;
import dao.DaoGeografico;
import dao.DaoPoliza;
import dao.DaoVehiculo;
import dataAccess.HibernateUtil;
import dominio.Poliza.PolizaDTO;
import dominio.Cliente;
import dominio.Cotizacion;
import dominio.Cuota;
import dominio.Hijo;
import dominio.MedidasSeguridad;
import dominio.Modelo;
import dominio.NumeroCliente;
import dominio.NumeroPoliza;
import dominio.Poliza;
import dominio.TipoCobertura;
import enumeradores.EstadoPoliza;
import enumeradores.FormaPago;
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
	public static ArrayList<Error> validarDatos2(PolizaDTO p) {
		return new ArrayList<>();
	}

	public static ArrayList<Error> validarDatos(PolizaDTO p) {
		ArrayList<Error> errores = new ArrayList<>();

		// Validar idUsuario
		if (p.getNroCliente() == null)
			errores.add(new Error("Falta definir el cliente"));
		else if (p.getNroCliente().length() != 10 || DaoCliente.buscarCliente(new NumeroCliente(p.getNroCliente())) == null)
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
		else if(GestorPoliza.existePolizaConMotor(p.getMotor()))
			errores.add(new Error("Ya existe una poliza con el número de motor indicado"));
		
		if(p.getChasis() == null)
			errores.add(new Error("No se ingresó el número de chasis"));
		else if (existePolizaConChasis(p.getChasis()))
			errores.add(new Error("Ya existe una póliza con el número de chasis indicado"));
		
		if (p.getPatente()!=null && existePolizaConPatente(p.getPatente()))
			errores.add(new Error("Ya existe una póliza con el dominio ingresado"));
			

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
		if (p.getSiniestros() == null)
			errores.add(new Error("No se especificó la cantidad de siniestros"));
		else if (p.getSiniestros() < 0 || p.getSiniestros() > 3)
			errores.add(new Error(
					"La cantidad de siniestros especficada no se encuentra dentro de los parametros esperadas"));

		return errores;
	}
	private static boolean existePolizaConPatente(String patente) {
		Session session = HibernateUtil.getSession();
		String hql = "FROM Poliza WHERE dominio=\'" + patente + "\'";
		Query<Poliza> query = session.createQuery(hql);
		return !query.getResultList().isEmpty();
	}

	private static boolean existePolizaConMotor(String motor) {
		Session session = HibernateUtil.getSession();
		String hql = "FROM Poliza WHERE motor=\'" + motor + "\'";
		Query<Poliza> query = session.createQuery(hql);
		return !query.getResultList().isEmpty();
		
	}

	private static boolean existePolizaConChasis(String chasis) {
		Session session = HibernateUtil.getSession();
		String hql = "FROM Poliza WHERE chasis=\'" + chasis + "\'";
		Query<Poliza> query = session.createQuery(hql);
		return !query.getResultList().isEmpty();
		
	}
	
	// TODO: Modificar (hibernate)
	public static Poliza generarPoliza(PolizaDTO p) {

		Poliza poliza = new Poliza();
		poliza.setAnioFabricacion(p.getAnio());
		poliza.setChasis(p.getChasis());
		poliza.setMotor(p.getMotor());

		Cliente c = DaoCliente.buscarCliente(new NumeroCliente(p.getNroCliente()));
		poliza.setCliente(c);

		MedidasSeguridad m = new MedidasSeguridad();
		m.setPoseeAlarma(p.getPoseeAlarma());
		m.setPoseeRastreoVehicular(p.getPoseeRastreoVehicular());
		m.setPoseeTuercasAntirrobo(p.getPoseeTuercasAntirrobo());
		m.setSeGuardaEnGarage(p.getSeGuardaEnGarage());

		poliza.setMedidasSeguridad(m);
		poliza.setSiniestros(p.getSiniestros());

		for (Hijo h : p.getHijos()) {
			h.setPoliza(poliza);
		}

		Set<Hijo> hijos = new HashSet<Hijo>(p.getHijos());
		poliza.setHijos(hijos);

		poliza.setTipoCobertura(HibernateUtil.getSession().get(TipoCobertura.class, p.getIdCobertura()));

		LocalDate inicioVigencia = LocalDate.parse(p.getFechaVigencia());
		poliza.setInicioVigencia(java.sql.Date.valueOf(inicioVigencia));
		poliza.setFinVigencia(java.sql.Date.valueOf(inicioVigencia.plusMonths(6)));

		Modelo mod = DaoVehiculo.getModelo(p.getModelo());
		poliza.setModelo(mod);
		poliza.setAnioFabricacion(p.getAnio());

		poliza.setKmsAnuales(p.getKmAnio());
		poliza.setDominio(p.getPatente());
		poliza.setEstadoPoliza(EstadoPoliza.GENERADA);
		poliza.setPremio(new BigDecimal(100));
		poliza.setDescuentos(new BigDecimal(100.23));
		poliza.setPrima(new BigDecimal(100.54));
		poliza.setDerechoEmision(new BigDecimal(100.65));

		Set<Cuota> cuotas = new HashSet<Cuota>();
		if (p.getModalidadPago().equals("MENSUAL")) {
			for (int i = 0; i < 6; i++) {
				Cuota cuota = new Cuota();
				cuota.setFechaVencimiento(java.sql.Date.valueOf(inicioVigencia.minusDays(1).plusMonths(i)));
				// TODO: Cambiar el importe
//				cuota.setImporte(Money.of(100, "ARS"));
				cuota.setImporte(new BigDecimal(100.65));
				cuota.setPoliza(poliza);

				cuotas.add(cuota);
			}
		} else {
			Cuota cuota = new Cuota();
			cuota.setFechaVencimiento(java.sql.Date.valueOf(inicioVigencia.minusDays(1)));
//			cuota.setImporte(Money.of(100, "ARS"));
			cuota.setImporte(new BigDecimal(100));
			cuota.setPoliza(poliza);
			cuotas.add(cuota);
		}

		poliza.setCuotas(cuotas);
		poliza.setFormaPago(FormaPago.valueOf(p.getModalidadPago()));
		poliza.setTipoCobertura(GestorCoberturas.getCobertura(p.getIdCobertura()));
		return poliza;

	}

	public static Boolean savePoliza(Poliza poliza) {
		try {
			Session s = HibernateUtil.getSession();
			Transaction t = s.beginTransaction();
			s.save(poliza);
			Set<Cuota> cuotas = poliza.getCuotas();
			// TODO: Revisar esto
			for (Cuota cuota : cuotas) {
				s.save(cuota);
			}
			t.commit();
			s.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static Poliza getPoliza(Integer nroPoliza) {
		return DaoPoliza.getPoliza(nroPoliza);
	}

	public static String getSumaAsegurada(Poliza p) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ArrayList<Poliza> buscarPoliza(String numeroPoliza) {
		return DaoPoliza.buscarPoliza(numeroPoliza);
	}
	
	public static Boolean altaPoliza(Poliza p) {
		
		
		Boolean result = GestorPoliza.savePoliza(p);
		p.setNroPoliza(new NumeroPoliza(1,p.getIdPoliza(),1));
		DaoPoliza.update(p);
		return result;
	}

}
