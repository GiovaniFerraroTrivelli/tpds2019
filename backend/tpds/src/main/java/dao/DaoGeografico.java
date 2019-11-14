package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccess.HibernateUtil;
import dominio.Cliente;
import dominio.Localidad;

public class DaoGeografico {
	public static Localidad getLocalidad(Integer idLocalidad) {
		Session session = HibernateUtil.openSession();
		Localidad localidad = session.get(Localidad.class, idLocalidad);
		return localidad;
	}

	public static void save(Localidad l) {
		Session session = HibernateUtil.openSession();
		Transaction t = session.beginTransaction();
		session.save(l);
		t.commit();
	}

}
