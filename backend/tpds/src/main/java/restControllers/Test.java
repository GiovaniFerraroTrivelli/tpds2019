package restControllers;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.DaoCliente;
import dataAccess.HibernateUtil;
import dataTransferObjects.ParametrosDeBusqueda;
import dominio.Cliente;

public class Test {

	public static void main(String[] args) {

		Session s = HibernateUtil.openSession();
		Transaction tx = s.beginTransaction();



		try { // Code here:

			
			

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			s.close();
			HibernateUtil.shutdown();
		}
		s.close();
		HibernateUtil.shutdown();
		System.out.println("DONE");

	}
}
