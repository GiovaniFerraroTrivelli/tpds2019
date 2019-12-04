package dao;

import java.io.Serializable;

import org.hibernate.Session;

import dataAccess.HibernateUtil;
import usuarios.Usuario;

public class DaoUsuario {
	
	public static final Session session = HibernateUtil.getSession();
	
	public static Usuario getUsuario(String nombreUsuario) {
		//Session session = HibernateUtil.getSession();
		Usuario usuario = session.get(Usuario.class, nombreUsuario);
		return usuario;
	}
}
