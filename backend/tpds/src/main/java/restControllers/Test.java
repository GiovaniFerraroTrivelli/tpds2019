package restControllers;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import dataAccess.HibernateUtil;
import dominio.AnioModelo;
import dominio.Cliente;
import dominio.Documento;
import dominio.Poliza;
import dominio.Hijo;
import dominio.Modelo;
import dominio.TipoCobertura;
import enumeradores.EstadoCivil;
import enumeradores.Sexo;
import enumeradores.TipoDocumento;

public class Test {

	public static void main(String[] args) {
		//agregarAnioAModelo();
		recuperarModeloConAnio();
	}

	private static void recuperarModeloConAnio() {
		HibernateUtil.createSessionFactory();
		Session session = HibernateUtil.getSession();

		try {
			Modelo m = session.get(Modelo.class, 1);
			ArrayList<AnioModelo> listaAnios = new ArrayList<AnioModelo>(m.getAnios());
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
		Session session = HibernateUtil.getSession();
		AnioModelo a = new AnioModelo();
		a.setValor(2011);

		try {
			Transaction tx = session.beginTransaction();
			Modelo m = session.get(Modelo.class, 1);
			m.getAnios().add(a);
			a.setModelo(m);
			//session.save(a);
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
		Session session = HibernateUtil.getSession();

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
		Session session = HibernateUtil.getSession();

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

		Session session = HibernateUtil.getSession();
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
		Session session = HibernateUtil.getSession();

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
