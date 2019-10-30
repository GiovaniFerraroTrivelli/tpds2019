package Gestores;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import antlr.collections.List;
import dataAccess.HibernateUtil;
import dominio.Localidad;
import dominio.Pais;
import dominio.Provincia;
import excepciones.DatoNoEncontradoException;

public class GestorGeografico{
	
	public static Provincia getProvincia(Integer idProvincia) throws DatoNoEncontradoException{
		try {
			Provincia result = HibernateUtil.getSession().get(Provincia.class, idProvincia);
			return result;
		} catch (HibernateException e) {
			throw new DatoNoEncontradoException();
		}
	}
	
	public static Pais getPais(Integer idPais) throws DatoNoEncontradoException{
		try {
			Pais result = HibernateUtil.getSession().get(Pais.class, idPais);
			return result;
		} catch (HibernateException e) {
			throw new DatoNoEncontradoException();
		}
	}
	
	public static Localidad getLocalidad(String CPA) throws DatoNoEncontradoException{
		try {
			Localidad result = HibernateUtil.getSession().get(Localidad.class, CPA);
			return result;
		} catch (HibernateException e) {
			throw new DatoNoEncontradoException();
		}
	}
	
	public static ArrayList<Provincia> getProvinciasDePais(Integer idPais) throws DatoNoEncontradoException{
		
		try {
			String hql = "FROM Provincia WHERE id_pais=" + idPais.toString();
			Query query = HibernateUtil.getSession().createQuery(hql);
			return  new ArrayList<Provincia>(query.list());
		} catch (HibernateException e) {
			throw new DatoNoEncontradoException();
		}
	}
	
	public static ArrayList<Localidad> getLocalidadesDeProvincia(Integer idProvincia) throws DatoNoEncontradoException{
		try {
			String hql = "FROM Localidad WHERE provinciaid=" + idProvincia.toString();
			Query query = HibernateUtil.getSession().createQuery(hql);
			return  new ArrayList<Localidad>(query.list());
		} catch (HibernateException e) {
			throw new DatoNoEncontradoException();
		}
	}

}