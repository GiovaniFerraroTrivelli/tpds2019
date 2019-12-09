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

import org.hibernate.Session;
import org.hibernate.Transaction;
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
import enumeradores.EstadoCuota;
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

	public static ArrayList<Error> validarDatos(PolizaDTO p) {
		ArrayList<Error> errores = new ArrayList<>();

		// Validar numero de cliente
		errores.addAll(validarNumeroDeCliente(p.getNroCliente()));

		// Validar Domicilio de riesgo
		errores.addAll(validarDomicilioDeRiesgo(p.getLocalidad()));

		// Validar Modelo
		errores.addAll(validarModeloYAnioDeFabricacion(p.getModelo(), p.getAnio()));

		// Validar Motor
		errores.addAll(validarMotor(p.getMotor()));

		// Validar Chasis
		errores.addAll(validarChasis(p.getChasis()));

		// Validar Patente
		errores.addAll(validarPatente(p.getPatente()));

		// Validar Medidas de Seguridad
		if (p.getPoseeAlarma() == null || p.getPoseeRastreoVehicular() == null || p.getPoseeTuercasAntirrobo() == null
				|| p.getPoseeAlarma() == null)
			errores.add(new Error("No se especificaron las medidas de seguridad"));

		// Validar hijos
		errores.addAll(validarHijos(p.getHijos()));

		// Validar siniestros
		errores.addAll(validarNumeroDeSiniestros(p.getSiniestros()));

		return errores;
	}

	private static ArrayList<Error> validarDomicilioDeRiesgo(Integer localidad) {
		ArrayList<Error> errores = new ArrayList<>();
		if (localidad == null)
			errores.add(new Error("No se definió una localidad de riesgo"));
		else if (DaoGeografico.getLocalidad(localidad) == null)
			errores.add(new Error("No existe el domicilio de riesgo especificado"));
		return errores;
	}

	private static ArrayList<Error> validarNumeroDeCliente(String nroCliente) {
		ArrayList<Error> errores = new ArrayList<>();
		if (nroCliente == null)
			errores.add(new Error("Falta definir el cliente o el numero de cliente no es válido"));
		else if (!nroCliente.matches("[0-9]{10}"))
			errores.add(new Error("El numero de cliente no es válido"));
		else if (DaoCliente.buscarCliente(new NumeroCliente(nroCliente)) == null)
			errores.add(new Error("No existe el cliente especificado"));
		return errores;
	}

	private static ArrayList<Error> validarModeloYAnioDeFabricacion(Integer modelo, Integer anio) {
		ArrayList<Error> errores = new ArrayList<>();
		if (modelo == null)
			errores.add(new Error("No se definió el modelo del vehículo"));
		else {
			if (GestorModelos.getModelo(modelo) == null)
				errores.add(new Error("No existe el modelo de vehículo especificado"));
			else {
				// Validar anio
				Boolean existe = false;
				// Validar existencia de anio de fabricación
				if (anio == null)
					errores.add(new Error("No de definió un año de fabricación"));

				else {
					for (Cotizacion m : GestorModelos.getModelo(modelo).getAnios()) {
						if (m.getAnio() - anio == 0)
							existe = true;
					}
					if (!existe)
						errores.add(new Error("El modelo seleccionado no se fabricó en el año indicado"));
				}
			}
		}
		return errores;
	}

	private static ArrayList<Error> validarMotor(String motor) {
		ArrayList<Error> errores = new ArrayList<>();
		if (motor == null)
			errores.add(new Error("No se ingresó el número de motor"));
		else {
			motor = motor.toUpperCase();
			if (GestorPoliza.existePolizaConMotor(motor))
				errores.add(new Error("Ya existe una poliza con el número de motor indicado"));
		}
		return errores;
	}

	private static ArrayList<Error> validarChasis(String chasis) {
		ArrayList<Error> errores = new ArrayList<>();
		if (chasis == null)
			errores.add(new Error("No se ingresó el número de chasis"));
		else {
			chasis = chasis.toUpperCase();
			if (!chasis.matches("[A-HJ-NPR-Z0-9]{17}"))
				errores.add(new Error("El número de chasis no se corresponde con formato válido"));
			else if (existePolizaConChasis(chasis))
				errores.add(new Error("Ya existe una póliza con el número de chasis indicado"));
		}
		return errores;
	}

	private static ArrayList<Error> validarPatente(String patente) {
		ArrayList<Error> errores = new ArrayList<>();
		if (patente != null) {
			patente = patente.toUpperCase().replaceAll("\\s", "");
			if (!patente.matches("([A-Z]{2}[0-9]{3}[A-Z]{2})|([A-Z]{3}[0-9]{3})"))
				errores.add(new Error("El dominio ingresado no se corresponde con formato válido"));
			else if (existePolizaConPatente(patente))
				errores.add(new Error("Ya existe una póliza con el dominio ingresado"));
		}
		return errores;
	}

	private static ArrayList<Error> validarHijos(ArrayList<Hijo> hijos) {
		ArrayList<Error> errores = new ArrayList<>();
		if (hijos != null)
			for (Hijo h : hijos) {
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
		return errores;
	}

	private static ArrayList<Error> validarNumeroDeSiniestros(Integer siniestros) {
		ArrayList<Error> errores = new ArrayList<>();
		if (siniestros == null)
			errores.add(new Error("No se especificó la cantidad de siniestros"));
		else if (siniestros < 0 || siniestros > 3)
			errores.add(new Error(
					"La cantidad de siniestros especficada no se encuentra dentro de los parametros esperadas"));
		return errores;
	}

	private static boolean existePolizaConPatente(String dominio) {
		ArrayList<Poliza> polizas = DaoPoliza.getPolizasVigentesOGeneradasConDominio(dominio);
		return !polizas.isEmpty();
	}

	private static boolean existePolizaConMotor(String motor) {
		ArrayList<Poliza> polizas = DaoPoliza.getPolizasVigentesOGeneradasConMotor(motor);
		return !polizas.isEmpty();
	}

	private static boolean existePolizaConChasis(String chasis) {
		ArrayList<Poliza> polizas = DaoPoliza.getPolizasVigentesOGeneradasConChasis(chasis);
		return !polizas.isEmpty();
	}

	// TODO: Modificar (hibernate)
	public static Poliza generarPoliza(PolizaDTO p) {

		Poliza poliza = new Poliza();
		NumeroPoliza numeroPoliza = new NumeroPoliza(1, 1);
		poliza.setNroPoliza(numeroPoliza);

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
		poliza.setPremio(new BigDecimal("100"));
		poliza.setPrima(new BigDecimal("100.54"));
		poliza.setDerechoEmision(new BigDecimal("100.65"));

		Set<Cuota> cuotas = new HashSet<Cuota>();
		if (p.getModalidadPago().equals("MENSUAL")) {
			for (int i = 0; i < 6; i++) {
				Cuota cuota = new Cuota();
				cuota.setEstadoCuota(EstadoCuota.PENDIENTE);
				cuota.setFechaVencimiento(java.sql.Date.valueOf(inicioVigencia.minusDays(1).plusMonths(i)));
				cuota.setImporte(new BigDecimal("100.65"));
				cuota.setPoliza(poliza);

				cuotas.add(cuota);
			}
		} else {
			Cuota cuota = new Cuota();
			cuota.setFechaVencimiento(java.sql.Date.valueOf(inicioVigencia.minusDays(1)));
			cuota.setImporte(new BigDecimal("100"));
			cuota.setEstadoCuota(EstadoCuota.PENDIENTE);
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
		p.setNroPoliza(new NumeroPoliza(1, p.getIdPoliza(), 1));
		DaoPoliza.update(p);
		GestorClientes.actualizarCondicionCliente(p.getCliente());
		return result;
	}

	@SuppressWarnings("incomplete-switch")
	public static void actualizarPolizas() {
		ArrayList<Poliza> polizas = new ArrayList<Poliza>(DaoPoliza.getAllPolizas());

		for (Poliza p : polizas) {
			switch (p.getEstadoPoliza()) {
			case GENERADA: {
				Date today = new Date();
				if (today.after(p.getInicioVigencia())) {
					if (p.moroso()) {
						p.setEstadoPoliza(EstadoPoliza.SUSPENDIDA);
					} else {
						p.setEstadoPoliza(EstadoPoliza.VIGENTE);
					}
				}
				break;
			}

			case VIGENTE: {
				if (p.moroso()) {
					p.setEstadoPoliza(EstadoPoliza.SUSPENDIDA);
				}
				break;
			}

			case SUSPENDIDA: {
				if (!p.moroso()) {
					p.setEstadoPoliza(EstadoPoliza.VIGENTE);
				}
				break;
			}
			}

			Date today = new Date();
			if (today.after(p.getFinVigencia())) {
				p.setEstadoPoliza(EstadoPoliza.FINALIZADA);
			}
		}

		DaoPoliza.actualizarPolizas(polizas);
	}

}
