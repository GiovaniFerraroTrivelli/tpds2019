package restControllers;

import java.util.List;

import org.hibernate.Session;

import dataAccess.HibernateUtil;
import dominio.Provincia;

public class Test {

	public static void main(String[] args) {

		try {

			long start = System.nanoTime();
			HibernateUtil.getSessionfactory();
			long elapsedTime = System.nanoTime() - start;
			System.out.println("TIEMPO PARA CREAR UNA SESSION FACTORY: " + elapsedTime + " NS");

			start = System.nanoTime();
			HibernateUtil.getSession();
			elapsedTime = System.nanoTime() - start;
			System.out.println("TIEMPO PARA CREAR UNA SESSION: " + elapsedTime + " NS");

			start = System.nanoTime();
			HibernateUtil.getSession();
			elapsedTime = System.nanoTime() - start;
			System.out.println("TIEMPO PARA CREAR UNA SESSION: " + elapsedTime + " NS");

			start = System.nanoTime();
			HibernateUtil.getSession();
			elapsedTime = System.nanoTime() - start;
			System.out.println("TIEMPO PARA CREAR UNA SESSION: " + elapsedTime + " NS");

			/*
			 * long start = System.nanoTime(); Pais pais = session.get(Pais.class, 1); long
			 * elapsedTime = System.nanoTime() - start; System.out.println(elapsedTime);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.getSession().close();
			HibernateUtil.shutdown();
		}

		HibernateUtil.getSession().close();
		HibernateUtil.shutdown();
	}

	public static List<Provincia> loadAllData(Session session) {
		return session.createQuery("SELECT a FROM Provincia a", Provincia.class).getResultList();
	}

}
