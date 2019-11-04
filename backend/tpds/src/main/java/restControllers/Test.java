package restControllers;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccess.HibernateUtil;
import dominio.Provincia;
import dominio.TipoCobertura;

public class Test {

	public static void main(String[] args) {

		HibernateUtil.createSessionFactory();

		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		try {
			
			TipoCobertura tc = session.get(TipoCobertura.class, 5);
			System.out.println(tc.getDescripcion());

		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			HibernateUtil.shutdown();
		}

		session.close();
		HibernateUtil.shutdown();

	}

}
