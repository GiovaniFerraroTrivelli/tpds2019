package gestores;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.query.Query;

import dataAccess.HibernateUtil;
import dominio.Localidad;
import dominio.Pais;
import dominio.Provincia;
import excepciones.DatoNoEncontradoException;

public class GestorGeografico{
	
	public static Provincia getProvincia(Integer idProvincia) throws DatoNoEncontradoException{
		Provincia result = HibernateUtil.getSession().get(Provincia.class, idProvincia);
		if (result.equals(null))throw new DatoNoEncontradoException();
		else return result;
	}
	
	public static Pais getPais(Integer idPais) throws DatoNoEncontradoException{
		Pais result = HibernateUtil.getSession().get(Pais.class, idPais);
		if (result.equals(null))throw new DatoNoEncontradoException();
		else return result;
	}
	
	public static Localidad getLocalidad(Integer idLocalidad) throws DatoNoEncontradoException{
		Localidad result = HibernateUtil.getSession().get(Localidad.class, idLocalidad);
		if (result.equals(null))throw new DatoNoEncontradoException();
		else return result;
	}
	
	public static ArrayList<Provincia> getProvinciasDePais(Integer idPais) throws DatoNoEncontradoException{
		try {
			String hql = "FROM Provincia WHERE id_pais=" + idPais.toString() + " ORDER BY nombre ASC";
			Query<Provincia> query = HibernateUtil.getSession().createQuery(hql);
			if (query.list().isEmpty()) throw new ObjectNotFoundException(hql, hql);
			return  new ArrayList<Provincia>(query.list());
		} catch (ObjectNotFoundException e) {
			throw new DatoNoEncontradoException();
		}
	}
	
	public static ArrayList<Localidad> getLocalidadesDeProvincia(Integer idProvincia) throws DatoNoEncontradoException{
		try {
			String hql = "FROM Localidad WHERE id_provincia=" + idProvincia.toString() + " ORDER BY nombre ASC";
			Query<Localidad> query = HibernateUtil.getSession().createQuery(hql);
			return  new ArrayList<Localidad>(query.list());
		} catch (HibernateException e) {
			throw new DatoNoEncontradoException();
		}
	}
}