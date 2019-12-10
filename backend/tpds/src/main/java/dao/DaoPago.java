package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import dataAccess.HibernateUtil;
import dominio.Pago;
import dominio.Recibo;

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
	
	public static void updatePago(Pago p) {
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(p);
			tx.commit();
		} catch (ConstraintViolationException e) {
			tx.rollback();
			throw e;
		}
	}
	
	
	public static Integer gaurdarRecibo(Recibo recibo) {
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(recibo);
			tx.commit();
		} catch (ConstraintViolationException e) {
			tx.rollback();
			throw e;
		}
		return recibo.getNumeroRecibo();
	}
}
