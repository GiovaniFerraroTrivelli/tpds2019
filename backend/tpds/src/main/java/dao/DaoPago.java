package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import dataAccess.HibernateUtil;
import dominio.Pago;

public class DaoPago {
	public static Session session = HibernateUtil.getSession();

	public static void guardarPago(Pago p) {
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(p);
			tx.commit();
		} catch (ConstraintViolationException e) {
			tx.rollback();
			throw e;
		}
	}
}
