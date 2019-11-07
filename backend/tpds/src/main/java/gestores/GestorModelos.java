package gestores;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import dataAccess.HibernateUtil;
import dominio.AnioModelo;
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

	public static ArrayList<Integer> getAnios(Integer idModelo) throws DatoNoEncontradoException {
		Session session = HibernateUtil.getSession();
		String hql = "FROM AnioModelo WHERE id_modelo=" + idModelo.toString() + " ORDER BY valor DESC";
		ArrayList<Integer> anios = new ArrayList<Integer>();

		try {
			Query<AnioModelo> query = session.createQuery(hql);
			ArrayList<AnioModelo> listaModelos = new ArrayList<AnioModelo>(query.list());

			for (AnioModelo a : listaModelos)
				anios.add(a.getValor());

		} catch (HibernateException e) {
			throw new DatoNoEncontradoException();
		}

		session.close();
		return anios;
	}
}
