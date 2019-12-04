package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccess.HibernateUtil;
import dominio.Modelo;


public class DaoVehiculo {
	
	public static final Session session = HibernateUtil.getSession();

	public static Modelo getModelo(Integer idModelo) {
		//Session session = HibernateUtil.getSession();
		Modelo modelo = session.get(Modelo.class, idModelo);
		return modelo;
	}

	public static void save(Modelo m) {
		//Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();
		session.save(m);
		t.commit();
	}
}
