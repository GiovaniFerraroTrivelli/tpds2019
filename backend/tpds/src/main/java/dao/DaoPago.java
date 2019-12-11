package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import dataAccess.HibernateUtil;
import dominio.Descuento;
import dominio.Pago;
import dominio.PagoCuota;
import dominio.Recargo;
import dominio.Recibo;

public class DaoPago {
	public static Session session = HibernateUtil.getSession();

	public static Descuento getDescuentoPagoAdelantado() {
		return session.get(Descuento.class, 1);
	}

	public static Recargo getRecargoMora() {
		return session.get(Recargo.class, 1);
	}

	public static void guardarPago(Pago p) {
		Transaction tx = session.beginTransaction();

		try {
			session.save(p);
			for (PagoCuota pc : p.getCuotas()) { 
				session.save(pc); 
			}
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

	public static Integer guardarRecibo(Recibo recibo) {
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
	
	public static void refresh(Object object) {
		session.refresh(object);
	}
}
