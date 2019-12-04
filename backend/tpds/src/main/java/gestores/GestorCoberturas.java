package gestores;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import dataAccess.HibernateUtil;
import dominio.TipoCobertura;
import excepciones.DatoNoEncontradoException;

public class GestorCoberturas {
	
	
	public static ArrayList<TipoCobertura> getCoberturas() throws DatoNoEncontradoException {
		
		Session session = HibernateUtil.getSession();
		try {
			
			//TODO: Corregir esto con la arquitectura nueva
			
			String hql = "FROM TipoCobertura ORDER BY nombre ASC";
			Query<TipoCobertura> query = session.createQuery(hql);
			for (TipoCobertura c : query.list()) {
				//c.setHistorialFactorCobertura(GestorCoberturas.getHistorialFactorCobertura(c.getIdCobertura()));
			}
			session.close();
			return new ArrayList<TipoCobertura>(query.list());
		} catch (HibernateException e) {
			session.close();
			throw new DatoNoEncontradoException();
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
		Session session = HibernateUtil.getSession();
		Query<TipoCobertura> query;
		TipoCobertura cobertura = null;
		try {
			String hql = "FROM TipoCobertura WHERE nombre='Responsabilidad Civil'";
			query = session.createQuery(hql);
			cobertura = query.getSingleResult();
			session.close();
		} catch (HibernateException e) {
			session.close();
			throw new DatoNoEncontradoException();
		}
		
		return cobertura;
		
	}
	
	public static TipoCobertura getCobertura(Integer id) {
		Session session = HibernateUtil.getSession();
		TipoCobertura tipoCobertura =  session.get(TipoCobertura.class, id);
		session.close();
		return tipoCobertura;
	}
}
