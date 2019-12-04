package dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dataAccess.HibernateUtil;
import dominio.Cliente;
import dominio.NumeroPoliza;
import dominio.Poliza;
import dominio.TipoCobertura;
import excepciones.DatoNoEncontradoException;

public class DaoPoliza {
	
	public static final Session session = HibernateUtil.getSession();
	
	public static Poliza getPoliza(Integer nroPoliza) {
		//Session session = HibernateUtil.getSession();
		Poliza poliza = session.get(Poliza.class, nroPoliza);
		return poliza;
	}
	
	public static ArrayList<Poliza> buscarPoliza(String numeroPoliza) {
		//Session session = HibernateUtil.getSession();
		QueryBuilder qb = new QueryBuilder();
		Query query = qb.getQuery(new NumeroPoliza(numeroPoliza), session);
		ArrayList<Poliza> listaPolizas = new ArrayList<Poliza>(query.list());
		return listaPolizas;
	}
	
	public static Poliza getPolizaConMotor(String motor) {
		//Session session = HibernateUtil.getSession();
		String hql = "FROM Poliza WHERE motor=" + motor;
		Query<Poliza> query = null;
		try {
			query = session.createQuery(hql);
		} catch (Exception e) {
		}
		Poliza poliza = null;
		if (query != null) poliza = query.getSingleResult();
		return poliza;		
	}
	
	//TODO: Revisar este metodo
	public static Poliza getPolizaConChasis(String chasis) {
		//Session session = HibernateUtil.getSession();
		String hql = "FROM Poliza WHERE chasis=" + chasis;
		Query<Poliza> query = null;
		try {
			query = session.createQuery(hql);
		} catch (Exception e) {
		}
		Poliza poliza = null;
		if (query != null) poliza = query.getSingleResult();
		return poliza;
	}

	public static void save(Poliza p) {
		//Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();
		session.save(p);
		t.commit();
	}

	public static void update(Poliza p) {
		Transaction t = session.beginTransaction();
		session.update(p);
		t.commit();
	}
	
	public static ArrayList<TipoCobertura> getCoberturas() {
		
		try {
			
			//TODO: Corregir esto con la arquitectura nueva
			
			String hql = "FROM TipoCobertura ORDER BY nombre ASC";
			Query<TipoCobertura> query = session.createQuery(hql);
			for (TipoCobertura c : query.list()) {
				//c.setHistorialFactorCobertura(GestorCoberturas.getHistorialFactorCobertura(c.getIdCobertura()));
			}
			return new ArrayList<TipoCobertura>(query.list());
		} catch (HibernateException e) {
			return null;
		}
	}

	/*
	private static HistorialValor<Float> getHistorialFactorCobertura(Integer idCobertura) {
		HistorialValor<Float> historial = new HistorialValor<>();
		return historial;

		// historial.setHistorial(historial);
	}
	 */

	public static TipoCobertura getCoberturaContraTerceros() throws DatoNoEncontradoException {
		Query<TipoCobertura> query;
		TipoCobertura cobertura = null;
		try {
			String hql = "FROM TipoCobertura WHERE nombre='Responsabilidad Civil'";
			query = session.createQuery(hql);
			cobertura = query.getSingleResult();

		} catch (HibernateException e) {
	}
		
		return cobertura;
		
	}
	
	public static TipoCobertura getCobertura(Integer id) {

		TipoCobertura tipoCobertura =  session.get(TipoCobertura.class, id);
		return tipoCobertura;
	}
}
