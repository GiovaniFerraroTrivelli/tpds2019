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
import dominio.Marca;
import dominio.Modelo;
import dominio.Pais;
import dominio.Provincia;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub



		try {
			
			
			HibernateUtil.getSession().beginTransaction();
			Marca m = HibernateUtil.getSession().get(Marca.class, 1);
			
			System.out.println(m.getNombre());
			
			Modelo mod = new Modelo();
			mod.setMarca(m);
			mod.setNombre("test");
			HibernateUtil.getSession().save(mod);
			
			HibernateUtil.getSession().getTransaction().commit();

			
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
