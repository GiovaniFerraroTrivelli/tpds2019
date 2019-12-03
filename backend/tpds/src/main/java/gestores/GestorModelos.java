package gestores;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import dao.DaoVehiculo;
import dataAccess.HibernateUtil;
import dominio.Cotizacion;
import dominio.Localidad;
import dominio.Marca;
import dominio.Modelo;
import excepciones.DatoNoEncontradoException;

public class GestorModelos {
	public static ArrayList<Marca> getMarcas() throws DatoNoEncontradoException {
		try {
			String hql = "FROM Marca ORDER BY nombre ASC";
			Query<Marca> query = HibernateUtil.getSession().createQuery(hql);
			return new ArrayList<Marca>(query.list());
		} catch (HibernateException e) {
			throw new DatoNoEncontradoException();
		}
	}

	public static ArrayList<Modelo> getModelosDeMarca(Integer idMarca) throws DatoNoEncontradoException {
		try {
			String hql = "FROM Modelo WHERE id_marca=" + idMarca.toString() + " ORDER BY nombre ASC";
			Query<Modelo> query = HibernateUtil.getSession().createQuery(hql);
			return new ArrayList<Modelo>(query.list());
		} catch (HibernateException e) {
			throw new DatoNoEncontradoException();
		}
	}
	
	public static Modelo getModelo(Integer idModelo) {
		return DaoVehiculo.getModelo(idModelo);
	}

	public static ArrayList<Integer> getAnios(Integer idModelo) throws DatoNoEncontradoException {
		Session session = HibernateUtil.getSession();
		String hql = "FROM Cotizacion WHERE id_modelo =" + idModelo.toString() + " ORDER BY anio DESC";
		ArrayList<Integer> anios = new ArrayList<Integer>();

		try {
			Query<Cotizacion> query = session.createQuery(hql);
			ArrayList<Cotizacion> listaModelos = new ArrayList<Cotizacion>(query.list());

			for (Cotizacion a : listaModelos)
				anios.add(a.getAnio());

		} catch (HibernateException e) {
			throw new DatoNoEncontradoException();
		}

		session.close();
		return anios;
	}
}
