package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccess.HibernateUtil;
import dominio.Modelo;


public class DaoVehiculo {

	public static Modelo getModelo(Integer idModelo) {
		Session session = HibernateUtil.openSession();
		Modelo modelo = session.get(Modelo.class, idModelo);
		return modelo;
	}

	public static void save(Modelo m) {
		Session session = HibernateUtil.openSession();
		Transaction t = session.beginTransaction();
		session.save(m);
		t.commit();
	}
}