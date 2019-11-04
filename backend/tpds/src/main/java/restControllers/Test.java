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

		HibernateUtil.createSessionFactory();

		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		
		Cliente c = new Cliente();
		c.setNombre("Francisco");
		c.setApellido("Busso");
		c.setEmail("francisco.busso@outlook.com");
		c.setEstadoCivil(EstadoCivil.VIUDO);
		Documento d = new Documento(TipoDocumento.DNI, 41490799);
		c.setDocumento(d);
//		Cliente c = session.get(Cliente.class, 1);
//		System.out.println(c.getNombre());
//		System.out.println(c.getDocumento().getNroDocumento());
//		
		
		
		
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

}
