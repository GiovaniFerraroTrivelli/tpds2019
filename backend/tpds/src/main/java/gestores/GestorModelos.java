package gestores;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import dataAccess.HibernateUtil;
import dominio.Localidad;
import dominio.Marca;
import dominio.Modelo;
import excepciones.DatoNoEncontradoException;

public class GestorModelos {
	public static ArrayList<Marca> getMarcas() throws DatoNoEncontradoException{
		try {
			String hql = "FROM Marca ORDER BY nombre ASC";
			Query<Marca> query = HibernateUtil.getSession().createQuery(hql);
			return  new ArrayList<Marca>(query.list());
		} catch (HibernateException e) {
			throw new DatoNoEncontradoException();
		}
	}
	
	public static ArrayList<Modelo> getModelosDeMarca(Integer idMarca) throws DatoNoEncontradoException{
		try {
			String hql = "FROM Modelo WHERE id_marca=" + idMarca.toString() + " ORDER BY nombre ASC";
			Query<Modelo> query = HibernateUtil.getSession().createQuery(hql);
			return  new ArrayList<Modelo>(query.list());
		} catch (HibernateException e) {
			throw new DatoNoEncontradoException();
		}
	}
}
