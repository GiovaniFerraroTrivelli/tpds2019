package gestores;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import dao.DaoPoliza;
import dataAccess.HibernateUtil;
import dominio.TipoCobertura;
import excepciones.DatoNoEncontradoException;

public class GestorCoberturas {
	
	private Session session = HibernateUtil.getSession();
	
	public static ArrayList<TipoCobertura> getCoberturas() {
		return DaoPoliza.getCoberturas();
	}

	public static TipoCobertura getCoberturaContraTerceros() throws DatoNoEncontradoException {
		return DaoPoliza.getCoberturaContraTerceros();
		
	}
	
	public static TipoCobertura getCobertura(Integer id) {
		return DaoPoliza.getCobertura(id);
	}
}
