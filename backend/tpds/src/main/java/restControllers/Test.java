package restControllers;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccess.HibernateUtil;
import dominio.Cliente;
import dominio.Documento;
import dominio.EstadoCivil;
import dominio.TipoCobertura;
import dominio.TipoDocumento;

public class Test {

	public static void main(String[] args) {
		recuperarCliente();

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
