package restControllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dataAccess.HibernateUtil;
import dominio.Localidad;
import dominio.Pais;
import dominio.Provincia;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub



		try {
			
			
			/*HibernateUtil.getSession().beginTransaction();
			Provincia p = HibernateUtil.getSession().get(Provincia.class, 2);
			
			System.out.println(p.getNombre());
			
			
			
			Localidad l = new Localidad();
			l.setIdLocalidad(666);
			l.setNombre("30 mil pesos");
			l.setProvincia(p);
			HibernateUtil.getSession().save(l);
			
			HibernateUtil.getSession().getTransaction().commit();*/
			

			
			/*
			long start = System.nanoTime();
			Pais pais = session.get(Pais.class, 1);
			long elapsedTime = System.nanoTime() - start;
			System.out.println(elapsedTime);
			*/
			Error e = new Error("Hola");
			System.out.println(e.getMensaje());

			


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
