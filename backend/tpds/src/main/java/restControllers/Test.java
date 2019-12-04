package restControllers;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.DaoCliente;
import dataAccess.HibernateUtil;
import dataTransferObjects.ParametrosDeBusqueda;
import dominio.Cliente;
import dominio.NumeroCliente;
import dominio.Poliza;

public class Test {

	public static void main(String[] args) {

		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();



		try { // Code here:
			System.out.println(new Date());
			
			

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
