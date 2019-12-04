package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccess.HibernateUtil;
import dominio.Cliente;
import dominio.Localidad;

public class DaoGeografico {
	
	public static final Session session = HibernateUtil.getSession();
	
	public static Localidad getLocalidad(Integer idLocalidad) {
		//Session session = HibernateUtil.getSession();
		Localidad localidad = session.get(Localidad.class, idLocalidad);
		return localidad;
	}

	public static void guardarLocalidad(Localidad l) {
		//Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();
		session.save(l);
		t.commit();
	}

}
