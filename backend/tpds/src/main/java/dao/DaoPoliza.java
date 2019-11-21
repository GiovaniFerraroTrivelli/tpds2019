package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dataAccess.HibernateUtil;
import dominio.Cliente;
import dominio.Poliza;

public class DaoPoliza {
	public static Poliza getPoliza(Integer nroPoliza) {
		Session session = HibernateUtil.getCurrentSession();
		Poliza poliza = session.get(Poliza.class, nroPoliza);
		session.close();
		return poliza;
	}
	
	public static Poliza getPolizaConMotor(String motor) {
		Session session = HibernateUtil.getCurrentSession();
		String hql = "FROM Poliza WHERE motor=" + motor;
		Query<Poliza> query = null;
		try {
			query = session.createQuery(hql);
		} catch (Exception e) {
			session.close();
		}
		Poliza poliza = null;
		if (query != null) poliza = query.getSingleResult();
		session.close();
		return poliza;		
	}
	
	public static Poliza getPolizaConChasis(String chasis) {
		Session session = HibernateUtil.getCurrentSession();
		String hql = "FROM Poliza WHERE chasis=" + chasis;
		Query<Poliza> query = null;
		try {
			query = session.createQuery(hql);
		} catch (Exception e) {
			session.close();
		}
		Poliza poliza = null;
		if (query != null) poliza = query.getSingleResult();
		session.close();
		return poliza;
	}

	public static void save(Poliza p) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction t = session.beginTransaction();
		session.save(p);
		t.commit();
		session.close();
	}
}
