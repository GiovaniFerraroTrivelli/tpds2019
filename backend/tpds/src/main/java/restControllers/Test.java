package restControllers;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccess.HibernateUtil;
import dominio.TipoCobertura;

public class Test {

	public static void main(String[] args) {

		HibernateUtil.createSessionFactory();

		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		TipoCobertura c1 = new TipoCobertura("Responsabilidad Civil",
				"Hacia terceros transportados y no transportados, con límite de $6.000.000 en la Argentina y $600.000 para países limítrofes.");

		TipoCobertura c2 = new TipoCobertura("Responsabilidad Civil, Robo o Incendio total",
				"Responsabilidad Civil + Robo Total (apoderamiento ilegítimo del vehículo con violencia) + Incendio Total (daños provocados por incendio o explosión interna o externa, siempre que haya fuego).");

		TipoCobertura c3 = new TipoCobertura("Todo Total",
				"Responsabilidad Civil + Robo Total, Hurto Total (apoderamiento ilegítimo del vehículo sin violencia) + Incendio Total + Destrucción Total (los restos no deben superar el 20% del valor del vehículo).");

		TipoCobertura c4 = new TipoCobertura("Terceros Completos",
				"Responsabilidad Civil + Robo Parcial, Hurto Total y Parcial (incluye partes integrantes del vehículo Ej.: partes fijas y accesorios) + Incendio Total y Parcial + Destrucción Total.");

		TipoCobertura c5 = new TipoCobertura("Todo Riesgo con Franquicia",
				"Responsabilidad Civil + Robo, Hurto é Incendio Total y Parcial + Daños Totales y Parciales (incluye daños parciales con una franquicia a cargo del asegurado).");

		
		
		try {

			session.save(c1);
			session.save(c2);
			session.save(c3);
			session.save(c4);
			session.save(c5);
			
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
