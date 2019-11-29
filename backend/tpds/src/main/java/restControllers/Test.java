package restControllers;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.util.ReflectionUtils;

import dao.DaoCliente;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import dataAccess.HibernateUtil;
import dataTransferObjects.ClienteDTO;
import dataTransferObjects.ParametrosDeBusqueda;
import dataTransferObjects.PolizaDTO;
import dominio.Cotizacion;
import dominio.Direccion;
import dominio.Cliente;
import dominio.Documento;
import dominio.Poliza;
import dominio.Hijo;
import dominio.Localidad;
import dominio.Modelo;
import dominio.TipoCobertura;
import enumeradores.CondicionIva;
import enumeradores.EstadoCivil;
import enumeradores.Sexo;
import enumeradores.TipoDocumento;
import excepciones.DatoNoEncontradoException;
import gestores.GestorClientes;
import gestores.GestorGeografico;
import historialValor.Entrada;
import historialValor.EntradaLocalidad;

public class Test {

	/*
	 * public static void main(String[] args) { Session s =
	 * HibernateUtil.openSession(); Transaction tx = s.beginTransaction();
	 * 
	 * try { // Code here:
	 * 
	 * Localidad l = s.get(Localidad.class, 2); for (EntradaLocalidad e :
	 * l.getHistorialValores()) { System.out.println(e.getValor()); }
	 * 
	 * } catch (Exception e) { tx.rollback(); e.printStackTrace(); s.close();
	 * HibernateUtil.shutdown(); } s.close(); HibernateUtil.shutdown();
	 * System.out.println("DONE");
	 * 
	 * }
	 */

	public static void main() {
		
		
	}

	public static ArrayList<Cliente> buscarCliente(ParametrosDeBusqueda c) {

		Session session = HibernateUtil.openSession();
		StringBuffer str = new StringBuffer();
		str.append("FROM Cliente C WHERE ");
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();

		ReflectionUtils.doWithFields(ParametrosDeBusqueda.class, field -> {
			field.setAccessible(true);
			if (field.get(c) != null) {
				parametros.add(new Parametro(field.getName(), field.get(c)));
				str.append("C." + field.getName() + " = :" + field.getName() + " AND ");
			}
		});

		String hql = str.toString().substring(0, str.toString().length() - 5);
		Query query = session.createQuery(hql);
		for (Parametro p : parametros) {
			query.setParameter(p.getNombre(), p.getValor());
		}

		try {
			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>(query.list());
			session.close();
			HibernateUtil.shutdown();
			return listaClientes;

		} catch (NoResultException e) {
			System.out.println("NO SE ENCONTRO CLIENTE");
			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
			session.close();
			HibernateUtil.shutdown();
			return listaClientes;
		}
	}

	private static void recuperarModeloConAnio() {
		HibernateUtil.createSessionFactory();
		Session session = HibernateUtil.openSession();

		try {
			Modelo m = session.get(Modelo.class, 1);
			ArrayList<Cotizacion> listaAnios = new ArrayList<Cotizacion>(m.getAnios());
			System.out.println(m.getNombre());
			System.out.println(m.getAnios().size());
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			HibernateUtil.shutdown();
		}

		session.close();
		HibernateUtil.shutdown();
	}

	private static void agregarAnioAModelo() {
		HibernateUtil.createSessionFactory();
		Session session = HibernateUtil.openSession();
		Cotizacion a = new Cotizacion();
		a.setAnio(2011);

		try {
			Transaction tx = session.beginTransaction();
			Modelo m = session.get(Modelo.class, 1);
			m.getAnios().add(a);
			a.setModelo(m);
			// session.save(a);
			session.update(m);
			tx.commit();
			System.out.println(m.getNombre());
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			HibernateUtil.shutdown();
		}

		session.close();
		HibernateUtil.shutdown();
	}

	private static void agregarTipoCobertura() {
		HibernateUtil.createSessionFactory();
		Session session = HibernateUtil.openSession();

		Poliza p = session.get(Poliza.class, 1);
		TipoCobertura t = session.get(TipoCobertura.class, 2);

		System.out.println(p.getAnioFabricacion());

		/*
		 * Transaction tx = session.beginTransaction();
		 * 
		 * try {
		 * 
		 * session.update(p); tx.commit();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); session.close();
		 * HibernateUtil.shutdown(); }
		 */

		session.close();
		HibernateUtil.shutdown();
	}

	private static void insertarPolizaConHijos() {

		Poliza p = new Poliza();

		Hijo h1 = new Hijo();
		h1.setSexo(Sexo.FEMENINO);

		Hijo h2 = new Hijo();
		h1.setSexo(Sexo.MASCULINO);

		Set<Hijo> hijos = new HashSet<Hijo>();
		hijos.add(h1);
		hijos.add(h2);

		h1.setPoliza(p);
		h2.setPoliza(p);

		p.setAnioFabricacion(2001);
		p.setHijos(hijos);

		HibernateUtil.createSessionFactory();
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		try {

			Cliente c = session.get(Cliente.class, 1);
			p.setCliente(c);

			TipoCobertura cob = session.get(TipoCobertura.class, 2);
			p.setTipoCobertura(cob);

			session.save(p);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			HibernateUtil.shutdown();
		}

		session.close();
		HibernateUtil.shutdown();
	}

	private static void insertarCliente() {
		HibernateUtil.createSessionFactory();

		Cliente c = new Cliente();
		c.setNombre("Francisco");
		c.setApellido("Busso");
		c.setEmail("francisco.busso@outlook.com");
		c.setEstadoCivil(EstadoCivil.VIUDO);
		Documento d = new Documento(TipoDocumento.DNI, 41490799);
		c.setDocumento(d);

		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();

		try {

			session.save(c);
			tx.commit();
			System.out.println(c.getIdCliente());

		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			HibernateUtil.shutdown();
		}

		session.close();
		HibernateUtil.shutdown();
	}

	private static void recuperarCliente() {
		HibernateUtil.createSessionFactory();
		Session session = HibernateUtil.openSession();

		try {

			Cliente c = session.get(Cliente.class, 1);
			System.out.println(c.getDocumento().getNroDocumento());

		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			HibernateUtil.shutdown();
		}

		session.close();
		HibernateUtil.shutdown();
	}
}
