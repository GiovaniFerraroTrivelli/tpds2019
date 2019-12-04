package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dataAccess.HibernateUtil;
import dominio.Cliente;
import dominio.NumeroPoliza;
import dominio.Poliza;

public class DaoPoliza {
	public static Poliza getPoliza(Integer nroPoliza) {
		Session session = HibernateUtil.getSession();
		Poliza poliza = session.get(Poliza.class, nroPoliza);
		return poliza;
	}
	
	public static ArrayList<Poliza> buscarPoliza(String numeroPoliza) {
		Session session = HibernateUtil.getSession();
		QueryBuilder qb = new QueryBuilder();
		Query query = qb.getQuery(new NumeroPoliza(numeroPoliza), session);
		ArrayList<Poliza> listaPolizas = new ArrayList<Poliza>(query.list());
		return listaPolizas;
	}
	
	public static Poliza getPolizaConMotor(String motor) {
		Session session = HibernateUtil.getSession();
		String hql = "FROM Poliza WHERE motor=" + motor;
		Query<Poliza> query = null;
		try {
			query = session.createQuery(hql);
		} catch (Exception e) {
		}
		Poliza poliza = null;
		if (query != null) poliza = query.getSingleResult();
		return poliza;		
	}
	
	//TODO: Revisar este metodo
	public static Poliza getPolizaConChasis(String chasis) {
		Session session = HibernateUtil.getSession();
		String hql = "FROM Poliza WHERE chasis=" + chasis;
		Query<Poliza> query = null;
		try {
			query = session.createQuery(hql);
		} catch (Exception e) {
		}
		Poliza poliza = null;
		if (query != null) poliza = query.getSingleResult();
		return poliza;
	}

	public static void save(Poliza p) {
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();
		session.save(p);
		t.commit();

	}
}