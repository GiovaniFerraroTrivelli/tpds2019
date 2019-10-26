package com.tpds.tpds2019;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccess.HibernateUtil;
import dominio.Pais;
import dominio.Provincia;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		Pais p = new Pais();

		p.setNombre("Argentina");

		Provincia prov1 = new Provincia();
		prov1.setNombre("Santa Fe");
		prov1.setPais(p);

		Provincia prov2 = new Provincia();
		prov2.setNombre("Chaco");
		prov2.setPais(p);

		try {			
			session.save(p);
			session.save(prov1);
			session.save(prov2);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			HibernateUtil.shutdown();
		}

		session.close();
		HibernateUtil.shutdown();
	}

}
