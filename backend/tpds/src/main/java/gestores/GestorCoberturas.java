package gestores;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import dataAccess.HibernateUtil;
import dominio.TipoCobertura;
import excepciones.DatoNoEncontradoException;
import historialValor.HistorialValor;

public class GestorCoberturas {
	public static ArrayList<TipoCobertura> getCoberturas() throws DatoNoEncontradoException{
		try {
			String hql = "FROM TipoCobertura ORDER BY nombre ASC";
			Query<TipoCobertura> query = HibernateUtil.getSession().createQuery(hql);
			for (TipoCobertura c : query.list()) {
				c.setHistorialFactorCobertura(GestorCoberturas.getHistorialFactorCobertura(c.getIdCobertura()));
			}
			return  new ArrayList<TipoCobertura>(query.list());
		} catch (HibernateException e) {
			throw new DatoNoEncontradoException();
		}
	}

	private static HistorialValor<Float> getHistorialFactorCobertura(Integer idCobertura) {
		HistorialValor<Float> historial = new HistorialValor<>();
		return historial;
		
		//historial.setHistorial(historial);
	}
}
