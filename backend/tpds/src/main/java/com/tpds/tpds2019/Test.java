package com.tpds.tpds2019;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dataAccess.HibernateUtil;
import dominio.Pais;
import dominio.Provincia;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSession();


		try {			
			
			long start = System.nanoTime();
			List<Provincia> provincias = loadAllData(session);
			long elapsedTime = System.nanoTime() - start;
			System.out.println(elapsedTime);
			
			


			


		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			HibernateUtil.shutdown();
		}

		session.close();
		HibernateUtil.shutdown();
	}
	
	public static List<Provincia> loadAllData(Session session) {
	    return session.createQuery("SELECT a FROM provincias a", Provincia.class).getResultList();      
	}

}
