package restControllers;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import dataAccess.HibernateUtil;
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

public class Test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		HibernateUtil.createSessionFactory();
		Session s = HibernateUtil.getSession();		

//		try {
//			String hql = "FROM Cotizacion WHERE id_modelo=" + idModelo.toString() + " ORDER BY anio DESC";
//			Query<Cotizacion> query = s.createQuery(hql);
//			
//			ArrayList<Cotizacion> listaModelos = new ArrayList<Cotizacion>(query.list());
//			
//			for(Cotizacion a : listaModelos) {
//				System.out.println(a.getAnio());
//			}
//		} catch (Exception e) {
//			s.close();
//			HibernateUtil.shutdown();
//		}
//		
		
		Cliente c = new Cliente();
		c.setApellido("Storani");
		c.setNombre("Miguel");
		c.setEmail("miguelignaciostorani@gmail.com");
		c.setCuil("20409679049");
		c.setFechaNacimiento(new Date("03/04/1998"));
		c.setDocumento(new Documento(TipoDocumento.DNI, 40967904));
		c.setProfesion("Estudiante");
		c.setSexo(Sexo.MASCULINO);
		c.setEstadoCivil(EstadoCivil.SOLTERO);
		c.setCondicionIva(CondicionIva.ConsumidorFinal);
		c.setIdCliente(1234567890);
		
		try {
			Localidad l = s.get(Localidad.class, 3707);
			Direccion d = new Direccion();
			d.setLocalidad(l);
			c.setDireccion(d);
			d.setCalle("Aristóbulo del Valle");
			d.setNumero(1831);
			s.save(d);
			s.save(c);

		} catch (Exception e) {
			s.close();
			HibernateUtil.shutdown();
		}
		
		s.close();
		HibernateUtil.shutdown();
	}

	private static void recuperarModeloConAnio() {
		HibernateUtil.createSessionFactory();
		Session session = HibernateUtil.getSession();

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
		Session session = HibernateUtil.getSession();
		Cotizacion a = new Cotizacion();
		a.setAnio(2011);

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
